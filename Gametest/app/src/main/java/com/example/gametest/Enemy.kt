package com.example.gametest

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.util.Random

class Enemy {



        var x = 0
        var y = 0
        var speed = 0
        var maxX = 0
        var maxY = 0
        var minX = 0
        var minY = 0

        var bitmap : Bitmap

        var boosting = false

        val generator = Random()

        var detectCollisions : Rect


        constructor(context: Context, width: Int, height: Int){

            bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy)

            minX = 0
            maxX = width

            minY = 0
            maxY = height - bitmap!!.height

            x = maxX
            y = generator.nextInt(maxY)

            speed = generator.nextInt(6) + 10

            detectCollisions = Rect(x, y, bitmap.width, bitmap.height)
        }

        fun update(playerSpeed: Int){
            x -= playerSpeed
            x -= speed

            if(x < minX - bitmap.width){
                x = maxX
                y = generator.nextInt(maxY)
                speed = generator.nextInt(15) + 1
            }

            detectCollisions.left = x
            detectCollisions.top = y
            detectCollisions.right = x + bitmap.width
            detectCollisions.bottom = y + bitmap.height
        }
    }
