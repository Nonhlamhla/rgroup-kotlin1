package com.rgroup.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.rgroup.R
import com.rgroup.databinding.ActivityOwnAmountBinding
import kotlinx.android.synthetic.main.activity_own_amount.*

class OwnAmountActivity : BaseActivity(){

    private var ctx : OwnAmountActivity = this
    private lateinit var ownAmountBinding: ActivityOwnAmountBinding
    private var select_service : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set the layout
        ownAmountBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_own_amount)
        //call initialize method
        init()
        // call set Listener method
        setListener()
    }

    private fun init() {
        // get the select data purchase value through the intent
        select_service = intent.getIntExtra("select_service", 0)
        //call set the network style method and pass the two parameter first for select network, and second header title
        setNetworkStyle(select_service, resources.getString(R.string.own_amount))
        // set the text in select amount according the network service
        when(select_service){
            1 -> {
                // set the text value and color
                r_amount_one.text = "R5"
                r_amount_two.text = "R12"
                r_amount_three.text = "R29"
                r_amount_four.text = "R55"
                r_amount_one.setTextColor(ContextCompat.getColor(ctx, R.color.red_color))
                r_amount_two.setTextColor(ContextCompat.getColor(ctx, R.color.red_color))
                r_amount_three.setTextColor(ContextCompat.getColor(ctx, R.color.red_color))
                r_amount_four.setTextColor(ContextCompat.getColor(ctx, R.color.red_color))
            }
            2 -> {
                // set the text value and color
                r_amount_one.text = "R2"
                r_amount_two.text = "R10"
                r_amount_three.text = "R15"
                r_amount_four.text = "R30"
                r_amount_one.setTextColor(ContextCompat.getColor(ctx, R.color.yellow_color))
                r_amount_two.setTextColor(ContextCompat.getColor(ctx, R.color.yellow_color))
                r_amount_three.setTextColor(ContextCompat.getColor(ctx, R.color.yellow_color))
                r_amount_four.setTextColor(ContextCompat.getColor(ctx, R.color.yellow_color))
            }
            3 -> {
                // set the text value and color
                r_amount_one.text = "R5"
                r_amount_two.text = "R10"
                r_amount_three.text = "R35"
                r_amount_four.text = "R50"
                r_amount_one.setTextColor(ContextCompat.getColor(ctx, R.color.orange_color))
                r_amount_two.setTextColor(ContextCompat.getColor(ctx, R.color.orange_color))
                r_amount_three.setTextColor(ContextCompat.getColor(ctx, R.color.orange_color))
                r_amount_four.setTextColor(ContextCompat.getColor(ctx, R.color.orange_color))
            }
            4 -> {
                // set the text value and color
                r_amount_one.text = "R5"
                r_amount_two.text = "R10"
                r_amount_three.text = "R30"
                r_amount_four.text = "R50"
                r_amount_one.setTextColor(ContextCompat.getColor(ctx, R.color.light_blue_color))
                r_amount_two.setTextColor(ContextCompat.getColor(ctx, R.color.light_blue_color))
                r_amount_three.setTextColor(ContextCompat.getColor(ctx, R.color.light_blue_color))
                r_amount_four.setTextColor(ContextCompat.getColor(ctx, R.color.light_blue_color))
            }
        }
    }

    private fun setListener() {

    }
}