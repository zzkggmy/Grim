package com.kai.grim.model

import com.kai.grim.present.Present

interface model {
    interface MainModel {
        fun getMainModel(mainArticle: Present.MainList, page: Int = 0)
    }

    interface BannerModel {
        fun getBannerModel(bannerPresent: Present.BannerPresent)
    }
}