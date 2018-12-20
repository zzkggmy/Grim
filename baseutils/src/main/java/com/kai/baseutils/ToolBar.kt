package com.kai.baseutils

import android.content.Context
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import com.kai.baseutils.utils.findColor
import com.kai.baseutils.utils.getScreeWidth

class ToolBar(context: Context) : Toolbar(context) {

    private var relativeLayout: LinearLayout
    private var leftLinearLayout: RelativeLayout
    private var rightLinearLayout: RelativeLayout
    private var centerLinearLayout: RelativeLayout
    private var leftFirstLinearLayout: RelativeLayout
    private var rightFirstLinearLayout: RelativeLayout

    init {
        setContentInsetsRelative(0, 0)
        setPadding(0, 0, 0, 0)
        setTitleMargin(15, 0, 15, 0)
        setBackgroundColor(findColor(R.color.red_600))
        relativeLayout = LinearLayout(context)
        leftLinearLayout = RelativeLayout(context)
        centerLinearLayout = RelativeLayout(context)
        rightLinearLayout = RelativeLayout(context)
        leftFirstLinearLayout = RelativeLayout(context)
        rightFirstLinearLayout = RelativeLayout(context)
        relativeLayout.orientation = LinearLayout.HORIZONTAL
        relativeLayout.gravity = Gravity.CENTER
        val linearParams = LinearLayout.LayoutParams(getScreeWidth() / 5, ViewGroup.LayoutParams.WRAP_CONTENT)
        relativeLayout.addView(leftLinearLayout, linearParams)
        relativeLayout.addView(leftFirstLinearLayout, linearParams)
        relativeLayout.addView(centerLinearLayout, linearParams)
        relativeLayout.addView(rightFirstLinearLayout, linearParams)
        relativeLayout.addView(rightLinearLayout, linearParams)
        val params = LayoutParams(Toolbar.LayoutParams.MATCH_PARENT, Toolbar.LayoutParams.WRAP_CONTENT)
        params.setMargins(15,0,15,0)
        addView(relativeLayout, params)
    }

    fun setLeftText(text: String, textColor: Int, textSize: Float) {
        val textView = TextView(context)
        textView.text = text
        textView.gravity = Gravity.CENTER
        textView.setTextColor(textColor)
        textView.textSize = textSize
        val params =
                RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                )
        params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
        params.setMargins(15, 0, 0, 0)
        leftLinearLayout.removeAllViews()
        leftLinearLayout.addView(textView)
    }

    fun setTitle(text: String, textColor: Int, textSize: Float) {
        val textView = TextView(context)
        textView.text = text
        textView.gravity = Gravity.CENTER
        textView.setTextColor(textColor)
        textView.textSize = textSize
        val params =
                RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                )
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        centerLinearLayout.removeAllViews()
        centerLinearLayout.addView(textView, params)
    }

    fun setRightText(text: String, textColor: Int, textSize: Float) {
        val textView = TextView(context)
        textView.text = text
        textView.gravity = Gravity.CENTER
        textView.setTextColor(textColor)
        textView.textSize = textSize
        val params =
                RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                )
        params.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
        params.alignWithParent = true
        params.setMargins(0, 0, 15, 0)
        rightLinearLayout.removeAllViews()
        rightLinearLayout.addView(textView, params)
    }

    fun setLeftIcon(icon: Int) {
        val imageView = ImageView(context)
        imageView.setImageResource(icon)
        val params =
                RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                )
        params.addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
        params.setMargins(15, 0, 0, 0)
        leftLinearLayout.removeAllViews()
        leftLinearLayout.addView(imageView, params)
    }

    fun setTitleIcon(icon: Int) {
        val imageView = ImageView(context)
        imageView.setImageResource(icon)
        val params =
                RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                )
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        centerLinearLayout.removeAllViews()
        centerLinearLayout.addView(imageView, params)
    }

    fun setRightIcon(icon: Int) {
        val imageView = ImageView(context)
        imageView.setImageResource(icon)
        val params =
                RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                )
        params.addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
        params.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
        params.setMargins(0, 0, 15, 0)
        rightLinearLayout.removeAllViews()
        rightLinearLayout.addView(imageView, params)
    }

    fun setLeftFirstTitle(title: String, textColor: Int?, textSize: Float?) {
        val textView = TextView(context)
        textView.text = title
        textView.gravity = Gravity.CENTER
        if (textColor == null) {
            textView.setTextColor(findColor(R.color.text_black))
        } else {
            textView.setTextColor(findColor(textColor))
        }
        if (textSize == null) {
            textView.textSize = 15f
        } else {
            textView.textSize = textSize
        }
        val params =
                RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                )
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        leftFirstLinearLayout.removeAllViews()
        leftFirstLinearLayout.addView(textView, params)
    }

    fun setRightFirstTitle(title: String, textColor: Int?, textSize: Float?) {
        val textView = TextView(context)
        textView.text = title
        textView.gravity = Gravity.CENTER
        if (textColor == null) {
            textView.setTextColor(findColor(R.color.text_black))
        } else {
            textView.setTextColor(findColor(textColor))
        }
        if (textSize == null) {
            textView.textSize = 15f
        } else {
            textView.textSize = textSize
        }
        val params =
                RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                )
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        rightFirstLinearLayout.removeAllViews()
        rightFirstLinearLayout.addView(textView, params)
    }

    fun setLeftFirstIcon(drawble: Int) {
        val textView = ImageView(context)
        textView.setImageResource(drawble)
        val params =
                RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                )
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        leftFirstLinearLayout.removeAllViews()
        leftFirstLinearLayout.addView(textView, params)
    }

    fun setRightFirstIcon(drawble: Int) {
        val textView = ImageView(context)
        textView.setImageResource(drawble)
        val params =
                RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                )
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        rightFirstLinearLayout.removeAllViews()
        rightFirstLinearLayout.addView(textView, params)
    }

    fun setLeftVisibility(visibility: Int) {
        leftLinearLayout.getChildAt(0).visibility = visibility
    }

    fun setLeftFirstVisibility(visibility: Int) {
        leftFirstLinearLayout.getChildAt(0).visibility = visibility
    }

    fun setCenterVisibility(visibility: Int) {
        centerLinearLayout.getChildAt(0).visibility = visibility
    }

    fun setRightVisibility(visibility: Int) {
        rightLinearLayout.getChildAt(0).visibility = visibility
    }

    fun setRightFirstVisibility(visibility: Int) {
        rightFirstLinearLayout.getChildAt(0).visibility = visibility
    }

    fun setLeftClickable(clickable: Boolean) {
        leftLinearLayout.getChildAt(0).isClickable = clickable
    }

    fun setLeftFirstClickable(clickable: Boolean) {
        leftFirstLinearLayout.getChildAt(0).isClickable = clickable
    }

    fun setCenterClickable(clickable: Boolean) {
        centerLinearLayout.getChildAt(0).isClickable = clickable
    }

    fun setRightClickable(clickable: Boolean) {
        rightLinearLayout.getChildAt(0).isClickable = clickable
    }

    fun setRightFirstClickable(clickable: Boolean) {
        rightFirstLinearLayout.getChildAt(0).isClickable = clickable
    }


    fun setLeftClick(click: () -> Unit) {
        leftLinearLayout.setOnClickListener { click() }
    }

    fun setTitleClick(click: () -> Unit) {
        centerLinearLayout.setOnClickListener { click() }

    }

    fun setRightClick(click: () -> Unit) {
        rightLinearLayout.setOnClickListener { click() }
    }

    fun setLeftFirstClick(click: () -> Unit) {
        leftFirstLinearLayout.setOnClickListener { click() }
    }

    fun setRightFirstClick(click: () -> Unit) {
        rightFirstLinearLayout.setOnClickListener { click() }
    }
}