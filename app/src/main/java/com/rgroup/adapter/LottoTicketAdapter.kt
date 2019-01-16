package com.rgroup.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.rgroup.R
import com.rgroup.activity.LottoTicketsActivity
import com.rgroup.pojo.LottoPojo
import kotlinx.android.synthetic.main.dialog_select_ball.*

@Suppress("DEPRECATION")
class LottoTicketAdapter(val context: Activity, var lotto_ticket_list: ArrayList<LottoPojo>) : RecyclerView.Adapter<LottoTicketAdapter.ViewHolder>() {

    var ctx: LottoTicketsActivity = context as LottoTicketsActivity

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LottoTicketAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_layout_lutto_ticket, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: LottoTicketAdapter.ViewHolder, position: Int) {
        holder.bindItems(lotto_ticket_list[position], ctx, position)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return lotto_ticket_list.size
    }

    //the class is hodling the list view
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(lottoData: LottoPojo, ctx: LottoTicketsActivity, pos : Int) {
            // initalize the fielda
           val row_ticket_number = itemView.findViewById<LinearLayout>(R.id.row_ticket_number)
            val spin = itemView.findViewById<TextView>(R.id.spin)
            val select_ball = itemView.findViewById<TextView>(R.id.select_ball)
            val lotto_ticket_delete = itemView.findViewById<ImageView>(R.id.lotto_ticket_delete)
            // click listener on delete lotto ticket
            lotto_ticket_delete.setOnClickListener(ctx)
            //set positon in the set tag and get the tag in activity
            lotto_ticket_delete.setTag(R.string.lotto_ticket_position, pos)
            //click listener on spin
            spin.setOnClickListener {
                //upadate the random number list on select position and new random number generation and set the number
                lottoData.random_number_list = ctx.randomNumber()
                // call the custom layout and pass the linear layout, class refernece and list
                setNumberTicket(row_ticket_number, ctx, lottoData.random_number_list!!, "spin")
            }
            // call the custom layout and pass the linear layout, class and referneces and random number list
            setNumberTicket(row_ticket_number, ctx, lottoData.random_number_list!!, "")
            // click listener on select ball
            select_ball.setOnClickListener {
                // initalize the new random array list
                val randomNumberList : ArrayList<Int> = ArrayList()
                // show select ball dialog method and pass the class reference, list and position
                showSelectBallDialog(ctx, randomNumberList, pos)
            }
        }

        @SuppressLint("ResourceType")
// call the custom layout for the ball
        fun setNumberTicket(row_ticket_number : LinearLayout, ctx: LottoTicketsActivity, random_number_list : ArrayList<Int>, type : String) {
            // remove the view created at frist
           row_ticket_number.removeAllViews()
            println("random number list   "+random_number_list.size)
            if(random_number_list.size == 0){
                for(j in 0 until 6){
                    // inflate the layout in for the ball
                    val itemView = LayoutInflater.from(ctx).inflate(R.layout.row_layout_random_ticket, null, false)
                    // intialize the Text view field
                    val random_number = itemView.findViewById(R.id.random_number) as TextView

                    if(type == "clear"){
                        val aniRotateClk = AnimationUtils.loadAnimation(ctx, R.anim.rotate_clockwise)
                        random_number.startAnimation(aniRotateClk)
                    }


                    // set the value in text view
                    random_number.text = ""
                    // add the view in the custom layout
                    row_ticket_number.addView(itemView)
                }
            }else{
                // for loop 0 to random number list size
                for (i in 0 until random_number_list.size) {
                    println("random number value "+random_number_list[i])
                    // inflate the layout in for the ball
                    val itemView = LayoutInflater.from(ctx).inflate(R.layout.row_layout_random_ticket, null, false)
                    // intialize the Text view field
                    val random_number = itemView.findViewById(R.id.random_number) as TextView
                    /* val  set = AnimatorInflater.loadAnimator(ctx, R.anim.flip_left_in) as AnimatorSet
                     set.setTarget(random_number)
                     set.start()*/
                    if(type == "spin"){
                        val aniRotateClk = AnimationUtils.loadAnimation(ctx, R.anim.rotate_clockwise)
                        random_number.startAnimation(aniRotateClk)
                    }
                    // set the value in text view
                    random_number.text = random_number_list[i].toString()
                    // add the view in the custom layout
                    row_ticket_number.addView(itemView)
                }
            }
        }


        private fun showSelectBallDialog(ctx: LottoTicketsActivity, random_number_list : ArrayList<Int>, pos : Int) {
            // infalte the custom layout
            val dialogSelectBallBinding = DataBindingUtil.inflate<ViewDataBinding>(LayoutInflater.from(ctx), R.layout.dialog_select_ball, null, false)
            // set dialog for no title show in top
            val dialog = Dialog(ctx, android.R.style.Theme_Translucent_NoTitleBar)
            // set view on dialog
            dialog.setContentView(dialogSelectBallBinding.root)
            // cancelation set false touch on outside the dialog and sytem back button
            dialog.setCancelable(false)
            //dialog.setCanceledOnTouchOutside(false)

            //set the ball click on select ball view
            setNumberTicket(dialog.row_ticket_number_select_ball, ctx, random_number_list, "")
            //click listener on cancel button
            dialog.cancel_select_ball.setOnClickListener {
                dialog.dismiss()
            }
            val selectRandomNumberList : ArrayList<Int> = ArrayList()

            dialog.clear_data.setOnClickListener {
                random_number_list.clear()
                selectRandomNumberList.clear()
                setNumberTicket(dialog.row_ticket_number_select_ball, ctx, random_number_list, "clear")
                dialog.ball_1.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_2.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_3.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_4.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_5.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_4_bg))
                dialog.ball_6.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_4_bg))
                dialog.ball_7.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_4_bg))
                dialog.ball_8.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_9.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_10.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_11.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_12.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_13.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_4_bg))
                dialog.ball_14.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_4_bg))
                dialog.ball_15.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_16.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_17.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_18.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_19.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_20.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_21.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_4_bg))
                dialog.ball_22.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_23.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_24.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_25.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_26.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_27.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_28.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_29.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_30.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_31.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_32.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_33.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_34.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_35.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_36.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_37.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_38.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_39.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_40.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_41.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_42.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_1_bg))
                dialog.ball_43.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_44.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_45.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_46.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_47.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_48.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_49.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_3_bg))
                dialog.ball_50.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_51.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
                dialog.ball_52.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_number_2_bg))
            }

            // click listener on add button
            dialog.add_select_ball.setOnClickListener {
                dialog.dismiss()
                lotto_ticket_list[pos].random_number_list = selectRandomNumberList
                notifyDataSetChanged()
            }

            dialog.ball_1.setOnClickListener {
                setStyle(dialog.ball_1, 1, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_2.setOnClickListener {
                setStyle(dialog.ball_2, 2, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_3.setOnClickListener {
                setStyle(dialog.ball_3,3, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

             dialog.ball_4.setOnClickListener {
                 setStyle(dialog.ball_4, 4, selectRandomNumberList, dialog.row_ticket_number_select_ball)
             }

            dialog.ball_5.setOnClickListener {
                setStyle(dialog.ball_5, 5, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_6.setOnClickListener {
                setStyle(dialog.ball_6, 6, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_7.setOnClickListener {
                setStyle(dialog.ball_7, 7, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_8.setOnClickListener {
                setStyle(dialog.ball_8, 8, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_9.setOnClickListener {
                setStyle(dialog.ball_9, 9, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_10.setOnClickListener {
                setStyle(dialog.ball_10, 10, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_11.setOnClickListener {
                setStyle(dialog.ball_11, 11, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_12.setOnClickListener {
                setStyle(dialog.ball_12, 12, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_13.setOnClickListener {
                setStyle(dialog.ball_13, 13, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_14.setOnClickListener {
                setStyle(dialog.ball_14, 14, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_15.setOnClickListener {
                setStyle(dialog.ball_15, 15, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_16.setOnClickListener {
                setStyle(dialog.ball_16, 16, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_17.setOnClickListener {
                setStyle(dialog.ball_17, 17, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_18.setOnClickListener {
                setStyle(dialog.ball_18, 18, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_19.setOnClickListener {
                setStyle(dialog.ball_19, 19, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_20.setOnClickListener {
                setStyle(dialog.ball_20, 20, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_21.setOnClickListener {
                setStyle(dialog.ball_21, 21, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_22.setOnClickListener {
                setStyle(dialog.ball_22, 22, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_23.setOnClickListener {
                setStyle(dialog.ball_23, 23, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_24.setOnClickListener {
                setStyle(dialog.ball_24, 24, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_25.setOnClickListener {
                setStyle(dialog.ball_25, 25, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_26.setOnClickListener {
                setStyle(dialog.ball_26, 26, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_27.setOnClickListener {
                setStyle(dialog.ball_27, 27, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_28.setOnClickListener {
                setStyle(dialog.ball_28, 28, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_29.setOnClickListener {
                setStyle(dialog.ball_29, 29, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_30.setOnClickListener {
                setStyle(dialog.ball_30, 30, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_31.setOnClickListener {
                setStyle(dialog.ball_31, 31, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_32.setOnClickListener {
                setStyle(dialog.ball_32, 32, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_33.setOnClickListener {
                setStyle(dialog.ball_33, 33, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_34.setOnClickListener {
                setStyle(dialog.ball_34, 34, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_35.setOnClickListener {
                setStyle(dialog.ball_35, 35, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_36.setOnClickListener {
                setStyle(dialog.ball_36, 36, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_37.setOnClickListener {
                setStyle(dialog.ball_37, 37, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_38.setOnClickListener {
                setStyle(dialog.ball_38, 38, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_39.setOnClickListener {
                setStyle(dialog.ball_39, 39, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_40.setOnClickListener {
                setStyle(dialog.ball_40, 40, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_41.setOnClickListener {
                setStyle(dialog.ball_41, 41, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_42.setOnClickListener {
                setStyle(dialog.ball_42, 42, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_43.setOnClickListener {
                setStyle(dialog.ball_43, 43, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_44.setOnClickListener {
                setStyle(dialog.ball_44, 44, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_45.setOnClickListener {
                setStyle(dialog.ball_45, 45, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_46.setOnClickListener {
                setStyle(dialog.ball_46, 46, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_47.setOnClickListener {
                setStyle(dialog.ball_47, 47, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_48.setOnClickListener {
                setStyle(dialog.ball_48, 48, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_49.setOnClickListener {
                setStyle(dialog.ball_49, 49, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_50.setOnClickListener {
                setStyle(dialog.ball_50, 50, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }
            dialog.ball_51.setOnClickListener {
                setStyle(dialog.ball_51, 51, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            dialog.ball_52.setOnClickListener {
                setStyle(dialog.ball_52, 52, selectRandomNumberList, dialog.row_ticket_number_select_ball)
            }

            // show the dialog
            dialog.show()
        }

        private fun setStyle(textView: TextView, random_number : Int, selectRandomNumberList : ArrayList<Int>, numberLayout : LinearLayout){
            var add_bol : Boolean = false
            if(selectRandomNumberList.size == 0){
                selectRandomNumberList.add(random_number)
                textView.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_bg_h))
            }else{
                if(selectRandomNumberList.size != 6){
                    for(i in 0 until selectRandomNumberList.size){
                        if(random_number != selectRandomNumberList[i]){
                            add_bol = true
                        }else{
                            add_bol = false
                            ctx.displayToast(ctx.resources.getString(R.string.already_select_number_erroe_msg))
                            break
                        }
                    }
                    if(add_bol){
                        selectRandomNumberList.add(random_number)
                        textView.setBackgroundDrawable(ContextCompat.getDrawable(ctx, R.mipmap.poup_ball_bg_h))
                    }
                }else{
                    ctx.displayToast(ctx.resources.getString(R.string.select_number_error_msg))
                }

            }
            setNumberTicket(numberLayout, ctx, selectRandomNumberList, "")

        }
    }




}