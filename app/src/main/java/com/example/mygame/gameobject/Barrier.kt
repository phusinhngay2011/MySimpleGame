package com.example.mygame.gameobject

import android.graphics.Bitmap
import kotlin.random.Random

class Barrier(img: Bitmap, player: Player, index: Int, maxBarrier: Int, veloctity: Float = 20F):
    GameObject(img)
{
    private var player: Player = player
    private final val damageToPlayer: Int = 5
    private final val collideDistance: Int = (w!! + h!!) / 6

    private final val index = index
    private final val maxBarrier = maxBarrier
    private final var incrementalVelocity = .2F

    var resetImg = false
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
        y = -1 * (1..3).random() * h!!

        x =((index + Random.nextDouble(.3, .6)) * screenWidth.toFloat()
                / maxBarrier
                ).toInt()
        resetImg = true
    }
    fun changeImg(newImg: Bitmap){
        if(resetImg){
            resetImg = false
            img = newImg
            h = img.height
            w = img.width
        }
    }

    override fun updateTouch(touch_x: Int, touch_y: Int) {
        TODO("Not yet implemented")
    }
}