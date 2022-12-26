package com.example.mygame.gameobject

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas

abstract class GameObject(var bitmap: Array<Bitmap>) {
    var x: Int = 0
    var y: Int = 0
    protected var velocityX: Float = 0F
    protected var velocityY: Float = 0F

    final var w: Int? = null
    final var h: Int? = null

    protected final var screenWidth =
        Resources.getSystem().displayMetrics.widthPixels
    protected final var screenHeight =
        Resources.getSystem().displayMetrics.heightPixels
    protected var indexSelected: Int = 0

    init {
        w = bitmap[0].width
        h = bitmap[0].height
    }

    open fun draw(canvas: Canvas){
        canvas.drawBitmap(bitmap[indexSelected],
            x.toFloat(), y.toFloat(), null)
    }


    abstract fun update();
    abstract fun updateTouch(touch_x: Int, touch_y: Int);
    fun getDistanceBetweenObjects(other_obj: GameObject): Int {
        return Math.sqrt(
            Math.pow((x - other_obj.x).toDouble(), 2.0) +
            Math.pow((y - other_obj.y).toDouble(), 2.0)
        ).toInt()
    }
}