package com.kai.grim.adapter

import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kai.baseutils.Banner
import com.kai.baseutils.base.BaseAdapter
import com.kai.baseutils.utils.app
import com.kai.grim.R
import com.kai.grim.model.bean.ArticleBean
import kotlinx.android.synthetic.main.article_item.view.*
import kotlinx.android.synthetic.main.main_header_item.view.*

class ArticleAdapter(
    datas: ArrayList<ArticleBean.Data.Datas>
) : BaseAdapter<ArticleBean.Data.Datas>(datas, R.layout.article_item, app) {

    override fun bindData(holder: RecyclerView.ViewHolder, position: Int) {
//        if (holder is HeaderHolder) {
//            holder.itemView.banner.apply {
//                setView(bannerList)
//                setAutoDisplay(true)
//                setCyclerDuration(2000)
//            }
//            holder.itemView.banner.bindImageLoader(object : Banner.BindImageEngine {
//                override fun load(url: String, iv: ImageView) {
//                    Glide.with(app)
//                        .asBitmap()
//                        .load(url)
//                        .apply {
//                            RequestOptions()
//                                .centerCrop()
//                                .placeholder(R.drawable.ic_no_video)
//                                .error(R.drawable.ic_no_video)
//                                .priority(Priority.HIGH)
//                                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                        }.into(iv)
//                }
//            })
//        } else {
//            holder.itemView.tv_article_item.text = datas[position].title
//        }
    }
}