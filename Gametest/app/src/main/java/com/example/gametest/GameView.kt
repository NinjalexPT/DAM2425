package com.example.gametest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect

import android.os.Handler
import android.os.Looper

import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView : SurfaceView, Runnable{

    var playing = false
    var gameThread: Thread? = null
    lateinit var surfaceHolder : SurfaceHolder
    lateinit var canvas : Canvas

    var Score = 0


    // Variável para armazenar o estado dos toques
    private val activeTouches = SparseArray<Boolean>()


    lateinit var paint : Paint
    lateinit var paint2 : Paint
    var stars = arrayListOf<Star>()
    lateinit var player : Player
    var enemies = arrayListOf<Enemy>()
    lateinit var boom : Boom

    var bullets = arrayListOf<Bullet>()
    val bulletstoremove = mutableListOf<Bullet>()

    var lives = 3

    var onGameOver : () -> Unit = {}

    private fun init(context: Context, width: Int, height: Int){
        surfaceHolder = holder
        paint = Paint()
        paint2 = Paint()



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

        for (bullet in bulletstoremove){
            bullets.remove(bullet)

        }

        for (bullet in bullets) {
            bullet.update()
        }




        for (e in enemies){
            e.update(player.speed)
            if(Rect.intersects(e.detectCollisions, player.detectCollisions)){

                boom.x = e.x
                boom.y = e.y
                e.x = -600
                lives -= 1


            }
            for (bullet in bullets) {
                if (Rect.intersects(e.detectCollisions, bullet.detectCollisions)) {

                    boom.x = e.x
                    boom.y = e.y
                    e.x = -600
                    bulletstoremove.add(bullet)
                    Score += 1
                }

                if(bullet.x > width){
                    bulletstoremove.add(bullet)
                }
            }

        }





    }

    fun draw(){
        if(surfaceHolder.surface.isValid){
            canvas = surfaceHolder.lockCanvas()

            canvas.drawColor(Color.BLACK)

            paint.color = Color.YELLOW
            paint2.color = Color.WHITE
            paint2.textSize = 60f


            for(star in stars){
            canvas.drawPoint(star.x.toFloat(), star.y.toFloat(), paint)
            }


            canvas.drawBitmap(player.bitmap, player.x.toFloat(), player.y.toFloat(), paint)

            canvas.drawBitmap(boom.bitmap, boom.x.toFloat(), boom.y.toFloat(), paint)

            for(e in enemies){
                canvas.drawBitmap(e.bitmap, e.x.toFloat(), e.y.toFloat(), paint)
            }

            for (bullet in bullets) {
                canvas.drawBitmap(bullet.bitmap, bullet.x.toFloat(), bullet.y.toFloat(), paint)
            }

            canvas.drawText("Score: ${Score}", 10f, 60f, paint2)

            canvas.drawCircle(width-(width/10f),height-(height/10f)*2,(height/10f)*1.5f,paint2)

            paint.strokeWidth = 5.0f
            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }
    var callGameOverOnce = false
    fun control(){
        Thread.sleep(17)
        if (lives == 0 ){
            playing = false
            Handler(Looper.getMainLooper()).post {
                if (!callGameOverOnce) {
                    onGameOver()
                    callGameOverOnce = true
                }
                gameThread?.join()
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        event?.let {
            val pointerCount = event.pointerCount


            when (event?.actionMasked) {

                MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                    val index = event.actionIndex
                    val pointerId = event.getPointerId(index)
                    val x = event.getX(index)
                    val y = event.getY(index)


                    if (x > width - (width / 10f) - (height / 10f) * 1.5f
                        && x < width - (width / 10f) + (height / 10f) * 1.5f
                        && y > height - (height / 10f) * 2 - (height / 10f) * 1.5f
                        && y < height - (height / 10f) + (height / 10f) * 1.5f
                    ) {

                        bullets.add(
                            Bullet(
                                context,
                                player.x + player.bitmap.width,
                                player.y + player.bitmap.height / 2,
                                width,
                                height
                            )
                        )
                        activeTouches.put(pointerId, false)


                    } else {
                        player.boosting = true
                        activeTouches.put(pointerId,true)
                    }
                }
                MotionEvent.ACTION_MOVE -> {
                    for (i in 0 until pointerCount) {
                        val pointerId = event.getPointerId(i)
                        val isBoosting = activeTouches[pointerId] ?: false

                        if (isBoosting) {
                            // Atualiza a posição ou estado, se necessário
                            player.boosting = true
                        }
                    }
                }

                MotionEvent.ACTION_UP,MotionEvent.ACTION_POINTER_UP -> {
                    val index = event.actionIndex
                    val pointerId = event.getPointerId(index)
                    activeTouches.remove(pointerId)


                    if (activeTouches.size() == 0)
                    player.boosting = false
                }
            }
        }
        return true
    }
}





