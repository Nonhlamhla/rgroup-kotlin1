package com.rgroup.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.rgroup.R
import com.rgroup.databinding.ActivityDoubleBundleBinding
import kotlinx.android.synthetic.main.activity_double_bundle.*

class DoubleBundleActivity : BaseActivity(){

    private var ctx : DoubleBundleActivity = this
    private lateinit var doubleBundleBinding: ActivityDoubleBundleBinding
    private var select_service : Int = 0
    private var sub_select_service : Int = 0
    private var title : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set the layout
        doubleBundleBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_double_bundle)
        //call initialize method
        init()
    }

    private fun init() {
        // get the select networik value through the intent
        select_service = intent.getIntExtra("select_service", 0)
        //get the title value through the intent
        title = intent.getStringExtra("header_value")
        //get the value for telkon in two service then sub select servie
        sub_select_service = intent.getIntExtra("sub_select_service", 0)
        //call set the network style method and pass the two parameter first for select network, and second header title
        setNetworkStyle(select_service, title)
        //call the method according the select service in setStyle method
        setStyleSelectSerivce()
    }

    private fun setStyleSelectSerivce(){
        println("select service in data activity is   "+select_service)
        // set the text value and visibility according to select network service
        // check the condition for select service value select value 1 for vodacom 2 for mtn
        // 3 for cell c and 4 for telkom
        when(select_service){
            1 -> {
                bundle_header.text = resources.getString(R.string.data_bundle)
                bundle_header_two.text = resources.getString(R.string.power_bundles)
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.VISIBLE
                lin_three.visibility = View.VISIBLE

                text_airtime_one.text = "R10"
                data_pack_one.text = "15MB"
                text_airtime_two.text = "R12"
                data_pack_two.text = "30MB"
                text_airtime_three.text = "R29"
                data_pack_three.text = "100MB"
                text_airtime_four.text = "R63"
                data_pack_four.text = "250MB"
                text_airtime_five.text = "R100"
                data_pack_five.text = "500MB"
                text_airtime_six.text = "R149"
                data_pack_six.text = "1GB"
                airtime_seven.visibility = View.INVISIBLE
                airtime_eight.visibility = View.INVISIBLE
                text_airtime_nine.text = "R2"
                data_pack_nine.text = "10MB"
                text_airtime_ten.text = "R5"
                data_pack_ten.text = "50MB"
                text_airtime_eleveen.text = "R10"
                data_pack_eleveen.text = "60MB"
                airtime_twelve.visibility = View.INVISIBLE
            }
            2 -> {
                bundle_header.text = resources.getString(R.string.daily_bundles)
                bundle_header_two.text = resources.getString(R.string.night_bundles)
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.GONE
                lin_three.visibility = View.VISIBLE
                airtime_twelve.visibility = View.INVISIBLE
                text_airtime_one.text = "R5"
                data_pack_one.text = "25MB"
                text_airtime_two.text = "R8"
                data_pack_two.text = "50MB"
                text_airtime_three.text = "R50"
                data_pack_three.text = "1GB"
                airtime_four.visibility = View.INVISIBLE
                text_airtime_nine.text = "R110"
                data_pack_nine.text = "1GB"
                airtime_ten.visibility = View.INVISIBLE
                airtime_eleveen.visibility = View.INVISIBLE
                airtime_twelve.visibility = View.INVISIBLE
            }
            3 -> {
                bundle_header.text = resources.getString(R.string.all_in_one_bundles)
                bundle_header_two.text = resources.getString(R.string.shout_out_bundles)
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.VISIBLE
                lin_three.visibility = View.VISIBLE
                airtime_seven.visibility = View.INVISIBLE
                airtime_eight.visibility = View.INVISIBLE
                airtime_six.visibility = View.INVISIBLE
                text_airtime_one.text = "R11"
                data_pack_one.text = "20MB"
                text_airtime_two.text = "R21"
                data_pack_two.text = "45MB"
                text_airtime_three.text = "R49"
                data_pack_three.text = "150MB"
                text_airtime_four.text = "R89"
                data_pack_four.text = "300MB"
                text_airtime_five.text = "R199"
                data_pack_five.text = "750MB"
                text_airtime_nine.text = "R4"
                data_pack_nine.text = "80MB"
                text_airtime_ten.text = "R17"
                data_pack_ten.text = "120MB"
                text_airtime_eleveen.text = "R49"
                data_pack_eleveen.text = "4GB"
                airtime_twelve.visibility = View.INVISIBLE

            }
            4 -> {
                lin_three.visibility = View.VISIBLE
                if(sub_select_service == 1){
                    lin_one.visibility = View.VISIBLE
                    lin_two.visibility = View.VISIBLE
                    bundle_header.text = resources.getString(R.string.free_me_all)
                    bundle_header_two.text = resources.getString(R.string.hourly_all)
                    text_airtime_one.text = "R30"
                    data_pack_one.text = "150MB"
                    text_airtime_two.text = "R40"
                    data_pack_two.text = "250MB"
                    text_airtime_three.text = "R70"
                    data_pack_three.text = "500MB"
                    text_airtime_four.text = "R100"
                    data_pack_four.text = "1GB"
                    text_airtime_five.text = "R150"
                    data_pack_five.text = "2GB"
                    text_airtime_six.text = "R200"
                    data_pack_six.text = "3GB"
                    text_airtime_seven.text = "R300"
                    data_pack_seven.text = "5GB"
                    airtime_eight.visibility = View.INVISIBLE
                    text_airtime_nine.text = "R6"
                    data_pack_nine.text = "75MB"
                    airtime_ten.visibility = View.INVISIBLE
                    airtime_eleveen.visibility = View.INVISIBLE
                    airtime_twelve.visibility = View.INVISIBLE
                }else if(sub_select_service == 2){
                    lin_one.visibility = View.VISIBLE
                    lin_two.visibility = View.GONE
                    bundle_header.text = resources.getString(R.string.daily_all)
                    bundle_header_two.text = resources.getString(R.string.weekend_all)
                    text_airtime_one.text = "R10"
                    data_pack_one.text = "150MB"
                    airtime_two.visibility = View.INVISIBLE
                    airtime_three.visibility = View.INVISIBLE
                    airtime_four.visibility = View.INVISIBLE
                    text_airtime_nine.text = "R40"
                    data_pack_nine.text = "500MB"
                    text_airtime_ten.text = "R60"
                    data_pack_ten.text = "1GB"
                    airtime_eleveen.visibility = View.INVISIBLE
                    airtime_twelve.visibility = View.INVISIBLE
                }

            }
        }
    }
}