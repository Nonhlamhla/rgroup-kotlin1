package com.rechargeapp.customview


import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.EditText

@SuppressLint("AppCompatCustomView")
/**
 * Created by upasna.mishra on 11/24/2017.
 */
class CustomEditText(context: Context, attrs: AttributeSet) : EditText(context, attrs) {
    init {
        val face = Typeface.createFromAsset(context.assets, "font/Roboto-Regular.ttf")
        this.typeface = face
    }
}