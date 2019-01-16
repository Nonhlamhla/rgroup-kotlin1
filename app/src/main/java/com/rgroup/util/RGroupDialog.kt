package com.rgroup.util

import android.app.Activity
import android.app.ProgressDialog
import com.rgroup.R

/**
 * Created by arpit.jain on 1/29/2018.
 */
class RGroupDialog(context: Activity) {
    var ctx: Activity = context

    companion object {
        fun showLoading(activity: Activity): ProgressDialog {
            val mProgressDialog = ProgressDialog(activity)
            mProgressDialog.isIndeterminate = true
            mProgressDialog.setMessage(activity.resources.getString(R.string.loading))
            if (!activity.isFinishing && !mProgressDialog.isShowing)
                mProgressDialog.show()
            return mProgressDialog
        }
    }

}