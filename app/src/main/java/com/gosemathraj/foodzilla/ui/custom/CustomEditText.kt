package com.gosemathraj.foodzilla.ui.custom

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText

class CustomEditText : AppCompatEditText {

    constructor(context: Context) : super(context){
        setTypeFace()
    }

    constructor(context: Context, attrs : AttributeSet) : super(context,attrs){
        setTypeFace()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr : Int) : super(context, attrs, defStyleAttr){
        setTypeFace()
    }

    fun setTypeFace() {
        val typeface = Typeface.createFromAsset(context.assets, "font/FredokaOne_Regular.ttf")
        setTypeface(typeface)
    }
}