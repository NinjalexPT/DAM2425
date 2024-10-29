package com.example.gametest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView



class GameView : SurfaceView, Runnable{

    var playing = false
    var gameThread: Thread? = null
    lateinit var surfaceHolder : SurfaceHolder
    lateinit var canvas : Canvas


    lateinit var paint : Paint
    var stars = arrayListOf<Star>()


    private fun init(context: Context, width: Int, height: Int){
        star = Star(width, height)
        paint = Paint()
        surfaceHolder = holder
    }

    constructor(context: Context?, width: Int, height: Int) : super(context){
        init(context!!, width, height)
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs){
        init(context!!, 0, 0)
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init(context!!, 0, 0)
    }

    fun resume() {
        playing = true
        gameThread = Thread(this)
        gameThread?.start()
    }

    fun pause() {
        playing = false
        gameThread?.join()
    }

    override fun run(){
        while(playing){
            update()
            draw()
            control()
        }
    }

    fun update(){
        for (s in stars){
            s.update()
        }

    }

    fun draw(){
        if(surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()

            canvas.drawColor(Color.BLACK)

            paint.color = Color.YELLOW

            for(star in stars){
            canvas.drawPoint(star.x.toFloat(), star.y.toFloat(), paint)
            }

            paint.strokeWidth = 5.0f
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control(){
        Thread.sleep(17) }
    }


