package com.rgroup.activity

import android.content.Context
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.TextView
import com.rgroup.R
import com.rgroup.databinding.ActivityRicaBinding
import kotlinx.android.synthetic.main.activity_rica.*

class RicaActivity : BaseActivity(){

    private var ctx : RicaActivity = this
    private lateinit var activityRicaBinding: ActivityRicaBinding
    private lateinit var network_list : ArrayList<String>
    private lateinit var country_list : ArrayList<String>
    private lateinit var networkInflator: LayoutInflater
    private lateinit var countryInflator: LayoutInflater
    private var select_network_id: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRicaBinding = DataBindingUtil.setContentView(ctx, R.layout.activity_rica)
        initalize()
        setListenr()
    }

    private fun initalize() {
        footer("")
        network_list = ArrayList()
        country_list = ArrayList()
        networkInflator = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        countryInflator = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        createNetworlList()
        countryList()
        network_spinner.adapter = networkSpinnerAdapter
        country.adapter = countrySpinnerAdapter
    }

    private fun setListenr() {
        submit.setOnClickListener {
            hideSoftKeyboard()
            validate()
        }

        sim_register_option_group.setOnCheckedChangeListener { group, checkId ->
            if(R.id.radioSouthAfrican == checkId){
                layAfricanIDBook.visibility = View.VISIBLE
                layPassport.visibility = View.GONE
            }else{
                layAfricanIDBook.visibility = View.GONE
                layPassport.visibility = View.VISIBLE
            }
        }

        network_spinner.onItemSelectedListener = networkSelectedListener
    }

    private fun createNetworlList(){
        network_list.add(0, resources.getString(R.string.choose_network))
        network_list.add(1, resources.getString(R.string.vodacom))
        network_list.add(2, resources.getString(R.string.mtn))
        network_list.add(3, resources.getString(R.string.cell))
        network_list.add(4, resources.getString(R.string.beta))
        network_list.add(5, resources.getString(R.string.telkom))
        network_list.add(6, resources.getString(R.string.world_call))
        network_list.add(7, resources.getString(R.string.neotel))
        network_list.add(8, resources.getString(R.string.virgin))
        network_list.add(9, resources.getString(R.string.unipin))
    }

    private fun countryList(){
        country_list.add(0, "Country")
        country_list.add(1, "Brazil")
        country_list.add(2, "France")
        country_list.add(3, "India")
        country_list.add(4, "Italy")
        country_list.add(5, "Malaysia")
        country_list.add(6, "Monaco")
        country_list.add(7, "Qatar")
        country_list.add(8, "Samoa")
        country_list.add(9, "Turkey")
    }

    private val networkSelectedListener = object : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
            hideSoftKeyboard()
            if(network_spinner.selectedItem != resources.getString(R.string.choose_network)){
                when(network_spinner.selectedItem){
                    resources.getString(R.string.vodacom) -> select_network_id = "1"
                    resources.getString(R.string.mtn) -> select_network_id = "2"
                    resources.getString(R.string.cell) -> select_network_id = "3"
                    resources.getString(R.string.beta) -> select_network_id = "4"
                    resources.getString(R.string.telkom) -> select_network_id = "5"
                    resources.getString(R.string.world_call) -> select_network_id = "6"
                    resources.getString(R.string.neotel) -> select_network_id = "7"
                    resources.getString(R.string.virgin) -> select_network_id = "8"
                    resources.getString(R.string.unipin) -> select_network_id = "9"
                }
            }else{
                select_network_id = ""
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>) {}
    }

    private fun validate() : Boolean{
        val last_digits_sim = last_digits_sim.text.toString()
        val full_sim = full_sim_card_number.text.toString()
        val african_id = african_id_number.text.toString()
        val first_name = first_name.text.toString().trim()
        val last_name = last_name.text.toString().trim()
        val street = street_address.text.toString().trim()
        val town = town_city.text.toString().trim()
        val postal = postal_code.text.toString().trim()
        return when {
            select_network_id.isEmpty() -> {
                displayToast(resources.getString(R.string.network_validation_msg))
                false
            }
            last_digits_sim.isEmpty() -> {
                displayToast(resources.getString(R.string.last_sim_empty_msg))
                false
            }
            full_sim.isEmpty() -> {
                displayToast(resources.getString(R.string.full_sim_empty_msg))
                false
            }
            african_id.isEmpty() -> {
                displayToast(resources.getString(R.string.africa_id_empty_msg))
                false
            }
            first_name.isEmpty() -> {
                displayToast(resources.getString(R.string.first_name_empty_msg))
                false
            }
            last_name.isEmpty() -> {
                displayToast(resources.getString(R.string.last_name_empty_msg))
                false
            }
            street.isEmpty() -> {
                displayToast(resources.getString(R.string.street_empty_msg))
                false
            }
            town.isEmpty() -> {
                displayToast(resources.getString(R.string.town_city_empty_msg))
                false
            }
            postal.isEmpty() -> {
                displayToast(resources.getString(R.string.postal_code_empty_msg))
                false
            }
            else -> true
        }
    }

    private val networkSpinnerAdapter = object : BaseAdapter() {

        private var ftext: TextView? = null

        override fun getView(position: Int, _convertView: View?, parent: ViewGroup): View {
            var convertView = _convertView
            if (convertView == null) {
                convertView = networkInflator.inflate(R.layout.row_spinner, parent, false)
            }
            ftext = convertView!!.findViewById(R.id.spinnerTarget) as TextView
            ftext!!.text = network_list[position]
            return convertView
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return network_list[position]
        }

        override fun getCount(): Int {
            return network_list.size
        }

        override fun getDropDownView(position: Int, _convertView: View?,
                                     parent: ViewGroup): View {
            var convertView = _convertView
            if (convertView == null) {
                convertView = networkInflator.inflate(
                        R.layout.row_spinner_drop_down, parent, false)
            }
            ftext = convertView!!.findViewById(R.id.spinnerTargetdrop) as TextView
            ftext!!.text = network_list[position]
            return convertView as View
        }
    }

    private val countrySpinnerAdapter = object : BaseAdapter() {

        private var ftext: TextView? = null

        override fun getView(position: Int, _convertView: View?, parent: ViewGroup): View {
            var convertView = _convertView
            if (convertView == null) {
                convertView = countryInflator.inflate(R.layout.row_spinner, parent, false)
            }
            ftext = convertView!!.findViewById(R.id.spinnerTarget) as TextView
            ftext!!.text = country_list[position]
            return convertView
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getItem(position: Int): Any {
            return country_list[position]
        }

        override fun getCount(): Int {
            return country_list.size
        }

        override fun getDropDownView(position: Int, _convertView: View?,
                                     parent: ViewGroup): View {
            var convertView = _convertView
            if (convertView == null) {
                convertView = countryInflator.inflate(
                        R.layout.row_spinner_drop_down, parent, false)
            }
            ftext = convertView!!.findViewById(R.id.spinnerTargetdrop) as TextView
            ftext!!.text = country_list[position]
            return convertView as View
        }
    }
}