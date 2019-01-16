package com.rgroup.activity

import android.app.ProgressDialog
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.rgroup.R
import com.rgroup.databinding.ActivityAirtimeBinding
import com.rgroup.util.RGroupDialog
import com.teacherapp.util.ConnectionDetector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_airtime.*
import moe.codeest.rxsocketclient.SocketSubscriber
import java.util.*

class AirtimeActivity : BaseActivity() {

    private var ctx : AirtimeActivity = this
    private lateinit var activityAirtimeBinding: ActivityAirtimeBinding
    private var select_service : Int = 0
    private val MESSAGE_STR = "Hi 860933037173516|18300"
    private val MESSAGE_STR_TWO = "Airtime clc10|1|1234|U|20111214|004528|0|"
    private lateinit var ref: Disposable
    private var getting_resp : Boolean = true
    private var getting_two_response : Boolean = false
    private lateinit var d : ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set the layout
        activityAirtimeBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_airtime)
        //call initialize method
        init()
        // call set Listener method
        setListener()
    }

    override fun onResume() {
        super.onResume()
        if(ConnectionDetector.isConnected(ctx)){
            connect()
        }else{
            displayToast(resources.getString(R.string.no_internet_connection))
        }
    }


    private fun init() {
        // get the select data purchase value through the intent
        select_service = intent.getIntExtra("select_service", 0)
        //call set the network style method and pass the two parameter first for select network, and second header title
        setNetworkStyle(select_service, resources.getString(R.string.airtime))

        // set the text value and visibility according to select network service
        // check the condition for select service value select value 1 for vodacon 2 for mtn
        // 3 for cell c, 4 for telkom, 5 for neotel and 6 for virgin mobile
        when(select_service){
            1 -> {
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.VISIBLE
                lin_three.visibility = View.VISIBLE
                airtime_eleveen.visibility = View.INVISIBLE
                airtime_twelve.visibility = View.INVISIBLE
                sms_text.visibility = View.VISIBLE
                text_airtime_one.text = "R2"
                text_airtime_two.text = "R5"
                text_airtime_three.text = "R10"
                text_airtime_four.text = "R12"
                text_airtime_five.text = "R29"
                text_airtime_six.text = "R49"
                text_airtime_seven.text = "R55"
                text_airtime_eight.text = "R110"
                text_airtime_nine.text = "R275"
                text_airtime_ten.text = "R1000"
            }
            2 -> {
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.VISIBLE
                lin_three.visibility = View.GONE
                airtime_eight.visibility = View.INVISIBLE
                sms_text.visibility = View.GONE
                text_airtime_one.text = "R2"
                text_airtime_two.text = "R5"
                text_airtime_three.text = "R10"
                text_airtime_four.text = "R15"
                text_airtime_five.text = "R30"
                text_airtime_six.text = "R60"
                text_airtime_seven.text = "R180"
                // click listener call
                text_airtime_one.setOnClickListener {
                    // call the commana for
                    sentData(MESSAGE_STR)

                }
            }
            3 -> {
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.VISIBLE
                lin_three.visibility = View.VISIBLE
                airtime_eleveen.visibility = View.VISIBLE
                airtime_twelve.visibility = View.INVISIBLE
                sms_text.visibility = View.GONE
                text_airtime_one.text = "R5"
                text_airtime_two.text = "R10"
                text_airtime_three.text = "R25"
                text_airtime_four.text = "R35"
                text_airtime_five.text = "R39"
                text_airtime_six.text = "R50"
                text_airtime_seven.text = "R70"
                text_airtime_eight.text = "R150"
                text_airtime_nine.text = "R200"
                text_airtime_ten.text = "R300"
                text_airtime_eleveen.text = "R500"
            }
            4 -> {
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.VISIBLE
                lin_three.visibility = View.GONE
                airtime_eight.visibility = View.INVISIBLE
                sms_text.visibility = View.GONE
                text_airtime_one.text = "R5"
                text_airtime_two.text = "R10"
                text_airtime_three.text = "R20"
                text_airtime_four.text = "R30"
                text_airtime_five.text = "R50"
                text_airtime_six.text = "R100"
                text_airtime_seven.text = "R250"
            }
            5 -> {
                setNetworkStyle(select_service, resources.getString(R.string.neotel))
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.GONE
                lin_three.visibility = View.GONE
                text_airtime_one.text = "R25"
                text_airtime_two.text = "R50"
                text_airtime_three.text = "R100"
                text_airtime_four.text = "R200"
            }
            6 -> {
                setNetworkStyle(select_service, resources.getString(R.string.virgin))
                lin_one.visibility = View.VISIBLE
                lin_two.visibility = View.VISIBLE
                lin_three.visibility = View.GONE
                airtime_six.visibility = View.INVISIBLE
                airtime_seven.visibility = View.INVISIBLE
                airtime_eight.visibility = View.INVISIBLE
                text_airtime_one.text = "R5"
                text_airtime_two.text = "R10"
                text_airtime_three.text = "R20"
                text_airtime_four.text = "R30"
                text_airtime_five.text = "R50"
            }
        }
    }

    private fun setListener() {

    }

    fun connect() {
        //connect
        ref = mClient.connect()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SocketSubscriber() {
                    override fun onConnected() {
                        //onConnected
                        println("IP Address"+ mClient.mSocket.inetAddress.toString() + "")
                        println("Port"+ mClient.mSocket.port.toString() + "")
                        println("Connected"+ "onConnected")
                    }

                    override fun onDisconnected() {
                        //onDisconnected
                        println("Dis connected"+ "onDisconnected")
                    }

                    override fun onResponse(data: ByteArray) {
                        //receive data
                        val rs = ByteArray(data.size - 2)
                        System.arraycopy(data, 2, rs, 0, data.size - 2)
                        val str = String(rs)
                        println("geeting reposne value "+getting_resp)
                        if(getting_resp){
                            d.dismiss()
                            sentData(MESSAGE_STR_TWO)
                            getting_resp = false
                        }
                        println("Rx Message"+ str)
                        if(str != null){
                            d.dismiss()
                            val separate1 = str.split(" ".toRegex())
                            val ok_string = separate1[0]
                            println("ok string is   "+ok_string)
                            if(ok_string == "Ok"){
                                // call the activity
                                startActivity(Intent(ctx, PrintActivity::class.java).putExtra("print_mesg", str))
                            }
                        }
                        println("Rx Byte Array"+ Arrays.toString(data))
                    }
                }, Consumer { throwable ->
                    //onError
                    println("Error"+ throwable.toString())
                })
    }



    fun sentData(message_str : String) {
        if(ConnectionDetector.isConnected(ctx)){
             d = RGroupDialog.showLoading(this)
            d.setCanceledOnTouchOutside(false)
            if(mClient.isConnecting()){
                val oMsg = stringToBytesASCII(message_str)
                val bLen = ByteArray(2)
                bLen[0] = java.lang.Byte.parseByte(Integer.toHexString(0), 16)
                bLen[1] = java.lang.Byte.parseByte(Integer.toHexString(message_str.length + 2), 16)
                println("Message byte array  "+ Arrays.toString(oMsg))
                val MESSAGE = Combine(bLen, oMsg)
                println("Message byte array  "+ Arrays.toString(MESSAGE))
                mClient.sendData(MESSAGE)
            }else{
                connect()
            }
        }else{
            displayToast(resources.getString(R.string.no_internet_connection))
        }

    }
}