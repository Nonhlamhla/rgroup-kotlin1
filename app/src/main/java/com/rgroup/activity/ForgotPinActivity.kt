package com.rgroup.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import com.rgroup.R
import com.rgroup.databinding.ActivityForgotBinding
import com.teacherapp.util.ConnectionDetector
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_forgot.*
import kotlinx.android.synthetic.main.back_footer.*
import moe.codeest.rxsocketclient.SocketSubscriber
import java.util.*

class ForgotPinActivity : BaseActivity(){


    private var ctx : ForgotPinActivity = this
    private lateinit var activityForgotBinding: ActivityForgotBinding
    private val MESSAGE_STR = "Hi 860933037173516|18300"
    private lateinit var ref: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityForgotBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_forgot)
        initalize()
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

    private fun initalize() {

    }

    private fun setListener() {
        iv_back_icon.setOnClickListener {
            finish()
        }

        submit.setOnClickListener {
            sentData(MESSAGE_STR)
        }
    }

    fun connect() {
        //connect
        ref = mClient.connect()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SocketSubscriber() {
                    override fun onConnected() {
                        //onConnected
                        Log.e("IP Address", mClient.mSocket.inetAddress.toString() + "")
                        Log.e("Port", mClient.mSocket.port.toString() + "")
                        Log.e("Connected", "onConnected")
                    }

                    override fun onDisconnected() {
                        //onDisconnected
                        Log.e("Dis connected", "onDisconnected")
                    }

                    override fun onResponse(data: ByteArray) {
                        //receive data
                        val rs = ByteArray(data.size - 2)
                        System.arraycopy(data, 2, rs, 0, data.size - 2)
                        val str = String(rs)
                        Log.e("Rx Message", str)
                        Log.e("Rx Byte Array", Arrays.toString(data))
                        displayToast("Output  "+str)
                    }
                }, Consumer { throwable ->
                    //onError
                    Log.e("Error", throwable.toString())
                })
    }



    fun sentData(message_str : String) {
        if(ConnectionDetector.isConnected(ctx)){
            if(mClient.isConnecting()){
                val oMsg = stringToBytesASCII(message_str)
                val bLen = ByteArray(2)
                bLen[0] = java.lang.Byte.parseByte(Integer.toHexString(0), 16)
                bLen[1] = java.lang.Byte.parseByte(Integer.toHexString(message_str.length + 2), 16)
                Log.e("Message byte array  ", Arrays.toString(oMsg))
                val MESSAGE = Combine(bLen, oMsg)
                Log.e("Message byte array  ", Arrays.toString(MESSAGE))
                mClient.sendData(MESSAGE)
            }else{
                connect()
            }
        }else{
            displayToast(resources.getString(R.string.no_internet_connection))
        }

    }

}