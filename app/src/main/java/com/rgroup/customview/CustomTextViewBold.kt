package com.rechargeapp.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by arpit.jain on 2/14/2018.
 */
class CustomTextViewBold(context: Context, attrs: AttributeSet) : TextView(context, attrs) {

    init {
        val face = Typeface.createFromAsset(context.assets, "font/Roboto-Regular.ttf")
        this.setTypeface(face, Typeface.BOLD)
    }
}