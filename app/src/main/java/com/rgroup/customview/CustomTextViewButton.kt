package com.rechargeapp.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

class CustomTextViewButton(context: Context, attrs: AttributeSet) : TextView(context, attrs) {

    init {
        val face = Typeface.createFromAsset(context.assets, "font/Roboto-Light.ttf")
        this.typeface = face
    }
}
