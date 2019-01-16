package com.rgroup.activity

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.rgroup.R
import com.rgroup.databinding.CustomDialogWithheaderBinding
import com.rgroup.util.RGroupConstant
import kotlinx.android.synthetic.main.activity_select_network.*
import moe.codeest.rxsocketclient.RxSocketClient
import moe.codeest.rxsocketclient.SocketClient
import moe.codeest.rxsocketclient.meta.SocketConfig
import moe.codeest.rxsocketclient.meta.SocketOption
import moe.codeest.rxsocketclient.meta.ThreadStrategy
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern
import kotlin.collections.ArrayList

// It is a base activity they extend in the all class in the app.

open class BaseActivity : AppCompatActivity(){

    // intialize the variable and field use in the base activity

    private val ctx: BaseActivity = this
    private lateinit var customDialogWithheaderBinding: CustomDialogWithheaderBinding
    private lateinit var random_number_list : ArrayList<Int>
    private var number_ava : Boolean = false
    lateinit var mClient: SocketClient
    private val IP = "196.31.118.146"
    private val PORT = 3110
    lateinit var baseApp : BaseApp


    // call the override onCreate method in android for set the activity.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseApp = application as BaseApp
        initialize()
    }

    fun initialize() {
        //initialize the socket client
        mClient = RxSocketClient
                // create the builder
                .create(SocketConfig.Builder()
                        // set the IP address
                        .setIp(IP)
                        // set the port
                        .setPort(PORT)
                        // set the charset utf 8
                        .setCharset(Charsets.UTF_8)
                        .setThreadStrategy(ThreadStrategy.ASYNC)
                        .setTimeout(300 * 1000)
                        .build())
                .option(SocketOption.Builder()
                        .build())
    }

    // convert string to byte ascii value and return the byte array
    fun stringToBytesASCII(str: String): ByteArray {
        // initaliae the b for byte array and define the length for the message
        val b = ByteArray(str.length)
        // for loop to the b size
        for (i in b.indices) {
            // store the value on the b in i position
            b[i] = str[i].toByte()
        }
        // return the b to the variable
        return b
    }

    // combine the two byte array and return the single byte array
    fun Combine(first: ByteArray, second: ByteArray): ByteArray {
        // initialize the byte array legth of the (one +Two) byte array length
        val ret = ByteArray(first.size + second.size)
        // use the method of combine
        System.arraycopy(first, 0, ret, 0, first.size)
        System.arraycopy(second, 0, ret, first.size, second.size)
        // retun the byte array
        return ret
    }

    // generate the random number and set the list

    fun randomNumber() : ArrayList<Int> {
        random_number_list = ArrayList()
        //define a new Random class
        val r = Random()

        //minimum number to generate as random number
        val minNumber = 1

        //maximum number to generate as random number
        val maxNumber = 52

        //get the next random number within range
        // Inclusive both minimum and maximum value

        // check the condition random list size is less than 6. so that loop are called by 6 size
        while (random_number_list.size != 6){
            // check the condition random list size is equal to zero not equal to zero then call the else part
            if(random_number_list.size == 0){
                // generate the random number with in the range of 1 to 52
                val randomNumber = r.nextInt(maxNumber - minNumber + 1) + minNumber
                // add the random number in list
                random_number_list.add(randomNumber)
            }else{
                // generate the random nujmber with in the range of 1 to 52
                val randomNumber = r.nextInt(maxNumber - minNumber + 1) + minNumber
                // generate for loop are 0 to random list of size
                for (i in 0 until random_number_list.size){
                    // check the condition list number and new generate random number are not same set the value are true
                    // same the set the value are false
                    if(randomNumber != random_number_list[i]) {
                        // set the variable is true
                        number_ava = true
                    }else {
                        // set the variable is false
                        number_ava = false
                        // break the loop both number are same
                        break
                    }
                }
                // check local varaible are true the add the number in list
                if(number_ava){
                    // add the number in list
                    random_number_list.add(randomNumber)
                }
            }

        }
        // return the random number list
        return random_number_list
    }

    // display the toast message
    fun displayToast(message: String) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show()
    }


    // hide the soft input keyboard
    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    // it is comman method in all activity. This method set foother click
    fun footer(title: String) {
        val tv_help = findViewById<TextView>(R.id.tv_help)
        val tv_logout = findViewById<TextView>(R.id.tv_logout)
        val iv_back_icon = findViewById<LinearLayout>(R.id.iv_back_icon)


        tv_help.setOnClickListener {
            startActivity(Intent(ctx, HelpActivity::class.java))
        }

        tv_logout.setOnClickListener {
            // call logout functionality in the app
            logoutConfirmationDialog()
        }

        iv_back_icon.setOnClickListener {
            finish()
        }

    }

    fun passCombination(pass: String): Boolean {
        val expression = "(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{6,}\$"
        val pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE)
        val matcher = pattern.matcher(pass)
        return matcher.matches()
    }

    // this method call for logout the app and go to the login screen.

    private fun logoutConfirmationDialog() {
        // infalte the custom layout
        customDialogWithheaderBinding = DataBindingUtil.inflate(LayoutInflater.from(ctx), R.layout.custom_dialog_withheader, null, false)
        customDialogWithheaderBinding.btnNoExit.visibility = View.VISIBLE
        //set the text on button for yes
        customDialogWithheaderBinding.btnYesExit.text = resources.getString(R.string.yes)
        //set the text on button for no
        customDialogWithheaderBinding.btnNoExit.text = resources.getString(R.string.no)
        //set the text and ask to user for logout message
        customDialogWithheaderBinding.textExit.text = resources.getString(R.string.logout_msg)

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
            startActivity(Intent(ctx, LoginActivity::class.java))
            (0 until RGroupConstant.EXITACTIVITIES.size)
                    .filter { RGroupConstant.EXITACTIVITIES[it] != null }
                    .forEach { RGroupConstant.EXITACTIVITIES[it]?.finish() }
            finish()
        }
        // show the dialog
        dialog.show()
    }

    fun setNetworkStyle(select_service : Int, title : String){

        // set the data purhcase logo in image according to get select service value.
        when(select_service){
            1 ->{
                // set logo
                ivNetworkLogo.setImageDrawable(ContextCompat.getDrawable(ctx, R.mipmap.net_logo_1))
                //set header name
                header_title.text = title
                // set header color
                header_title.setTextColor(ContextCompat.getColor(ctx, R.color.red_color))
            }

            2 ->{
                // set logo
                ivNetworkLogo.setImageDrawable(ContextCompat.getDrawable(ctx, R.mipmap.net_logo))
                //set header name
                header_title.text = title
                // set header color
                header_title.setTextColor(ContextCompat.getColor(ctx, R.color.yellow_color))
            }

            3 -> {
                //set logo
                ivNetworkLogo.setImageDrawable(ContextCompat.getDrawable(ctx, R.mipmap.net_logo_2))
                //set header name
                header_title.text = title
                // set header color
                header_title.setTextColor(ContextCompat.getColor(ctx, R.color.orange_color))
            }
            4 -> {
                //set logo
                ivNetworkLogo.setImageDrawable(ContextCompat.getDrawable(ctx, R.mipmap.net_logo_5))
                //set header name
                header_title.text = title
                // set header color
                header_title.setTextColor(ContextCompat.getColor(ctx, R.color.light_blue_color))
            }
            5 -> {
                //set logo
                ivNetworkLogo.setImageDrawable(ContextCompat.getDrawable(ctx, R.mipmap.net_logo_3))
                //set header name
                header_title.text = title
                // set header color
                header_title.setTextColor(ContextCompat.getColor(ctx, R.color.orange_color))
            }
            6 -> {
                //set logo
                ivNetworkLogo.setImageDrawable(ContextCompat.getDrawable(ctx, R.mipmap.net_logo_4))
                //set header name
                header_title.text = title
                // set header color
                header_title.setTextColor(ContextCompat.getColor(ctx, R.color.red_color))
            }

            7 -> {
                //set header name
                header_title.text = title
                // set header color
                header_title.setTextColor(ContextCompat.getColor(ctx, R.color.whiteColor))
            }
        }
    }

    // set the time format show in hh:mm:ss and am, pm
    fun changeDateTimeFormat(time: String): String {
        //get the time format in HH:mm
        val inputFormat = SimpleDateFormat("HH:mm", Locale.US)
        // set the time format in hh:mm:ss a
        val outputFormat = SimpleDateFormat("hh:mm:ss a", Locale.US)
        // convert the date format
        val date = inputFormat.parse(time)
        // return the date
        return outputFormat.format(date)
    }


}