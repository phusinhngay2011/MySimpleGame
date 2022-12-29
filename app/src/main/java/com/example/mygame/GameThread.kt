package com.example.mygame

import android.graphics.Canvas
import android.view.SurfaceHolder

class GameThread(private val surfaceHolder: SurfaceHolder,
                 private val gameView: GameView): Thread()  {
    private var isRunning: Boolean = false
    private val FPS = 45
    private var avgFPS =  0.0

    fun getAverageFPS(): Double{
        return Math.round(avgFPS * 10) / 10.0
    }

    fun startLoop() {
        isRunning = true
        start()
    }

    fun stopLoop(){
        isRunning = false
        try {
            join()
        }
        catch (e: InterruptedException){
            e.printStackTrace()
        }
    }


    override fun run() {
        var startTime: Long
        var timeMillis: Long
        var waitTime: Long
        val targetTime = (1000 / FPS).toLong()

        var frameCount = 0
        var startTimeFPS = System.currentTimeMillis()
        var timeLapse: Long

        while (isRunning) {
            startTime = System.nanoTime()
            canvas = null

            try {
                // locking the canvas allows us to draw on to it
                canvas = this.surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    this.gameView.update()
                    this.gameView.draw(canvas!!)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                        frameCount++;
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000
            waitTime = targetTime - timeMillis

            try {
                sleep(waitTime)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            timeLapse = (System.currentTimeMillis() - startTimeFPS)
            if(timeLapse >= 1000){
                avgFPS = frameCount / (1E-3 * timeLapse)
                startTimeFPS = System.currentTimeMillis()
                frameCount = 0
            }
        }
    }

    companion object {
        private var canvas: Canvas? = null
    }
}