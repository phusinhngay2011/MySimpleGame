package com.example.mygame.gameobject

import android.graphics.Bitmap
import kotlin.random.Random

class Barrier(bitmap: Array<Bitmap>, player: Player, index: Int, maxBarrier: Int, veloctity: Float = 20F):
    GameObject(bitmap)
{
    private var player: Player = player
    private final val damageToPlayer: Int = 5
    private final val collideDistance: Int = (w!! + h!!) / 6

    private final val index = index
    private final val maxBarrier = maxBarrier
    private final var incrementalVelocity = .2F

    init {
        x = ((index + Random.nextDouble(.3, .6)) * screenWidth.toFloat()
            / maxBarrier
                ).toInt()
        y = -1 * (1..3).random() * h!!
        velocityY = veloctity
    }

    override fun update() {
        y += velocityY.toInt()
        if(y >= screenHeight){
            reset()
        }
        isCollided()
    }

    fun isCollided(){
        if(this.getDistanceBetweenObjects(player) < collideDistance){
            player.getDamaged(damageToPlayer)
            reset()
        }
    }

    fun reset(){
        velocityY += incrementalVelocity
        y = -1 * (1..5).random() * h!!
        x =((index + Random.nextDouble(.3, .6)) * screenWidth.toFloat()
                / maxBarrier
                ).toInt()
        indexSelected = (0..bitmap.size - 1).random()
    }


    override fun updateTouch(touch_x: Int, touch_y: Int) {
        TODO("Not yet implemented")
    }
}