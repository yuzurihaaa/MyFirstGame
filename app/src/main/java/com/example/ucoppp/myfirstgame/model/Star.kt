package com.example.ucoppp.myfirstgame.model

import java.util.*

class Star(screenX: Int, screenY: Int) {

    private var maxX: Int = 0
    private var maxY: Int = 0
    private var minX: Int = 0
    private var minY: Int = 0
    var x: Int = 0
    var y: Int = 0
    private var speed: Int = 0

    val generator = Random()

    init {
        maxX = screenX
        maxY = screenY

        speed = generator.nextInt(10)

        // generating a random coordinate
        // but keeping the coordinate inside the screen size
        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)
    }

    fun update(playerSpeed: Double) {
        //animating the star horizontally left side
        //by decreasing x coordinate with player speed
        x -= playerSpeed.toInt()
        x -= speed

        //if the star reached the left edge of the screen
        if (x < 0) {
            //again starting the star from right edge
            //this will give a infinite scrolling background effect
            x = maxX
            y = generator.nextInt(maxY)

            speed = generator.nextInt(15)
        }
    }

    fun getStarWidth(): Float {
        //Making the star width random so that
        //it will give a real look
        val minX = 1.0f
        val maxX = 4.0f
        return generator.nextFloat() * (maxX - minX) + minX
    }
}