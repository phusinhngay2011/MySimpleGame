package com.example.mygame.gamepanel

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class GameOver(context: Context) {
    private final var endGame:String = "Game Over"
    private final var paint = Paint()
    private final var PAINT_COLOR = Color.RED
    private final var PAINT_SIZE = 150F

    init{
        paint.setColor(PAINT_COLOR)
        paint.textSize = PAINT_SIZE
        paint.setTextAlign(Paint.Align.CENTER)
    }

    fun draw(canvas: Canvas){
        val locationX =
            canvas.width.toFloat() / 2
        val locationY =
            canvas.height / 2 - (paint.descent() + paint.ascent())/2
        canvas.drawText(endGame, locationX, locationY, paint)
    }

}