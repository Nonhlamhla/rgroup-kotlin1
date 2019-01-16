package com.rgroup.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.rgroup.R
import com.rgroup.databinding.ActivityElectricityBinding
import kotlinx.android.synthetic.main.activity_electricity.*

class ElectiricityActivity : BaseActivity(){

    private var ctx : ElectiricityActivity = this
    private lateinit var activityElectricityBinding: ActivityElectricityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityElectricityBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_electricity)
        initalize()
        setListenr()
    }

    private fun initalize() {
        // set the footer functionnality
        footer("")
    }

    // handle the click listener on field
    private fun setListenr() {
        // click on pay button
        tvPay.setOnClickListener {
            // hide the soft input keyboard
            hideSoftKeyboard()
            // call the validae method
            validate()
        }
    }

    // check the field value and return the toast message
    private fun validate(): Boolean {
        //get the number value in enter by user and store in local variable
        val number = etNumber.text.toString()
        // get the eletricity value in enter by user and store in local variable
        val electricity = etElectricity.text.toString().trim()
        // get the amount value in enter by user and store in local variable
        val amount = etAmount.text.toString()
        return when {
            //check the number is empty
            number.isEmpty() -> {
                // display the toast message
                displayToast(resources.getString(R.string.number_error_msg))
                // it's field is empty return the false
                false
            }
            // check the number length is less than 8
            number.length < 8 -> {
                // display the toast message
                displayToast(resources.getString(R.string.valid_mobile))
                // it's field is less than to 8 then return the false
                false
            }
            // check the electricity meter number is empty
            electricity.isEmpty() -> {
                // display the toast message
                displayToast(resources.getString(R.string.electricity_meter_empty_msg))
                // it's field is empty then return the false
                false
            }
            // check the electricity meter nuber is less than 13
            electricity.length < 13 -> {
                // display the toast message
                displayToast(resources.getString(R.string.electricity_meter_valid_msg))
                // it's field is less than to 13 then return the false
                false
            }
            // check amount field is empty
            amount.isEmpty() -> {
                // display the toast message
                displayToast(resources.getString(R.string.amount_error_msg))
                // it's field is empty then return the false
                false
            }
            // check the amount is equal to dot
            etAmount.text.toString() == "." -> {
                // display the toast message
                displayToast(resources.getString(R.string.amoutn_valid_msg))
                // it's field is store the only dot then return the false
                false
            }
            // all conditions are true then return true
            else -> true
        }
    }
}