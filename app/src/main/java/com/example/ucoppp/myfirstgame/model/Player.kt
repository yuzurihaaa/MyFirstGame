package com.example.ucoppp.myfirstgame.model

import android.content.Context
import android.graphics.BitmapFactory
import com.example.ucoppp.myfirstgame.R

class Player(private val context: Context) {

    val playerBitmap by lazy {
        BitmapFactory.decodeResource(context.resources, R.drawable.player)
    }

    var playerX: Float = 75f

    var playerY: Float = 50f

    var speed: Float = 50f

    var boosting: Boolean = false

    //Gravity Value to add gravity effect on the ship
    private val GRAVITY = -10

    //Controlling Y coordinate so that ship won't go outside the screen
    private val maxY: Float = 0f
    private val minY: Float = 0f

    //Limit the bounds of the ship's speed
    private val MIN_SPEED: Float = 1f
    private val MAX_SPEED:Float = 20f

    init {

        //setting the boosting value to false initially
        boosting = false

    }

    fun update() {
        setBoosting()

        setSpeed()
    }

    fun setSpeed() {
        if (speed > MAX_SPEED) {
            speed = MAX_SPEED
        }

        if (speed < MIN_SPEED) {
            speed = MIN_SPEED
        }

        playerY -= speed + GRAVITY

        if (playerY < minY) {
            playerY = minY;
        }
        if (playerY > maxY) {
            playerY = maxY;
        }
    }

    fun setBoosting() {
        //if the ship is boosting
        if (boosting) {
            //speeding up the ship
            speed += 2
        } else {
            //slowing down if not boosting
            speed -= 5
        }
    }
}