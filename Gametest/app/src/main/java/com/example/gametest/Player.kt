package com.example.gametest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Player {
    var x = 0
    var y = 0
    var speed = 0
    var maxX = 0
    var maxY = 0
    var minX = 0
    var minY = 0

    var bitmap : Bitmap

    var boosting = false

    private val Gravity = -10
    private val MAX_SPEED = 20
    private val MIN_SPEED = 1

    var detectCollisions : Rect


    constructor(context: Context, width: Int, height: Int){

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)

        minX = 0
        maxX = width

        minY = 0
        maxY = height - bitmap.height

        x = 75
        y = 50

        speed = 1

        detectCollisions = Rect(x, y, bitmap.width, bitmap.height)
    }

    fun update() {

        if (boosting) {
            speed += 2
        } else {
            speed -= 5
        }
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED
        }
        if (speed < MIN_SPEED) {
            speed = MIN_SPEED
        }

        y -= speed + Gravity

        if(y<minY)y = minY
        if(y>maxY)y = maxY

        detectCollisions.left = x
        detectCollisions.top = y
        detectCollisions.right = x + bitmap.width
        detectCollisions.bottom = y + bitmap.height
    }
}