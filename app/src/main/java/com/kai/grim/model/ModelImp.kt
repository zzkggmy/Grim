package com.kai.grim.model

import com.kai.baseutils.http.createRequest
import com.kai.baseutils.http.httpUtils
import com.kai.grim.ApiService
import com.kai.grim.model.bean.ArticleBean
import com.kai.grim.model.bean.BannerBean
import com.kai.grim.present.Present
import rx.Subscriber

object ModelImp {
    class MainModel : model.MainModel {
        override fun getMainModel(mainArticle: Present.MainList, page: Int) {
            httpUtils(createRequest(ApiService::class.java).getArticle(page), object : Subscriber<ArticleBean>() {
                override fun onNext(t: ArticleBean) {
                    if (t.errorCode == 0) {
                        mainArticle.getMainSuccess(t)
                    } else {
                        mainArticle.getMainFailed(t.errorMsg)
                    }
                }

                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {

                }
            })
        }
    }

    class BannerModel : model.BannerModel {
        override fun getBannerModel(bannerPresent: Present.BannerPresent) {
            httpUtils(createRequest(ApiService::class.java).getBanner(), object : Subscriber<BannerBean>() {
                override fun onNext(t: BannerBean) {
                    if (t.errorCode == 0) {
                        bannerPresent.getBannerSuccess(t)
                    } else {
                            bannerPresent.getBannerFailed(t.errorMsg)
                    }
                }

                override fun onCompleted() {

                }

                override fun onError(e: Throwable) {

                }
            })
        }

    }
}