package com.kai.grim.ui.activity

import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.kai.baseutils.base.BaseActivity
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

    override fun getMainListSuccess(result: ArticleBean) {
        list.addAll(result.data.datas)
        adapter = ArticleAdapter(list)
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
        adapter.addHeader(R.layout.main_header_item)
    }

    override fun getBannerFailed(error: String) {

    }

    private val mainList: PresentImp.MainPresentImp by lazy { PresentImp.MainPresentImp(this) }
    private val banner: PresentImp.BannerPresentImp by lazy { PresentImp.BannerPresentImp(this) }

    override fun initView() {
        setCenterTitle("Main", R.color.red_3121, null)
        setRightTitle("Giao", R.color.zhihu_primary, 20f)
        rv_main.layoutManager = LinearLayoutManager(this)
        mainList.getMainList(0)
        banner.getBanner()
    }

    override fun bindLayout() = R.layout.activity_main
}
