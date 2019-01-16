package com.rgroup.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.rgroup.R
import com.rgroup.databinding.ActivityBalanceEnquiryBinding

class BalanceEnquiryActivity : BaseActivity(){

    private var ctx : BalanceEnquiryActivity = this
    private lateinit var balanceEnquiryBinding: ActivityBalanceEnquiryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        balanceEnquiryBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_balance_enquiry)
    }
}