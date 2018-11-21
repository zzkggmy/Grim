package com.kai.grim.view

import com.kai.grim.model.bean.ArticleBean
import com.kai.grim.model.bean.BannerBean

interface BaseView {

    interface MainListView {
        fun getMainListSuccess(result: ArticleBean)

        fun getMainListFailed(error: String)
    }

    interface BannerView {
        fun getBannerSuccess(result: BannerBean)

        fun getBannerFailed(error: String)
    }
}