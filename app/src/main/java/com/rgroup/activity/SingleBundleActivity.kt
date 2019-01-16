package com.rgroup.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.rgroup.R
import com.rgroup.databinding.ActivitySingleBundleBinding
import kotlinx.android.synthetic.main.activity_single_bundle.*

class SingleBundleActivity : BaseActivity() {

    private var ctx : SingleBundleActivity = this
    private lateinit var singleBundleBinding: ActivitySingleBundleBinding
    private var select_service : Int = 0
    private var title : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set the layout
        singleBundleBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_single_bundle)
        // call the initialize method
        init()
    }

    private fun init() {
        // get the select networik value through the intent
        select_service = intent.getIntExtra("select_service", 0)
        //get the title value through the intent
        title = intent.getStringExtra("header_value")
        //call set the network style method and pass the two parameter first for select network, and second header title
        setNetworkStyle(select_service, title)
        //call the method according the select service in setStyle method
        setStyleSelectSerivce()
    }

    private fun setStyleSelectSerivce(){
        println("select service in data activity is   "+select_service)
        // set the text value and visibility according to select network service
        // check the condition for select service value select value 2 for mtn
        // 3 for cell c and 4 for telkom
        when(select_service){
            2 -> {
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.VISIBLE
                lin_three.visibility = View.VISIBLE
                airtime_twelve.visibility = View.INVISIBLE
                text_airtime_one.text = "R10"
                data_pack_one.text = "20MB"
                text_airtime_two.text = "R20"
                data_pack_two.text = "50MB"
                text_airtime_three.text = "R29"
                data_pack_three.text = "100MB"
                text_airtime_four.text = "R39"
                data_pack_four.text = "150MB"
                text_airtime_five.text = "R60"
                data_pack_five.text = "300MB"
                text_airtime_six.text = "R99"
                data_pack_six.text = "600MB"
                text_airtime_seven.text = "R120"
                data_pack_seven.text = "750MB"
                text_airtime_eight.text = "R149"
                data_pack_eight.text = "1GB"
                text_airtime_nine.text = "R189"
                data_pack_nine.text = "1.5GB"
                text_airtime_ten.text = "R299"
                data_pack_ten.text = "3GB"
                text_airtime_eleveen.text = "R399"
                data_pack_eleveen.text = "6GB"
            }
            3 -> {
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.VISIBLE
                lin_three.visibility = View.GONE
                airtime_seven.visibility = View.INVISIBLE
                airtime_eight.visibility = View.INVISIBLE
                text_airtime_one.text = "R16"
                data_pack_one.text = "50MB"
                text_airtime_two.text = "R29"
                data_pack_two.text = "100MB"
                text_airtime_three.text = "R65"
                data_pack_three.text = "300MB"
                text_airtime_four.text = "R99"
                data_pack_four.text = "500MB"
                text_airtime_five.text = "R149"
                data_pack_five.text = "1GB"
                text_airtime_six.text = "R299"
                data_pack_six.text = "3GB"
            }
            4 -> {
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.VISIBLE
                lin_three.visibility = View.VISIBLE
                airtime_ten.visibility = View.INVISIBLE
                airtime_eleveen.visibility = View.INVISIBLE
                airtime_twelve.visibility = View.INVISIBLE
                text_airtime_one.text = "R7"
                data_pack_one.text = "25MB"
                text_airtime_two.text = "R13"
                data_pack_two.text = "50MB"
                text_airtime_three.text = "R25"
                data_pack_three.text = "100MB"
                text_airtime_four.text = "R35"
                data_pack_four.text = "250MB"
                text_airtime_five.text = "R70"
                data_pack_five.text = "500MB"
                text_airtime_six.text = "R100"
                data_pack_six.text = "1GB"
                text_airtime_seven.text = "R150"
                data_pack_seven.text = "2GB"
                text_airtime_eight.text = "R200"
                data_pack_eight.text = "3GB"
                text_airtime_nine.text = "R300"
                data_pack_nine.text = "5GB"
            }
        }
    }
}