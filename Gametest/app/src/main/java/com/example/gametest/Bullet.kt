package com.example.gametest


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

class Bullet{
    var x = 0
    var y = 0
    var speed = 25 // Velocidade da bala


    var bitmap: Bitmap // Crie uma imagem para a bala

    var detectCollisions : Rect

    constructor(context: Context, startX: Int, startY: Int){

        x = startX
        y = startY

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.bullet).let{
            Bitmap.createScaledBitmap(it, 60, 40, false)
        }
        detectCollisions = Rect(x, y, bitmap.width, bitmap.height)


    }



    fun update() {
        x += speed

        detectCollisions.left = x
        detectCollisions.top = y
        detectCollisions.right = x + bitmap.width
        detectCollisions.bottom = y + bitmap.height
    }
}