package com.kai.baseutils.base

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.kai.baseutils.ProgressView
import com.kai.baseutils.ToolBar

abstract class BaseFragment : Fragment() {

    private lateinit var toolBar: ToolBar
    private lateinit var activity: Activity
    private lateinit var progressView: ProgressView
    private lateinit var linearLayout: LinearLayout

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        activity = context as Activity
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        toolBar = ToolBar(activity)
        progressView = ProgressView(activity)
        linearLayout = LinearLayout(activity)
        val params =
            ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        linearLayout.addView(toolBar, params)
        return if (useToolBar()) {
            linearLayout
        } else {
            inflater.inflate(bindLayout(), null)
        }
    }

    private fun useToolBar(): Boolean = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(view)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData(savedInstanceState)
    }

    protected abstract fun initView(view: View)

    protected abstract fun initData(savedInstanceState: Bundle?)

    protected abstract fun bindLayout(): Int

    fun showProgressDialog() {
        progressView.show()
    }

    fun dismissProgressDialog() {
        progressView.dismiss()
    }

}