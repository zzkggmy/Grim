package com.kai.baseutils.base

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.kai.baseutils.R
import com.kai.baseutils.utils.StatusBarUtil
import com.kai.baseutils.utils.findColor
import com.kai.baseutils.ProgressView
import com.kai.baseutils.ToolBar

abstract class BaseActivity<T: Presenter> : AppCompatActivity() {
    private lateinit var progressView: ProgressView.Builder
    private lateinit var toolBar: ToolBar
    private lateinit var linearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        setContentView(bindLayout())
        setLeftClick { finish() }
        setCenterTitle("标题", R.color.text_black,null)
        initView()
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1)
            toolBar.fitsSystemWindows = true
        StatusBarUtil.setColor(toolBar, this, findColor(R.color.white), 0, true)
    }

    abstract fun initView()

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        progressView.create().dismiss()
    }

    override fun setContentView(view: View?) = if (useTitleBar()) {
        linearLayout.addView(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        super.setContentView(linearLayout)
    } else {
        super.setContentView(view)
    }

    override fun setContentView(layoutResID: Int) = if (useTitleBar()) {
        linearLayout.addView(
            layoutInflater.inflate(layoutResID, linearLayout, false),
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        super.setContentView(linearLayout)
    } else {
        super.setContentView(layoutResID)
    }

    override fun setContentView(view: View?, params: ViewGroup.LayoutParams?) = if (useTitleBar()) {
        linearLayout.addView(view, params)
        super.setContentView(linearLayout)
    } else {
        super.setContentView(view, params)
    }

    private fun init() {
        progressView = ProgressView.Builder(this)
        toolBar = ToolBar(this)
        linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            toolBar.elevation = 15f
        }
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        linearLayout.addView(toolBar, params)
    }

    abstract fun bindLayout(): Int

    fun showProgressDialog() {
        progressView.create().show()
    }

    fun dismissProgressDialog() {
        progressView.create().dismiss()
    }

    fun showDialogWithTitle(title: String) {
        progressView.setDialogTitle(title).create().show()
    }

    protected open fun useTitleBar(): Boolean = true

    fun setToolBarBackgroudColor(color: Int) {
        toolBar.setBackgroundColor(findColor(color))
    }

    fun setLeftTitle(drawble: Int) = toolBar.setLeftIcon(drawble)
    fun setLeftFirstTitle(drawble: Int) = toolBar.setLeftFirstIcon(drawble)
    fun setLeftTitle(text: String, textColor: Int?, textSize: Float?) = toolBar.setLeftText(text, textColor, textSize)
    fun setLeftFirstTitle(text: String, textColor: Int, textSize: Float) =
        toolBar.setLeftFirstTitle(text, textColor, textSize)

    fun setLeftClick(click: () -> Unit) = toolBar.setLeftClick { click() }
    fun setLeftFirstClick(click: () -> Unit) = toolBar.setLeftFirstClick { click() }
    fun setLeftVisibility(visibility: Int) = toolBar.setLeftVisibility(visibility)
    fun setLeftFirstVisibility(visibility: Int) = toolBar.setLeftFirstVisibility(visibility)
    fun setLeftClickable(clickable: Boolean) = toolBar.setLeftClickable(clickable)
    fun setLeftFirstClickable(clickable: Boolean) = toolBar.setLeftFirstClickable(clickable)

    fun setCenterTitle(text: String, textColor: Int?, textSize: Float?) = toolBar.setTitle(text, textColor, textSize)
    fun setCenterTitle(drawble: Int) = toolBar.setTitleIcon(drawble)
    fun setTitleClick(click: () -> Unit) = toolBar.setTitleClick { click() }
    fun setCenterVisibility(visibility: Int) = toolBar.setCenterVisibility(visibility)
    fun setCenterClickable(clickable: Boolean) = toolBar.setCenterClickable(clickable)

    fun setRightTitle(text: String, textColor: Int?, textSize: Float?) = toolBar.setRightText(text, textColor, textSize)
    fun setRightFirstTitle(drawble: Int) = toolBar.setRightFirstIcon(drawble)
    fun setRightFirstTitle(text: String, textColor: Int?, textSize: Float?) =
        toolBar.setRightFirstTitle(text, textColor, textSize)

    fun setRihtTitle(drawble: Int) = toolBar.setRightIcon(drawble)
    fun setRightClick(click: () -> Unit) = toolBar.setRightClick { click() }
    fun setRightFirstClick(click: () -> Unit) = toolBar.setRightFirstClick { click() }
    fun setRightVisibility(visibility: Int) = toolBar.setRightVisibility(visibility)
    fun setRightFirstVisibility(visibility: Int) = toolBar.setRightFirstVisibility(visibility)
    fun setRightClickable(clickable: Boolean) = toolBar.setRightClickable(clickable)
    fun setRightFirstClickable(clickable: Boolean) = toolBar.setRightFirstClickable(clickable)

}