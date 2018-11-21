package com.kai.baseutils.base

import android.content.Context
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.SparseArray
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout


abstract class BaseAdapter<T>(var datas: MutableList<T>, private val itemView: Int, val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val HEADER = 0
    private val BODY = 1
    private val FOOTER = 2
    private val DEFAULT_NUM = -1
    private lateinit var onItemClickListener: OnItemClickListener
    private val headLinearLayout = LinearLayout(context)
    private val footLinearLayout = LinearLayout(context)
    private val headViews: SparseArray<Int> = SparseArray()
    private val footViews: SparseArray<Int> = SparseArray()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when {
            headViews.get(viewType) != null -> HeaderHolder(
                LayoutInflater.from(context).inflate(
                    headViews.get(viewType),
                    parent,
                    false
                )
            )
            footViews.get(viewType) != null -> FooterHolder(
                LayoutInflater.from(context).inflate(
                    footViews.get(viewType),
                    parent,
                    false
                )
            )
            else -> BodyHolder(LayoutInflater.from(context).inflate(itemView, parent, false))
        }
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


    override fun getItemViewType(position: Int): Int {
        return when {
            isHeaderViewPos(position) -> headViews.keyAt(position)
            isFooterViewPos(position) -> footViews.keyAt(position - headViews.size() - datas.size)
            else -> this.getItemViewType(position - headViews.size())
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (isHeaderViewPos(position)) {
            return
        }
        if (isFooterViewPos(position)) {
            return
        }
        bindData(holder, position - headViews.size())
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

    fun addHeader(view: Int) {
        val i = headViews.indexOfValue(view)
        if (DEFAULT_NUM == i) {
            headViews.put(headViews.size(), view)
        }
    }

    fun addFooter(view: Int) {
        val i = footViews.indexOfValue(view)
        if (DEFAULT_NUM == i) {
            footViews.put(footViews.size(), view)
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