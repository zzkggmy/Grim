package com.kai.baseutils

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.kai.baseutils.utils.findColor


class Banner(context: Context, attr: AttributeSet) : RelativeLayout(context, attr) {


    private var currentId = 1
    private var cyclerDuration = 2000L
    private var indicatorBackground = R.drawable.bg_grey_dots_shape
    private var selectIndicatorBackground = R.drawable.bg_blue_default_dots_shape
    private var size = 0
    private val AUTO_DISPLAY = 0
    private val views: ArrayList<View> = ArrayList()
    private lateinit var vp: ViewPager
    private lateinit var dotsLinearLayout: LinearLayout
    private lateinit var relativeLayout: RelativeLayout
    private lateinit var title: TextView
    private var mOnBannerItemClickListener: OnBannerItemClickListener? = null
    private lateinit var bindImageEngine: BindImageEngine

    private val mHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)
            when (msg!!.what) {
                AUTO_DISPLAY -> {
                    vp.setCurrentItem(vp.currentItem + currentId, true)
                    sendEmptyMessageDelayed(AUTO_DISPLAY, cyclerDuration)
                }
            }
        }
    }

    fun setAutoDisplay(auto: Boolean) {
        if (auto) {
            mHandler.sendEmptyMessageDelayed(AUTO_DISPLAY, cyclerDuration)
        } else {
            mHandler.removeMessages(AUTO_DISPLAY)
        }
    }

    fun setCyclerDuration(duration: Long) {
        cyclerDuration = duration
    }

    fun stopAutoDisplay() {
        mHandler.removeMessages(AUTO_DISPLAY)
    }

    fun setIndicatorStyle(background: Int) {
        indicatorBackground = background
    }

    fun setSelectIndicatorStyle(background: Int) {
        selectIndicatorBackground = background
    }

    fun setOnBannerItemClickListener(onBannerItemClickListener: OnBannerItemClickListener) {
        this.mOnBannerItemClickListener = onBannerItemClickListener
    }

    fun setIndicatorWithTitle(
        backgroundColor: Int,
        backgroudAlpha: Float,
        textColor: Int,
        textSize: Float,
        titles: ArrayList<String>
    ) {
        titles.add(titles[titles.size - 1])
        val dotsList: ArrayList<TextView> = ArrayList()
        relativeLayout = RelativeLayout(context)
        val titleLinearLayout = LinearLayout(context)
        title = TextView(context)
        title.setTextColor(findColor(textColor))
        title.textSize = textSize
        val dotsParams = LinearLayout.LayoutParams(20, 20)
        dotsParams.gravity = Gravity.CENTER
        dotsParams.setMargins(0, 0, 40, 0)
        val linearParams = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        linearParams.setMargins(15, 0, 15, 0)
        linearParams.addRule(RelativeLayout.ALIGN_PARENT_END)
        linearParams.addRule(RelativeLayout.CENTER_VERTICAL)
        val titleParam = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        titleParam.setMargins(15, 0, 15, 30)
        titleParam.addRule(RelativeLayout.CENTER_VERTICAL)
        titleParam.addRule(RelativeLayout.ALIGN_PARENT_START)
        //RelativeLayout的格式
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        params.setMargins(0, 0, 0, 0)
        relativeLayout.setBackgroundColor(findColor(backgroundColor))
        relativeLayout.alpha = backgroudAlpha
        removeView(dotsLinearLayout)
        relativeLayout.addView(titleLinearLayout, linearParams)
        for (i in 0 until size - 1) {
            val dots = TextView(context)
            dots.setBackgroundResource(indicatorBackground)
            titleLinearLayout.addView(dots, dotsParams)
            dotsList.add(dots)
        }
        relativeLayout.addView(title, titleParam)
        addView(relativeLayout, params)
        title.text = titles[0]
        moveIndicatorWithTitle(titles, dotsList)
    }

    private fun moveIndicatorWithTitle(titles: ArrayList<String>, dotsList: ArrayList<TextView>) {
        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                dotsList[dotsList.size - 1].visibility = View.GONE
                views[dotsList.size - 1].visibility = View.GONE
                if (position == dotsList.size - 1) {
                    vp.currentItem = 0
                }
                dotsList.forEachIndexed { index, _ ->
                    if (index == position) {
                        dotsList[index].setBackgroundResource(selectIndicatorBackground)
                    } else {
                        dotsList[index].setBackgroundResource(indicatorBackground)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                title.text = titles[position]
                dotsList[dotsList.size - 1].visibility = View.GONE
                views[dotsList.size - 1].visibility = View.GONE
                if (position == dotsList.size - 1) {
                    vp.currentItem = 0
                }
                dotsList.forEachIndexed { index, _ ->
                    if (index == position) {
                        dotsList[index].setBackgroundResource(selectIndicatorBackground)
                    } else {
                        dotsList[index].setBackgroundResource(indicatorBackground)
                    }
                }
            }
        })
    }

    private fun setIndicator() {
        val dotsList: ArrayList<TextView> = ArrayList()
        dotsLinearLayout = LinearLayout(context)
        val params = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.WRAP_CONTENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        params.addRule(RelativeLayout.CENTER_HORIZONTAL)
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        params.setMargins(0, 0, 0, 30)
        addView(dotsLinearLayout, params)
        val dotsParams = LinearLayout.LayoutParams(20, 20)
        dotsParams.setMargins(15, 0, 15, 0)
        dotsParams.gravity = Gravity.CENTER
        for (i in 0 until size - 1) {
            val dots = TextView(context)
            dots.setBackgroundResource(indicatorBackground)
            dotsLinearLayout.addView(dots, dotsParams)
            dotsList.add(dots)
        }
        moveIndicator(dotsList)
    }

    private fun moveIndicator(dotsList: ArrayList<TextView>) {
        vp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                dotsList[dotsList.size - 1].visibility = View.GONE
                views[dotsList.size - 1].visibility = View.GONE
                if (dotsList.size - 1 == position) {
                    vp.currentItem = 0
                }
                dotsList.forEachIndexed { index, _ ->
                    if (index == position) {
                        dotsList[index].setBackgroundResource(selectIndicatorBackground)
                    } else {
                        dotsList[index].setBackgroundResource(indicatorBackground)
                    }
                }
            }

            override fun onPageSelected(position: Int) {
                dotsList[dotsList.size - 1].visibility = View.GONE
                views[dotsList.size - 1].visibility = View.GONE
                if (dotsList.size - 1 == position) {
                    vp.currentItem = 0
                }
                dotsList.forEachIndexed { index, _ ->
                    if (index == position) {
                        dotsList[index].setBackgroundResource(selectIndicatorBackground)
                    } else {
                        dotsList[index].setBackgroundResource(indicatorBackground)
                    }
                }
            }

            override fun onPageScrollStateChanged(state: Int) {
            }
        })
    }

    fun setView(urls: ArrayList<String>) {
        size = urls.size + 1
        urls.add(urls[urls.size - 1])
        vp = ViewPager(context)
        for (i in 0 until urls.size) {
            views.add(frameLayout(urls[i], i))
        }
        val bannerAdapter = BannerAdapter(views)
        addView(vp)
        vp.adapter = bannerAdapter
        setIndicator()
//        setSliderTransformDuration()
    }

    private fun frameLayout(url: String, position: Int): FrameLayout {
        val frameLayout = FrameLayout(context)
        val layoutParams =
            FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
        layoutParams.setMargins(0, 0, 0, 0)
        frameLayout.layoutParams = layoutParams
        val iv = ImageView(context)
        val ivParams =
            ViewGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        iv.scaleType = ImageView.ScaleType.CENTER_CROP
        LoadImage(url,iv)
        frameLayout.addView(iv, ivParams)
        frameLayout.setOnClickListener {
            mOnBannerItemClickListener?.onItemClick(iv, position)
        }
        return frameLayout
    }

    private fun LoadImage(url: String, iv: ImageView) {
        bindImageEngine.load(url,iv)
    }

    fun bindImageLoader(bindImageEngine: BindImageEngine) {
        this.bindImageEngine = bindImageEngine
    }

    private fun setSliderTransformDuration() {
        try {
            val mScroller = ViewPager::class.java.getDeclaredField("mScroller")
            mScroller.isAccessible = true
//            val fixedSpeedScroller = FixedSpeedScroller(vp.context)
//            mScroller.set(vp, fixedSpeedScroller)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    inner class BannerAdapter(private val views: List<View>) : PagerAdapter() {

        override fun isViewFromObject(view: View, `object`: Any): Boolean {
            return view === `object`
        }

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            var view: View? = null
            if (views.isNotEmpty()) {
                view = views[position % views.size]
                if (container == view.parent) {
                    container.removeView(view)
                }
                container.addView(view)
                return view
            }
            return view!!
        }

        override fun getCount() = views.size

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {}
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        when (ev.action) {
            MotionEvent.ACTION_DOWN -> stopAutoDisplay()
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> setAutoDisplay(true)
        }
        return super.dispatchTouchEvent(ev)
    }
//
//    inner class FixedSpeedScroller(context: Context) : Scroller(context) {
//
//        private var mDuration = cyclerDuration.toInt()
//
//        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int, duration: Int) {
//            super.startScroll(startX, startY, dx, dy, mDuration)
//        }
//
//        override fun startScroll(startX: Int, startY: Int, dx: Int, dy: Int) {
//            super.startScroll(startX, startY, dx, dy, mDuration)
//        }
//
//        init {
//            mDuration = duration
//        }
//    }

    interface OnBannerItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    interface BindImageEngine {
        fun load(url: String, iv: ImageView)
    }

}
