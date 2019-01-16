package com.rgroup.activity

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import com.rgroup.R
import com.rgroup.databinding.ActivityDataBinding
import kotlinx.android.synthetic.main.activity_data.*

class DataActivity : BaseActivity(){

    private var ctx : DataActivity = this
    private lateinit var dataBinding: ActivityDataBinding
    private var select_service : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set the layout
        dataBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_data)
        //call initialize method
        init()

    }

    private fun init() {
        // get the select data purchase value through the intent
        select_service = intent.getIntExtra("select_service", 0)
        //call set the network style method and pass the two parameter first for select network, and second header title
        setNetworkStyle(select_service, resources.getString(R.string.data))
        //call the method according the select service in setStyle method
        setStyleSelectSerivce()
    }

    private fun setStyleSelectSerivce(){
        println("select service in data activity is   "+select_service)
        // set the text value according to select network service
        // set the reports value click on report button
        // check the condition for select service value select value 1 for vodacon 2 for mtn
        // 3 for cell c, 4 for telkom and 7 for reports
        when(select_service){
            1 -> {
                data_bundle_one.text = resources.getString(R.string.power_bundle)
                data_bundle_one.visibility = View.VISIBLE
                data_bundle_two.visibility = View.GONE
                data_bundle_three.visibility = View.GONE
                data_bundle_four.visibility = View.GONE
                // click listener on data bundle

                // handle the click listener on all network bundle
                data_bundle_one.setOnClickListener {
                    // call the activity and pass the two parameter one for select network and 2nd for header value
                    startActivity(Intent(ctx, DoubleBundleActivity::class.java).putExtra("select_service", 1)
                            .putExtra("header_value", "Data/Power").putExtra("sub_select_service", 0))
                }

            }
            2 -> {
                data_bundle_one.text = resources.getString(R.string.day_night_bundles)
                data_bundle_two.text = resources.getString(R.string.monthly_bundle)
                data_bundle_one.visibility = View.VISIBLE
                data_bundle_two.visibility = View.VISIBLE
                data_bundle_three.visibility = View.GONE
                data_bundle_four.visibility = View.GONE
                // click listener on data bundle

                // handle the click listener on all network bundle
                data_bundle_one.setOnClickListener {
                    // call the activity and pass the two parameter one for select network and 2nd for header value
                    startActivity(Intent(ctx, DoubleBundleActivity::class.java).putExtra("select_service", 2)
                            .putExtra("header_value", "Daily/Night").putExtra("sub_select_service", 0))
                }

                // handle the click listener on all network bundle
                data_bundle_two.setOnClickListener {
                    // call the activity and pass the two parameter one for select network and 2nd for header value
                    startActivity(Intent(ctx, SingleBundleActivity::class.java).putExtra("select_service", 2)
                            .putExtra("header_value", data_bundle_two.text.toString()))
                }
            }
            3 -> {
                data_bundle_one.text = resources.getString(R.string.data_bundle)
                data_bundle_two.text = resources.getString(R.string.all_in_one)
                data_bundle_one.visibility = View.VISIBLE
                data_bundle_two.visibility = View.VISIBLE
                data_bundle_three.visibility = View.GONE
                data_bundle_four.visibility = View.GONE
                // click listener on data bundle

                // handle the click listener on all network bundle
                data_bundle_one.setOnClickListener {
                    // call the activity and pass the two parameter one for select network and 2nd for header value
                    startActivity(Intent(ctx, SingleBundleActivity::class.java).putExtra("select_service", 3)
                            .putExtra("header_value", data_bundle_one.text.toString()))
                }

                // handle the click listener on all network bundle
                data_bundle_two.setOnClickListener {
                    // call the activity and pass the two parameter one for select network and 2nd for header value
                    startActivity(Intent(ctx, DoubleBundleActivity::class.java).putExtra("select_service", 3)
                            .putExtra("header_value", "All-in-1/shout-Out").putExtra("sub_select_service", 0))
                }
            }
            4 -> {
                data_bundle_one.text = resources.getString(R.string.free_me_bundle)
                data_bundle_two.text = resources.getString(R.string.daily_weekend_bundle)
                data_bundle_three.text = resources.getString(R.string.all_network_bundle)
                data_bundle_one.visibility = View.VISIBLE
                data_bundle_two.visibility = View.VISIBLE
                data_bundle_three.visibility = View.VISIBLE
                data_bundle_four.visibility = View.GONE
                // click listener on data bundle

                // handle the click listener on all network bundle
                data_bundle_one.setOnClickListener {
                    // call the activity and pass the two parameter one for select network and 2nd for header value
                    startActivity(Intent(ctx, DoubleBundleActivity::class.java).putExtra("select_service", 4)
                            .putExtra("header_value", "Free-Me/Hourly").putExtra("sub_select_service", 1))
                }

                // handle the click listener on all network bundle
                data_bundle_two.setOnClickListener {
                    // call the activity and pass the two parameter one for select network and 2nd for header value
                    startActivity(Intent(ctx, DoubleBundleActivity::class.java).putExtra("select_service", 4)
                            .putExtra("header_value", "Daily/Weekend").putExtra("sub_select_service", 2))
                }

                // handle the click listener on all network bundle
                data_bundle_three.setOnClickListener {
                    // call the activity and pass the two parameter one for select network and 2nd for header value
                    startActivity(Intent(ctx, SingleBundleActivity::class.java).putExtra("select_service", 4)
                            .putExtra("header_value", data_bundle_three.text.toString()))
                }
            }
            7 -> {
                //call set the network style method and pass the two parameter first for select network, and second header title
                setNetworkStyle(select_service, resources.getString(R.string.report))
                ivNetworkLogo.visibility = View.GONE
                data_bundle_one.text = resources.getString(R.string.balance_enquiry)
                data_bundle_two.text = resources.getString(R.string.airtime)
                data_bundle_three.text = resources.getString(R.string.credit_history)
                data_bundle_four.text = resources.getString(R.string.re_print)
                data_bundle_one.visibility = View.VISIBLE
                data_bundle_two.visibility = View.VISIBLE
                data_bundle_three.visibility = View.VISIBLE
                data_bundle_four.visibility = View.VISIBLE

                // report cilck listener method

                data_bundle_one.setOnClickListener {
                    // call the Activity
                    startActivity(Intent(ctx, BalanceEnquiryActivity::class.java))
                }

                data_bundle_two.setOnClickListener {
                    // call the Activity and pass the select service value
                    startActivity(Intent(ctx, CreditHistoryActivity::class.java).putExtra("select_service", 1))
                }

                data_bundle_three.setOnClickListener {
                    // call the Activity and pass the select service value
                    startActivity(Intent(ctx, CreditHistoryActivity::class.java).putExtra("select_service", 2))
                }
            }
        }
    }

}