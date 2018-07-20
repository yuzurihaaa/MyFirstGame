package com.example.ucoppp.myfirstgame.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.ucoppp.myfirstgame.R


class Boom(context: Context) {

    var bitmap: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.boom)

    var x: Int = -250
    var y: Int = -250
}