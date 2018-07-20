package com.example.ucoppp.myfirstgame.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import com.example.ucoppp.myfirstgame.model.Boom
import com.example.ucoppp.myfirstgame.model.Enemy
import com.example.ucoppp.myfirstgame.model.Player
import com.example.ucoppp.myfirstgame.model.Star

class GameView(context: Context?) : SurfaceView(context), Runnable {

    // boolean variable to track if the game is playing or not
    @Volatile
    var playing: Boolean = false

    // the game thread
    private val gameThread by lazy { Thread(this) }

    //adding the player to this class
    private var player: Player? = null

    //These objects will be used for drawing
    private val paint by lazy { Paint() }

    private var canvas: Canvas? = null

    private val surfaceHolder by lazy { holder }

    //Adding an stars list
    private val stars = ArrayList<Star>()

    //Adding 3 enemies you may increase the size
    private var enemyCount = 3

    //Adding enemies object array
    private var enemies: Array<Enemy>? = null

    private var boom: Boom? = null

    constructor(context: Context?, screenX: Int, screenY: Int) : this(context) {

        player = Player(context!!, screenX, screenY)

        Array(100) { it }.map {
            stars.add(Star(screenX, screenY))
        }

        enemies = Array(3) { Enemy(context, screenX, screenY) }
        for (i in 0 until enemyCount) {
            enemies!![i] = Enemy(context, screenX, screenY)
        }

        boom = Boom(context)
    }

    override fun run() {
        while (playing) {
            //to update the frame
            update()

            //to draw the frame
            draw()

            //to control
            control()
        }
    }

    private fun update() {
        // Here we will update the coordinate of our characters.
        player?.update()

        //setting boom outside the screen
        boom!!.x = -250
        boom!!.y = -250

        stars.forEach {
            it.update(player!!.speed.toDouble())
        }

        enemies!!.map {
            it.update(player!!.speed.toInt())

            if (Rect.intersects(player!!.detectCollision, it.detectCollision)) {

                boom!!.x = it.x
                boom!!.y = it.y
                it.x = -200
            }
        }
    }

    private fun draw() {
        //  Here we will draw the characters to the canvas.

        // 1. checking if surface is valid
        if (surfaceHolder.surface.isValid) {
            // For some reason I can't use lazy for lockCanvas()
            canvas = surfaceHolder.lockCanvas()
            canvas?.let(this::setUpCanvas)
            paint.color = Color.WHITE

            stars.forEach {
                paint.strokeWidth = it.getStarWidth()
                canvas!!.drawPoint(it.x.toFloat(), it.y.toFloat(), paint)
            }

            enemies!!.map {
                canvas!!.drawBitmap(
                        it.bitmap,
                        it.x.toFloat(),
                        it.y.toFloat(),
                        paint
                )
            }

            //drawing boom image
            canvas!!.drawBitmap(
                    boom!!.bitmap,
                    boom!!.x.toFloat(),
                    boom!!.y.toFloat(),
                    paint
            )

            surfaceHolder.unlockCanvasAndPost(canvas)
        }
    }

    private fun control() {
        // This method will control the frames per seconds drawn.
        // Here we are calling the delay method of Thread.
        // And this is actually making our frame rate to aroud 60fps.
        // After these we have two more methods.
        try {
            Thread.sleep(17)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }

    fun onPause() {
        // To pause the game, we are stopping the gameThread here.
        playing = false
        try {
            //stopping the thread
            gameThread.apply {
            }
        } catch (e: InterruptedException) {
            Log.e("Game thread onPause", e.localizedMessage)
        }

    }

    fun onResume() {
        // To resume the game, here we are starting the gameThread.
        playing = true
        gameThread.start()
    }

    private fun setUpCanvas(canvas: Canvas) {
        canvas.drawColor(Color.BLACK)
        //Drawing the player
        canvas.drawBitmap(
                player?.playerBitmap,
                player?.playerX!!,
                player?.playerY!!,
                paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_UP -> {
                player?.setBoosting()
            }
            MotionEvent.ACTION_DOWN -> {
                player?.stopBoosting()
            }
            else -> {
            }
        }

        return true
    }
}