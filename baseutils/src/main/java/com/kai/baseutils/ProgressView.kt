package com.kai.baseutils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ClipDrawable
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView

class ProgressView(context: Context) : Dialog(context, R.style.dialog) {

    class Builder(context: Context) {

        private val mContext = context
        val loadingDialog = ProgressView(context)
        private val linearLayout: LinearLayout = LinearLayout(context)
        private val titleView: TextView = TextView(context)
        private var title = "请稍等"
        private var setTitle = false

        fun setDialogTitle(title: String): Builder {
            this.title = title
            setTitle = true
            return this
        }

        fun create(): ProgressView {
            linearLayout.orientation = LinearLayout.VERTICAL
            if (setTitle) {
                titleView.text = title
                titleView.gravity = Gravity.CENTER
                titleView.setPadding(10,10,10,10)
                val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT)
                titleView.layoutParams = params
                linearLayout.addView(titleView)
            }
            val progressBar = ProgressBar(mContext)
            val d = ClipDrawable(ColorDrawable(Color.YELLOW), Gravity.LEFT, ClipDrawable.HORIZONTAL)
            progressBar.progressDrawable = d
            val pbParams =
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            linearLayout.addView(progressBar,pbParams)
            loadingDialog.setContentView(linearLayout)
            return loadingDialog
        }
    }
}