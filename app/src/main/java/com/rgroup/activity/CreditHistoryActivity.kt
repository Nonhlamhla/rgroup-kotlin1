package com.rgroup.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.rgroup.R
import com.rgroup.databinding.ActivityCreditHistoryBinding
import kotlinx.android.synthetic.main.activity_credit_history.*
import java.text.SimpleDateFormat
import java.util.*

class CreditHistoryActivity : BaseActivity() {

    private var ctx : CreditHistoryActivity = this
    private lateinit var creditHistoryBinding: ActivityCreditHistoryBinding
    private var select_service : Int = 0
    private lateinit var calendar: Calendar
    private var mHour: Int = 0
    private var mMinute: Int = 0
    private var year: Int = 0
    private var month: Int = 0
    private var day: Int = 0
    private var selected_data_from: String = ""
    private var selected_time_from: String = ""
    private var selected_data_to: String = ""
    private var selected_time_to: String = ""
    private var sdf: SimpleDateFormat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //set the layout
        creditHistoryBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_credit_history)
        // call the init method
        init()
        // call the set listener method
        setListener()
    }

    private fun init() {
        //set the time format in
        sdf = SimpleDateFormat(ctx.resources.getString(R.string.timeformat), Locale.US)
        // get the select data purchase value through the intent
        select_service = intent.getIntExtra("select_service", 0)
        // set the title and visible the view according the select service
        // select service 1 for airtime and 2 for credit history in report activity screen click
        when(select_service){
            1 -> {
                header_title.text = resources.getString(R.string.airtime)
                lin_last_deposite.visibility = View.GONE
            }
            2 -> {
                header_title.text = resources.getString(R.string.credit_history)
                lin_last_deposite.visibility = View.VISIBLE
            }
        }
        // initalize the calendar instance
        calendar = Calendar.getInstance()
        // set current hour in varialbe
        mHour = calendar.get(Calendar.HOUR_OF_DAY)
        //set the current minute in variable
        mMinute = calendar.get(Calendar.MINUTE)
        //set the current year in variable
        year = calendar.get(Calendar.YEAR)
        //set the current month in variable
        month = calendar.get(Calendar.MONTH)
        //set the current month of day in variable
        day = calendar.get(Calendar.DAY_OF_MONTH)
    }

    // set listener from date and time
    // to date and time

    private fun setListener() {
        // click listener on get report
        get_report.setOnClickListener {
            // call the validate method
            if(validate()){
                // after validate call your work
            }
        }

        from_date.setOnClickListener {
            // call show date picker dialog and pass the two parameter one for text view for set the value
            // andd one for selected date set in variable
            showDatePicker(from_date, 1)
        }

        from_time.setOnClickListener {
            // call show time picker dialog and pass the two parameter one for text view for set the value
            // andd one for selected date set in variable
            showTimePicker(from_time, 3)
        }

        to_date.setOnClickListener {
            // call show date picker dialog and pass the two parameter one for text view for set the value
            // andd one for selected date set in variable
            showDatePicker(to_date, 2)
        }

        to_time.setOnClickListener {
            // call show time picker dialog and pass the two parameter one for text view for set the value
            // andd one for selected date set in variable
            showTimePicker(to_time, 4)
        }
    }

    //date picker Dialog open

    private fun showDatePicker(dateText: TextView, selected_date : Int) {
        val dialog = DatePickerDialog(ctx, DatePickerDialog.OnDateSetListener { _, year, month, day ->
            calendar.set(year, month, day)
            this.year = year
            this.month = month
            this.day = day
            //call update label method for set the select date on text view
            updateLabel(dateText, selected_date)
        }, year, month, day)
        dialog.datePicker.minDate = System.currentTimeMillis() - 1000
        dialog.show()
    }

    // set the selected date on text view
    private fun updateLabel(dateTxt: TextView, setdateinvariable : Int) {
        // set the format show the user
        val myFormat = ctx.resources.getString(R.string.dateformat)//In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        // set the value in variable for select from and to date
        // and check the condition for 1 to set the from date and for 2 to set the to date
        if(setdateinvariable == 1){
            selected_data_from = sdf.format(calendar.time)
        }else{
            selected_data_to = sdf.format(calendar.time)
        }

        println("selected time is   " + selected_data_from)
        // set the date in text view
        dateTxt.text = sdf.format(calendar.time)
    }


    // show the time picker dialog and the set the value in text view
    private fun showTimePicker(timeText: TextView, selectTime : Int) {

        val timePickerDialog = TimePickerDialog(ctx,
                TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
                    val time = sdf!!.parse(hourOfDay.toString() + ":" + minute)
                    println("time format  " + sdf!!.format(time))
                    // set the value in variable for select from and to time
                    // and check the condition for 3 to set the from time and for 4 to set the to time
                    if(selectTime == 3){
                        selected_time_from = sdf!!.format(time) + ":00"
                    }else{
                        selected_time_to = sdf!!.format(time) + ":00"
                    }
                    timeText.text = ctx.changeDateTimeFormat(hourOfDay.toString() + ":" + minute)
                }, mHour, mMinute, false)
        timePickerDialog.show()
    }

    // check the from date and time equal blank then show the error message and return false other wise return true
    // check the to date and time equal blank then show the error message and return false other wise return true

     private fun validate() : Boolean{
         if(selected_data_from == ""){
             displayToast(resources.getString(R.string.from_date_empty_msg))
             return false
         }else if(selected_time_from == ""){
             displayToast(resources.getString(R.string.from_time_empty_msg))
             return false
         }else if(selected_data_to == ""){
             displayToast(resources.getString(R.string.to_date_empty_msg))
             return false
         }else if(selected_time_to == ""){
             displayToast(resources.getString(R.string.to_time_empty_msg))
             return false
         }else{
             return true
         }
     }
}