package com.example.mygame.gameobject

import android.graphics.Bitmap

class Ghost(bitmap: Array<Bitmap>, player: Player, _velocity: Float = 30F): GameObject(bitmap) {
    private var player: Player = player
    private var slope: Double = 0.0
    private var velocity: Float = _velocity
    private var x_double: Double = 0.0
    private var y_double: Double = 0.0

    private final var incrementalVelocity = .3F

    private final val damageToPlayer: Int = 10
    private final val collideDistance: Int = (w!! + h!!) / 3

    init {
        calculateVX_VY(velocity)
    }

    override fun update() {
        x_double += velocityX
        y_double += velocityY
        if(x_double < 0 || x_double.toInt() >= screenWidth ||
            y_double.toInt() > screenHeight){
           reset()
        }
        x = x_double.toInt()
        y = y_double.toInt()
        isCollided()
    }

    private fun calculateVX_VY(veloc: Float){
        x_double = (0..screenWidth).random().toDouble()
        y_double = -(1..3).random() * w!!.toDouble()

        slope = (player.y - y_double).toDouble() / (player.x - x_double).toDouble()
        if(Math.abs(slope) < 1E-5){
            velocityX = 0F
            velocityY = veloc
        }
        else{
            // Độ dốc nhỏ, Dx > Dy
            if(Math.abs(slope) < 1){
                velocityX = if(slope < 0) -veloc else veloc
                velocityY = Math.abs(veloc * slope.toInt())
            }
            else{
                velocityX = (veloc / slope).toFloat()
                velocityY = veloc
            }
        }
    }

    fun isCollided(){
        if(this.getDistanceBetweenObjects(player) < collideDistance){
            player.getDamaged(damageToPlayer)
            reset()
        }
    }

    fun reset(){
        velocity += incrementalVelocity
        indexSelected = (0..bitmap.size - 1).random()
        calculateVX_VY(velocity)
    }

    override fun updateTouch(touch_x: Int, touch_y: Int) {
        TODO("Not yet implemented")
    }

}