package com.kai.grim.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.kai.baseutils.Banner
import com.kai.baseutils.base.BaseActivity
import com.kai.baseutils.utils.app
import com.kai.grim.R
import com.kai.grim.adapter.ArticleAdapter
import com.kai.grim.model.bean.ArticleBean
import com.kai.grim.model.bean.BannerBean
import com.kai.grim.present.PresentImp
import com.kai.grim.view.BaseView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), BaseView.MainListView, BaseView.BannerView {

    private val list: ArrayList<ArticleBean.Data.Datas> = ArrayList()
    private lateinit var adapter: ArticleAdapter
    private val bannerList: ArrayList<String> = ArrayList()
    private lateinit var bannerView: Banner

    override fun getMainListSuccess(result: ArticleBean) {
        list.addAll(result.data.datas)
        Log.d("success_data", result.data.datas[0].title)
        adapter = ArticleAdapter(list)
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = adapter
    }

    override fun getMainListFailed(error: String) {
        Log.d("error_data", error)
    }

    override fun getBannerSuccess(result: BannerBean) {
        for (url in result.data) {
            bannerList.add(url.imagePath)
            Log.d("banner_data", url.imagePath)
        }
        bannerView = LayoutInflater.from(this).inflate(R.layout.main_header_item, null) as Banner
        adapter.addHeader(bannerView)
        bannerView.apply {
            setView(bannerList)
            setAutoDisplay(true)
            setCyclerDuration(2000)
            bindImageLoader(object : Banner.BindImageEngine {
                override fun load(url: String, iv: ImageView) {
                    Glide.with(app)
                         .asBitmap()
                         .load(url)
                         .apply {
                            RequestOptions()
                                .centerCrop()
                                .placeholder(R.drawable.ic_no_video)
                                .error(R.drawable.ic_no_video)
                                .priority(Priority.HIGH)
                                .diskCacheStrategy(DiskCacheStrategy.NONE)
                         }.into(iv)
                }
            })
        }
    }

    override fun getBannerFailed(error: String) {

    }

    private val mainList: PresentImp.MainPresentImp by lazy { PresentImp.MainPresentImp(this) }
    private val banner: PresentImp.BannerPresentImp by lazy { PresentImp.BannerPresentImp(this) }

    override fun initView() {
        setCenterTitle("Main", R.color.red_3121, null)
        setRightTitle("Giao", R.color.zhihu_primary, 20f)
        mainList.getMainList(0)
        banner.getBanner()
    }

    override fun bindLayout() = R.layout.activity_main
}
