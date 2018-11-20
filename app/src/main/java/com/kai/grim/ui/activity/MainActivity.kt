package com.kai.grim.ui.activity

import android.util.Log
import com.kai.baseutils.base.BaseActivity
import com.kai.baseutils.base.Presenter
import com.kai.baseutils.http.*
import com.kai.grim.ApiService
import com.kai.grim.R
import com.kai.grim.model.bean.ArticleBean
import com.kai.grim.model.bean.PoetryBean
import com.kai.grim.present.getMainList
import kotlinx.android.synthetic.main.activity_main.*
import rx.Subscriber

class MainActivity : BaseActivity<Presenter>(),getMainList {
    override fun getMain(page: Int) {

    }

    override fun getMainSuccess(result: ArticleBean) {
    }

    override fun getMainFailed(error: String) {

    }

    override fun initView() {
        setCenterTitle("Main", R.color.red_3121, null)
        setRightTitle("Giao", R.color.zhihu_primary, 20f)
        getArticle()
    }

    private fun getArticle() {
        httpUtils(createRequest(ApiService::class.java).getArticle(),
            object : Subscriber<PoetryBean>() {
                override fun onNext(t: PoetryBean) {
                    tv_main.text = t.result.content
                    Log.d("success_data", t.result.authors)
                }

                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {

                }
            })
    }

    override fun bindLayout() = R.layout.activity_main
}
