package com.kampus.teler.ui

import android.app.Activity
import android.app.Dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable

import android.view.View

import com.kampus.teler.R

object CustomeDialog {
    private var dialog: Dialog? = null

    fun showLoading(activity: Activity) {
        val dialogViews: View = activity.layoutInflater.inflate(R.layout.custom_dialog, null, false)

        dialog = Dialog(activity)
        dialog?.setCancelable(false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.setContentView(dialogViews)
        dialog?.show()

    }

    fun hideLoading(){
        dialog?.dismiss()
    }
}