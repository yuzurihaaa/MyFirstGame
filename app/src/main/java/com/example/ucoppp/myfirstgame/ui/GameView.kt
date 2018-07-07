package com.example.ucoppp.myfirstgame.ui

import android.content.Context
import android.util.Log
import android.view.SurfaceView

class GameView(context: Context?) : SurfaceView(context), Runnable {

    // boolean variable to track if the game is playing or not
    @Volatile
    var playing: Boolean = false

    // the game thread
    private val gameThread by lazy {
        Thread(this)
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
    }

    private fun draw() {
        //  Here we will draw the characters to the canvas.
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

}