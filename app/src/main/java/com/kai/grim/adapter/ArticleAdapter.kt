package com.kai.grim.adapter

import android.support.v7.widget.RecyclerView
import com.kai.baseutils.base.BaseAdapter
import com.kai.baseutils.utils.app
import com.kai.grim.R
import com.kai.grim.bean.ArticleBean
import kotlinx.android.synthetic.main.article_item.view.*

class ArticleAdapter(
    datas: ArrayList<ArticleBean.Data.Datas>
) : BaseAdapter<ArticleBean.Data.Datas>(datas, R.layout.article_item, app) {

    override fun bindData(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.tv_article_item.text = datas[position].title
    }

}