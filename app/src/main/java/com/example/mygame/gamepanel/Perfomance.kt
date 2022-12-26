package com.example.mygame.gamepanel

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.example.mygame.GameThread


class Perfomance(thread: GameThread) {
    private final var thread = thread
    private var avgFPS: Double = 0.0

    fun draw(canvas: Canvas){
        drawFPS(canvas)
    }

    fun drawFPS(canvas: Canvas){
        avgFPS = thread.getAverageFPS()
        var paint = Paint()
        paint.setColor(Color.WHITE)
        paint.setTextSize(50.0F);

        canvas.drawText("FPS: " + avgFPS.toString(),
                        150F, 150F, paint);
    }
}