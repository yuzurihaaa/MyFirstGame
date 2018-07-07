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

    fun update() {
        //updating x coordinate
        playerX += 1
    }
}