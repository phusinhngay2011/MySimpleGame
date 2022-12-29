package com.example.mygame.gameobject

import android.graphics.Bitmap
import android.graphics.Canvas
import android.util.Log
import com.example.mygame.gamepanel.HealthBar

// Main character

class Player(bitmap: Array<Bitmap>): GameObject(bitmap) {
    private final var MAX_HEALTH = 100
    private var health = MAX_HEALTH
    private var healthBar: HealthBar? = HealthBar(this)
    private var SPEED = 2

    init {
        x = screenWidth / 2
        y = screenHeight / 2
        healthBar!!.update()
    }

    override fun update(){
        healthBar!!.update()
        indexSelected = (indexSelected + SPEED) % bitmap.size
    }

    override fun updateTouch(touch_x: Int, touch_y: Int) {
        x = touch_x - w!! / 2
        y = touch_y - h!! / 2

        healthBar!!.update()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        healthBar!!.draw(canvas)
    }
    fun getHealthPercentage(): Int{
        //Log.d("Health bar", health.toString())
        return health * 100 / MAX_HEALTH
    }

    fun getDamaged(damage: Int){
        health = Math.max(health - damage, 0)
    }

}