package com.rgroup.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.rgroup.R
import com.rgroup.databinding.ActivityWaterbillBinding
import kotlinx.android.synthetic.main.activity_waterbill.*

class WaterBillActivity : BaseActivity(){

    private var ctx : WaterBillActivity = this
    private lateinit var activityWaterbillBinding: ActivityWaterbillBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityWaterbillBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_waterbill)
        initalize()
        setListenr()
    }

    private fun initalize() {
        footer("")
    }

    private fun setListenr() {
        tvPay.setOnClickListener {
            hideSoftKeyboard()
            validate()
        }
    }

    private fun validate() : Boolean {
        val number = etNumber.text.toString()
        val electricity = etElectricity.text.toString().trim()
        val amount = etAmount.text.toString()
        return when {
            number.isEmpty() -> {
                displayToast(resources.getString(R.string.number_error_msg))
                false
            }
            number.length < 8 -> {
                displayToast(resources.getString(R.string.valid_mobile))
                false
            }
            electricity.isEmpty() -> {
                displayToast(resources.getString(R.string.electricity_meter_empty_msg))
                false
            }
            electricity.length < 13 -> {
                displayToast(resources.getString(R.string.electricity_meter_valid_msg))
                false
            }
            amount.isEmpty() -> {
                displayToast(resources.getString(R.string.amount_error_msg))
                false
            }
            etAmount.text.toString() == "." -> {
                displayToast(resources.getString(R.string.amoutn_valid_msg))
                false
            }
            else -> true
        }
    }

}