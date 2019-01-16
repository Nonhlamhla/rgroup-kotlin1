package com.rechargeapp.customview

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView

/**
 * Created by upasna.mishra on 11/24/2017.
 */
class CustomTextView(context: Context, attrs: AttributeSet) : TextView(context, attrs) {

    init {
        val face = Typeface.createFromAsset(context.assets, "font/Roboto-Regular.ttf")
        this.typeface = face
    }
}
