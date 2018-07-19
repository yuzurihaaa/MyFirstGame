package com.example.ucoppp.myfirstgame.ui

import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class GameActivity : AppCompatActivity() {

    private val gameView by lazy {
        GameView(this, size.x, size.y)
    }

    private val display by lazy {
        windowManager.defaultDisplay
    }

    private val size by lazy {
        Point()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        display.getSize(size)

        setContentView(gameView)
    }

    override fun onResume() {
        super.onResume()
        gameView.onResume()
    }

    override fun onPause() {
        super.onPause()
        gameView.onPause()
    }
}