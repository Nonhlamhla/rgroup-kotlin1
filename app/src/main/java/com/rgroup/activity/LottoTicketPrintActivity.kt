package com.rgroup.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import com.rgroup.R
import com.rgroup.databinding.ActivityLottoTicketPrintBinding

class LottoTicketPrintActivity : BaseActivity(){

    private var ctx : LottoTicketPrintActivity = this
    private lateinit var activityLottoTicketPrintBinding: ActivityLottoTicketPrintBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLottoTicketPrintBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_lotto_ticket_print)
        initalize()
        setListenr()
    }

    private fun initalize() {
        footer("")
    }

    private fun setListenr() {

    }
}