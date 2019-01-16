package com.rgroup.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.rgroup.R
import com.rgroup.databinding.ActivityLoginBinding
import com.rgroup.util.RGroupConstant.EXITACTIVITIES
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity() {

    private var ctx: LoginActivity = this
    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_login)
        initalize()
        setListener()
    }

    private fun initalize() {
        // add the open activity in array list define in the constant file
        EXITACTIVITIES.add(ctx)
    }


    private fun setListener() {
        // redirect to the Register Activity
        tvCreateAcc.setOnClickListener {
            startActivity(Intent(ctx, RegisterAcitvity::class.java))
        }

        login.setOnClickListener {
            //call the hide soft key board mehtod in base activity
            hideSoftKeyboard()
            startActivity(Intent(ctx, MainActivity::class.java))
            finish()
            /*if(validate()){

            }*/
        }

        tvForgotPin.setOnClickListener {
            startActivity(Intent(ctx, ForgotPinActivity::class.java))
        }

        google_plus.setOnClickListener {

        }

        facebook.setOnClickListener {

        }

        twitter.setOnClickListener {

        }

        // set the visibility visible and gone in image view

        uncheck_cell_number.setOnClickListener {
            check_cell_number.visibility = View.VISIBLE
            uncheck_cell_number.visibility = View.GONE
        }

        // set the visibility visible and gone in image view

        check_cell_number.setOnClickListener {
            check_cell_number.visibility = View.GONE
            uncheck_cell_number.visibility = View.VISIBLE
        }

        // set the visibility visible and gone in image view

        uncheck_pin.setOnClickListener {
            check_pin.visibility = View.VISIBLE
            uncheck_pin.visibility = View.GONE
        }

        // set the visibility visible and gone in image view

        check_pin.setOnClickListener {
            check_pin.visibility = View.GONE
            uncheck_pin.visibility = View.VISIBLE
        }
    }

    // check the empty validation on the field

    private fun validate(): Boolean {
        val number = number.text.toString().trim()
        val password = login_password.text.toString()
        return when {
            number.isEmpty() -> {
                displayToast(resources.getString(R.string.number_error_msg))
                false
            }
            password.isEmpty() -> {
                displayToast(resources.getString(R.string.password_error_msg))
                false
            }
            else -> true
        }
    }
}