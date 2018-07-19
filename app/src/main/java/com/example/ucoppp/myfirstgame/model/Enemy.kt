package com.example.ucoppp.myfirstgame.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ucoppp.myfirstgame.R
import java.util.*


class Enemy(context: Context, screenX: Int, screenY: Int) {
    //bitmap for the enemy
    //we have already pasted the bitmap in the drawable folder
    var bitmap: Bitmap? = null

    //x and y coordinates
    var x: Int = 0
    var y: Int = 0

    //enemy speed
    private var speed = 1

    //min and max coordinates to keep the enemy inside the screen
    private var maxX: Int = 0
    private var minX: Int = 0

    private var maxY: Int = 0
    private var minY: Int = 0

    var generator = Random()

    init {
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.enemy)

        //initializing min and max coordinates
        maxX = screenX
        maxY = screenY
        minX = 0
        minY = 0

        speed = generator.nextInt(6) + 10
        x = screenX
        y = generator.nextInt(maxY) - bitmap!!.height
    }

    fun update(playerSpeed: Int) {
        //decreasing x coordinate so that enemy will move right to left
        x -= playerSpeed
        x -= speed
        //if the enemy reaches the left edge
        if (x < minX - bitmap!!.width) {
            //adding the enemy again to the right edge
            val generator = Random()
            speed = generator.nextInt(10) + 10
            x = maxX
            y = generator.nextInt(maxY) - bitmap!!.height
        }
    }
}