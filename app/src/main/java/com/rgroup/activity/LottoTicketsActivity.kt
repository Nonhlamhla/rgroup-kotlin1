package com.rgroup.activity

import android.app.Dialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import com.rgroup.R
import com.rgroup.adapter.LottoTicketAdapter
import com.rgroup.databinding.ActivityLottoTicketsBinding
import com.rgroup.databinding.CustomDialogWithheaderBinding
import com.rgroup.pojo.LottoPojo
import kotlinx.android.synthetic.main.activity_lotto_tickets.*

class LottoTicketsActivity : BaseActivity(), View.OnClickListener {

    private var ctx : LottoTicketsActivity = this
    private lateinit var activityLottoTicketsBinding: ActivityLottoTicketsBinding
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var adapter: LottoTicketAdapter
    private lateinit var lotto_list : ArrayList<LottoPojo>
    private lateinit var random_number_list : ArrayList<Int>
    private lateinit var customDialogWithheaderBinding: CustomDialogWithheaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLottoTicketsBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_lotto_tickets)
        initalize()
        setListenr()
    }

    // initialize the fields
    private fun initalize() {
        //set the footer
        footer("")
        //initialize the array list
        lotto_list = ArrayList()
        // initialize the random number list
        random_number_list = ArrayList()
        // set the value in from random number method in base activity
        random_number_list = randomNumber()
        // initialize the lotto pojo class
        val lotto_pojo  = LottoPojo()
        //set the random number list in lotto pojo varible for getting in the previous line
        lotto_pojo.random_number_list = random_number_list
        // add the pojo class to the local list
        lotto_list.add(lotto_pojo)
        // initalize the Linear layout managet for recycler view
        mLayoutManager = LinearLayoutManager(this)
        // set the layout manager to recycler list view
        rv_lotto_tickets.layoutManager = mLayoutManager
        // set the value in adapter (class referenece and random number list)
        adapter = LottoTicketAdapter(ctx, lotto_list)
        // set the adapter in recycler list view
        rv_lotto_tickets.adapter = adapter
    }

    private fun setListenr() {
        lotto_ticket_add.setOnClickListener {
            println("lotto ticket size     "+lotto_list.size)
            if(lotto_list.size >= 9){
                displayToast(resources.getString(R.string.lotto_max_msg))
            }else{
                random_number_list = ArrayList()
                random_number_list = randomNumber()
                var lotto_pojo  = LottoPojo()
                lotto_pojo.random_number_list = random_number_list
                lotto_list.add(lotto_pojo)
                adapter = LottoTicketAdapter(ctx, lotto_list)
                rv_lotto_tickets.adapter = adapter
            }
        }

        buy.setOnClickListener {
            startActivity(Intent(ctx, LottoTicketPrintActivity::class.java))
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            when (view.id) {
                R.id.lotto_ticket_delete -> deleteLottoTicket(view)
            }
        }

    }

    private fun deleteLottoTicket(view : View?){
        val pos = view!!.getTag(R.string.lotto_ticket_position)
        println("position is   "+pos)
        println("Lotto List size   "+lotto_list.size)
        deleteDialog(pos as Int)

    }

    // call the delete dialog for the user the lotto ticket message confirmation.

    private fun deleteDialog(pos : Int) {
        // infalte the custom layout
        customDialogWithheaderBinding = DataBindingUtil.inflate(LayoutInflater.from(ctx), R.layout.custom_dialog_withheader, null, false)
        customDialogWithheaderBinding.btnNoExit.visibility = View.VISIBLE
        //set the text on button for yes
        customDialogWithheaderBinding.btnYesExit.text = resources.getString(R.string.yes)
        //set the text on button for no
        customDialogWithheaderBinding.btnNoExit.text = resources.getString(R.string.no)
        //set the text and ask to user for delete lotto tickets
        customDialogWithheaderBinding.textExit.text = resources.getString(R.string.delete_lotto_ticket_msg)

        // set dialog for no title show in top
        val dialog = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
        // set view on dialog
        dialog.setContentView(customDialogWithheaderBinding.root)
        // cancelation set false touch on outside the dialog
        dialog.setCanceledOnTouchOutside(false)

        // dialog on dissmiss click on no button
        customDialogWithheaderBinding.btnNoExit.setOnClickListener {
            dialog.dismiss()
        }

        // handle the yes button click and delete the lotto ticket and reset the adapter again and remove the ticket delete in the list

        customDialogWithheaderBinding.btnYesExit.setOnClickListener {
            // dismiss the dialog
            dialog.dismiss()
            // check the condition int list only 1 lotto ticket they can't remove (max 9 and min 1) lotto ticket in the list
            //lotto list not equal one then enter the condition and remove the ticket and reset the adapter, and false part call the display taost.
            if(lotto_list.size != 1){
                // delete the lotto ticket at this selected position by the user.
                lotto_list.remove(lotto_list[pos])
                // adapter reset again.
                adapter.notifyDataSetChanged()
            }else{
                displayToast(resources.getString(R.string.lotto_min_msg))
            }
        }
        // show the dialog
        dialog.show()
    }

}