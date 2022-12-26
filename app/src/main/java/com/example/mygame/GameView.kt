package com.example.mygame
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.example.mygame.gameobject.Barrier
import com.example.mygame.gameobject.Ghost
import com.example.mygame.gameobject.Player
import com.example.mygame.gamepanel.GameOver
import com.example.mygame.gamepanel.Perfomance

class GameView(context: Context, attributes: AttributeSet?): SurfaceView(context, attributes), SurfaceHolder.Callback{
    private var thread: GameThread
    private var displayFPS: Boolean = true

    private var touched: Boolean = false
    private var touched_x: Int = 0
    private var touched_y: Int = 0

    // Bitmap
    private final var player_bitmap = BitmapFactory.
                decodeResource(resources, R.drawable.player_0);

    private final val MAX_BARRIERS_ON_SCREEN = 3;
    private final val barrier_bitmaps = arrayOf(
        BitmapFactory.decodeResource(resources, R.drawable.barrier_0),
        BitmapFactory.decodeResource(resources, R.drawable.barrier_1),
        BitmapFactory.decodeResource(resources, R.drawable.barrier_2),
        BitmapFactory.decodeResource(resources, R.drawable.barrier_3),
        BitmapFactory.decodeResource(resources, R.drawable.barrier_4)
    )

    private final val MAX_GHOSTS_ON_SCREEN = 2;
    private final val ghost_bitmaps = arrayOf(
        BitmapFactory.decodeResource(resources, R.drawable.ghost_0),
        BitmapFactory.decodeResource(resources, R.drawable.ghost_1),
        BitmapFactory.decodeResource(resources, R.drawable.ghost_2),
        BitmapFactory.decodeResource(resources, R.drawable.ghost_3),
        BitmapFactory.decodeResource(resources, R.drawable.ghost_4),
        BitmapFactory.decodeResource(resources, R.drawable.ghost_5),
        BitmapFactory.decodeResource(resources, R.drawable.ghost_6),
        BitmapFactory.decodeResource(resources, R.drawable.ghost_7)
    )

    // Objects
    private final var player: Player? = null
    private final var barriers = emptyArray<Barrier>()
    private final var ghosts = emptyArray<Ghost>()

    private final var gameOver: GameOver? = null
    private final var perfomance: Perfomance? = null

    init{
        // add callback
        holder.addCallback(this)
        // instantiate the game thread
        thread = GameThread(holder, this)

        // GAME OBJECT
        player = Player(player_bitmap)
        for (i in 0..MAX_BARRIERS_ON_SCREEN-1){
            val index = (0..barrier_bitmaps.size - 1).random()
            barriers += Barrier(
                barrier_bitmaps[index],
                player!!,
                i,
                MAX_BARRIERS_ON_SCREEN
            )
        }

        for (i in 0..MAX_GHOSTS_ON_SCREEN-1){
            val index = (0..ghost_bitmaps.size - 1).random()
            ghosts += Ghost(
                ghost_bitmaps[index],
                player!!,
                20F
            )
        }

        // GAME PANEL
        gameOver = GameOver(this.context)
        perfomance = Perfomance(thread)
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        Log.d("GameView.kt", "SurfaceCreate()")
        // Start game thread
        thread.startLoop()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
        Log.d("GameView.kt", "SurfaceChanged()")
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        Log.d("GameView.kt", "SurfaceDestroyed()")
        var retry = true
        while (retry) {
            thread.stopLoop()
            retry = false
        }
    }

    fun update(){

        if(player!!.getHealthPercentage() == 0){
            return
        }

        // update Barriers
        for(i in 0 .. MAX_BARRIERS_ON_SCREEN-1){
            barriers[i].update()
            barriers[i].changeImg(barrier_bitmaps[(0..barrier_bitmaps.size-1).random()])
        }

        // Update Ghosts
        for(i in 0 .. MAX_GHOSTS_ON_SCREEN-1){
            ghosts[i].update()
            ghosts[i].changeImg(ghost_bitmaps[(0..ghost_bitmaps.size-1).random()])
        }
        // Update Player
        player!!.update()
        // update if touch screen
        updateIfTouched()
    }

    override fun draw(canvas: Canvas){
        super.draw(canvas)
        // Draw barriers
        for(i in 0..MAX_BARRIERS_ON_SCREEN-1){
            barriers[i].draw(canvas)
        }

        // Draw barriers
        for(i in 0..MAX_GHOSTS_ON_SCREEN-1){
            ghosts[i].draw(canvas)
        }


        // Draw player (main character)
        player!!.draw(canvas)

        perfomance!!.draw(canvas)

        if (player!!.getHealthPercentage() == 0){
            gameOver!!.draw(canvas)
        }
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        // when ever there is a touch on the screen,
        // we can get the position of touch
        // which we may use it for tracking some of the game objects
        touched_x = event.x.toInt()
        touched_y = event.y.toInt()

        val action = event.action
        when (action) {
            MotionEvent.ACTION_DOWN -> touched = true
            MotionEvent.ACTION_MOVE -> touched = true
            MotionEvent.ACTION_UP -> touched = true
            MotionEvent.ACTION_CANCEL -> touched = false
            MotionEvent.ACTION_OUTSIDE -> touched = false
        }
        return true
    }

    fun updateIfTouched(){
        if(touched){
            player!!.updateTouch(touched_x, touched_y)
        }
    }

    fun onPause(){
        thread.stopLoop()
    }

}