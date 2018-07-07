package com.example.ucoppp.myfirstgame.ui

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.Log
import android.view.MotionEvent
import android.view.SurfaceView
import com.example.ucoppp.myfirstgame.model.Player


class GameView(context: Context?) : SurfaceView(context), Runnable {

    // boolean variable to track if the game is playing or not
    @Volatile
    var playing: Boolean = false

    // the game thread
    private val gameThread by lazy { Thread(this) }

    //adding the player to this class
    private val player by lazy { Player(context!!) }

    //These objects will be used for drawing
    private val paint by lazy { Paint() }

    private var canvas: Canvas? = null

    private val surfaceHolder by lazy { holder }

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
    }

    private fun draw() {
        //  Here we will draw the characters to the canvas.

        // 1. checking if surface is valid
        if (surfaceHolder.surface.isValid) {
            // For some reason I can't use lazy for lockCanvas()
            canvas = surfaceHolder.lockCanvas()

            canvas?.let(this::setUpCanvas)

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
                player.playerBitmap,
                player.playerX,
                player.playerY,
                paint)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event!!.action and MotionEvent.ACTION_MASK){
            MotionEvent.ACTION_UP -> {}
            MotionEvent.ACTION_DOWN -> {}
            else -> {}
        }

        return true
    }
}