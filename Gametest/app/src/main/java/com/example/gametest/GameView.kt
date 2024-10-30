package com.example.gametest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView



class GameView : SurfaceView, Runnable{

    var playing = false
    var gameThread: Thread? = null
    lateinit var surfaceHolder : SurfaceHolder
    lateinit var canvas : Canvas



    lateinit var paint : Paint
    var stars = arrayListOf<Star>()
    lateinit var player : Player
    var enemies = arrayListOf<Enemy>()
    lateinit var boom : Boom


    private fun init(context: Context, width: Int, height: Int){
        surfaceHolder = holder
        paint = Paint()

        for (i in 0..100){
            stars.add(Star(width, height))
        }

        player = Player(context, width, height)

        for (i in 0..2){
            enemies.add(Enemy(context, width, height))
        }
        boom = Boom(context, width, height)
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

        boom.x = -300
        boom.y = -300


        for (s in stars){
            s.update(player.speed)
        }
        player.update()
        for (e in enemies){
            e.update(player.speed)
            if(Rect.intersects(e.detectCollisions, player.detectCollisions)){

                boom.x = e.x
                boom.y = e.y
                e.x = -300

            }
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


            canvas.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)

            canvas.drawBitmap(boom.bitmap, boom.x.toFloat(), boom.y.toFloat(), paint)

            for(e in enemies){
                canvas.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
            }

            paint.strokeWidth = 5.0f
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    fun control(){
        Thread.sleep(17) }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                player.boosting = true
            }

            MotionEvent.ACTION_UP -> {
                player.boosting = false
            }
        }

        return true
    }
}





