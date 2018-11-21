package com.kai.grim.present

import com.kai.grim.model.bean.ArticleBean
import com.kai.grim.model.bean.BannerBean

interface Present {
    interface MainList {
        fun getMainList(page: Int = 0)
        fun getMainSuccess(result: ArticleBean)
        fun getMainFailed(error: String)
    }

    interface BannerPresent {
        fun getBanner()
        fun getBannerSuccess(result: BannerBean)
        fun getBannerFailed(error: String)
    }
}