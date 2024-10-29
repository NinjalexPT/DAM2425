package com.example.gametest

import java.util.Random


class Star {
    var x = 0
    var y = 0
    var speed = 0
    var maxX = 0
    var maxY = 0

    var minX = 0
    var minY = 0

    val generator = Random()

    constructor(width: Int, height: Int){
        minX = 0
        maxX = width
        minY = 0
        maxY = height



        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)

        speed = generator.nextInt(15) + 1
    }

    fun update(){
        x -= speed

        if(x < 0){
            x = maxX
            y = generator.nextInt(maxY)
            speed = generator.nextInt(15)
        }
    }

    var starwidth : Int = 0
        get(){
            return generator.nextInt(10) + 1
        }

}