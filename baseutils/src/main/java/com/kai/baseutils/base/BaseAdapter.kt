package com.kai.baseutils.base

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.LinearLayout


abstract class BaseAdapter<T>(var datas: MutableList<T>, private val itemView: Int, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val HEADER = 0
    private val BODY = 1
    private val FOOTER = 2
    private val DEFAULT_NUM = -1
    private lateinit var onItemClickListener: OnItemClickListener
    private lateinit var headLinearLayout: LinearLayout
    private lateinit var footLinearLayout: LinearLayout
    private lateinit var mEmptyLayout: FrameLayout
    private val headViews: SparseArray<View> = SparseArray()
    private val footViews: SparseArray<View> = SparseArray()
    val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    private var mIsUseEmpty = true
    private var mHeadAndEmptyEnable: Boolean = false
    private var mFootAndEmptyEnable: Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            HEADER -> HeaderHolder(headLinearLayout)
//            FOOTER -> FooterHolder(footLinearLayout)
//            else ->
        return BodyHolder(LayoutInflater.from(context).inflate(itemView, parent, false))
//        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.onAttachedToRecyclerView(recyclerView)
        val manager = recyclerView.layoutManager
        if (manager is GridLayoutManager) {
            manager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    val itemViewType = getItemViewType(position)
                    return if (headViews.get(itemViewType) != null || footViews.get(itemViewType) != null) {
                        manager.spanCount
                    } else 1
                }
            }
        }
    }

    override fun onViewAttachedToWindow(holder: RecyclerView.ViewHolder) {
        super.onViewAttachedToWindow(holder)
        this.onViewAttachedToWindow(holder)
        val layoutPosition = holder.layoutPosition
        if (isHeaderViewPos(layoutPosition) || isFooterViewPos(layoutPosition)) {
            val layoutParams = holder.itemView.layoutParams
            if (layoutParams != null) {
                if (layoutParams is StaggeredGridLayoutManager.LayoutParams) {
                    layoutParams.isFullSpan = true
                }
            }
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    override fun getItemCount(): Int = datas.size + headViews.size() + footViews.size()


//    override fun getItemViewType(position: Int): Int {
//        return when (position) {
//            0 -> HEADER
//            datas.size - 1 -> FOOTER
//            else -> BODY
//        }
//    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        if (position == 0) {
//            return
//        }
//        if (position == datas.size - 1) {
//            return
//        }
        bindData(holder, holder.adapterPosition)
    }

    private fun getRealItemCount(): Int {
        return datas.size + headViews.size() + footViews.size()
    }

    private fun isHeaderViewPos(position: Int): Boolean {
        return position < headViews.size()
    }

    private fun isFooterViewPos(position: Int): Boolean {
        return position >= headViews.size() + getRealItemCount()
    }

    fun addHeader(view: View): Int {
        return addHeader(view)
    }

    fun addHeader(view: View, index: Int): Int {
        return addHeader(view, index, LinearLayout.VERTICAL)
    }

    fun addHeader(view: View, index: Int, orientation: Int): Int {
        var pos: Int = index
            headLinearLayout = LinearLayout(view.context)
            if (orientation == LinearLayout.VERTICAL) {
                headLinearLayout.orientation = LinearLayout.VERTICAL
                headLinearLayout.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
            } else {
                headLinearLayout.setOrientation(LinearLayout.HORIZONTAL)
                headLinearLayout.setLayoutParams(ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT))
            }
        val childCount = headLinearLayout.getChildCount()
        if (index < 0 || index > childCount) {
            pos = childCount
        }
        headLinearLayout.addView(view, index)
        if (headLinearLayout.childCount == 1) {
            val position = getHeaderViewPosition()
            if (position != -1) {
                notifyItemInserted(position)
            }
        }
        return pos
    }

    private fun getHeaderViewPosition(): Int {
        if (getEmptyViewCount() == 1) {
            if (mHeadAndEmptyEnable) {
                return 0
            }
        } else {
            return 0
        }
        return -1
    }

    fun getEmptyViewCount(): Int {
        if (mEmptyLayout == null || mEmptyLayout.childCount == 0) {
            return 0
        }
        if (!mIsUseEmpty) {
            return 0
        }
        return if (datas.size != 0) {
            0
        } else 1
    }

    fun getEmptyView(): View {
        return mEmptyLayout
    }

    fun addFooter(view: View) {
//        val i = footViews.indexOfValue(view)
//        if (DEFAULT_NUM == i) {
//            footViews.put(footViews.size(), view)
//        }
        footLinearLayout.setVerticalGravity(LinearLayout.VERTICAL)
        footLinearLayout.layoutParams = params
        footLinearLayout.addView(view)
    }

    private fun getHeaderLayoutCount(): Int {
        return if (headLinearLayout == null || headLinearLayout.childCount == 0)
            0 else 1
    }

    fun setEmptyView(emptyView: View) {
        var insert = false
        if (mEmptyLayout == null) {
            mEmptyLayout = FrameLayout(emptyView.context)
            val layoutParams =
                RecyclerView.LayoutParams(
                    RecyclerView.LayoutParams.MATCH_PARENT,
                    RecyclerView.LayoutParams.MATCH_PARENT
                )
            val lp = emptyView.layoutParams
            if (lp != null) {
                layoutParams.width = lp.width
                layoutParams.height = lp.height
            }
            mEmptyLayout.layoutParams = layoutParams
            insert = true
        }
        mEmptyLayout.removeAllViews()
        mEmptyLayout.addView(emptyView)
        mIsUseEmpty = true
        if (insert) {
            if (getEmptyViewCount() == 1) {
                var position = 0
                if (mHeadAndEmptyEnable && getHeaderLayoutCount() != 0) {
                    position++
                }
                notifyItemInserted(position)
            }
        }
    }

    abstract fun bindData(holder: RecyclerView.ViewHolder, position: Int)

    class HeaderHolder(view: View) : RecyclerView.ViewHolder(view)

    class FooterHolder(view: View) : RecyclerView.ViewHolder(view)

    class BodyHolder(view: View) : RecyclerView.ViewHolder(view)

    interface OnItemClickListener {
        fun click(view: View, position: Int)
    }

}