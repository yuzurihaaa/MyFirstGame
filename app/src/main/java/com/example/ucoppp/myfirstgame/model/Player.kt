package com.example.ucoppp.myfirstgame.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ucoppp.myfirstgame.R

class Player(private val context: Context, screenX: Int, screenY: Int) {

    val playerBitmap: Bitmap by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.player)
    }

    var playerX: Float = 75f

    var playerY: Float = 50f

    var speed: Float = 0f

    var boosting: Boolean = false

    //Gravity Value to add gravity effect on the ship
    private val gravity = -10

    //Controlling Y coordinate so that ship won't go outside the screen
    private var maxY: Float = 0f
    private var minY: Float = 0f

    //Limit the bounds of the ship's speed
    private val minSpeed: Float = 1f
    private val maxSpeed: Float = 20f

    init {
        maxY = (screenY - playerBitmap.height).toFloat()

        //top edge's y point is 0 so min y will always be zero
        minY = 0f

        //setting the boosting value to false initially
        boosting = false

    }

    fun update() {
        checkBoosting()

        setSpeed()

    }

    private fun setSpeed() {
        if (speed > maxSpeed) {
            speed = maxSpeed
        }

        if (speed < minSpeed) {
            speed = minSpeed
        }

        playerY -= speed + gravity

        if (playerY < minY) {
            playerY = minY
        }
        if (playerY > maxY) {
            playerY = maxY
        }
    }

    private fun checkBoosting() {
        if (boosting) speed += 2 else speed -= 5
    }


    fun stopBoosting() {
        boosting = false
    }

    fun setBoosting() {
        boosting = true
    }
}