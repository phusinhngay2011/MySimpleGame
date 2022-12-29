package com.example.mygame.gamepanel

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import com.example.mygame.gameobject.Player

class HealthBar(player: Player){
    private var player = player
    private var x: Float = 0F
    private var y: Float = 0F
    private var w: Int = player.w!!
    private var h: Int = 30

    // Left, Right, Bottom, Top
    private var fullHealthBarRec = arrayOf(0F,0F,0F,0F)
    private var healthBarRec = arrayOf(0F,0F,0F,0F)
    private final val marginBottom = 20

    private var paintFullHealth = Paint()
    private var paintHealth = Paint()

    init {
        paintHealth.setColor(Color.GREEN)
        paintFullHealth.setColor(Color.LTGRAY)
    }
    fun draw(canvas: Canvas){
        canvas.drawRect(fullHealthBarRec[0],
                        fullHealthBarRec[3],
                        fullHealthBarRec[1],
                        fullHealthBarRec[2],
                        paintFullHealth)

        canvas.drawRect(healthBarRec[0],
                        healthBarRec[3],
                        healthBarRec[1],
                        healthBarRec[2],
                        paintHealth)
    }

    fun update(){
        x = player.x.toFloat()
        y = player.y.toFloat()

        var percent = player.getHealthPercentage() * w / 100
        fullHealthBarRec[0] = x
        fullHealthBarRec[1] = x + w
        fullHealthBarRec[2] = y - marginBottom
        fullHealthBarRec[3] = fullHealthBarRec[2] - h

        Log.d("Health bar", percent.toString())
        healthBarRec[0] = fullHealthBarRec[0]
        healthBarRec[1] = healthBarRec[0] + percent
        healthBarRec[2] = fullHealthBarRec[2]
        healthBarRec[3] = fullHealthBarRec[3]
    }
}