package com.rgroup.activity

import android.app.Dialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.rgroup.R
import com.rgroup.databinding.ActivityRegisterBinding
import com.rgroup.databinding.CustomDialogWithheaderBinding
import com.rgroup.util.RGroupConstant
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.layout_footer.*


class RegisterAcitvity : BaseActivity(){

    private var ctx : RegisterAcitvity = this
    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var customDialogWithheaderBinding: CustomDialogWithheaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_register)
        setListener()
    }

    private fun setListener() {
        register.setOnClickListener {
            //call the hide soft key board mehtod in base activity
            hideSoftKeyboard()
            // call the validate method
            if(validate()){

            }
        }

        tv_first.setOnClickListener {
            finish()
        }

        tv_third.setOnClickListener {
            // call the exit method
            exitConfirmationDialog()
        }

        tv_sec.setOnClickListener {
            startActivity(Intent(ctx, HelpActivity::class.java))
        }
    }

    // check the empty, number length, pin length, pin match validation on the field
    private fun validate(): Boolean {
        val number = register_number.text.toString().trim()
        val first_name = register_first_name.text.toString().trim()
        val last_name = register_last_name.text.toString().trim()
        val password = register_password.text.toString()
        val confirm_pass = register_confirm_password.text.toString()
        return when {
            number.isEmpty() -> {
                displayToast(resources.getString(R.string.number_error_msg))
                false
            }
            number.length < 8 -> {
                displayToast(resources.getString(R.string.valid_mobile))
                false
            }
            first_name.isEmpty() -> {
                displayToast(resources.getString(R.string.name_error_msg))
                false
            }
            last_name.isEmpty() -> {
                displayToast(resources.getString(R.string.last_name_error_msg))
                false
            }
            password.isEmpty() -> {
                displayToast(resources.getString(R.string.password_error_msg))
                false
            }
            confirm_pass.isEmpty() -> {
                displayToast(resources.getString(R.string.confirm_pass_msg))
                false
            }
            password.length < 6 ->{
                displayToast(resources.getString(R.string.min_password_msg))
                false
            }
            !confirm_pass.equals(password) -> {
                displayToast(resources.getString(R.string.combination_msg))
                false
            }
            else -> true
        }
    }

    private fun exitConfirmationDialog() {
        // infalte the custom layout
        customDialogWithheaderBinding = DataBindingUtil.inflate(LayoutInflater.from(ctx), R.layout.custom_dialog_withheader, null, false)
        customDialogWithheaderBinding.btnNoExit.visibility = View.VISIBLE
        //set the text on button for yes
        customDialogWithheaderBinding.btnYesExit.text = resources.getString(R.string.yes)
        //set the text on button for no
        customDialogWithheaderBinding.btnNoExit.text = resources.getString(R.string.no)
        //set the text and ask to user for exit the app message
        customDialogWithheaderBinding.textExit.text = resources.getString(R.string.exit_msg)

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

        // handle the yes button click and redirect to the login screen and clear the all cache .
        customDialogWithheaderBinding.btnYesExit.setOnClickListener {
            dialog.dismiss()
            (0 until RGroupConstant.EXITACTIVITIES.size)
                    .filter { RGroupConstant.EXITACTIVITIES[it] != null }
                    .forEach { RGroupConstant.EXITACTIVITIES[it]?.finish() }
            finish()
        }

        // show the dialog
        dialog.show()
    }
}