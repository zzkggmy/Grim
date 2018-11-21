package com.kai.grim.present

import com.kai.grim.model.ModelImp
import com.kai.grim.model.bean.ArticleBean
import com.kai.grim.model.bean.BannerBean
import com.kai.grim.model.model
import com.kai.grim.view.BaseView

object PresentImp {
    class MainPresentImp(private val mainListView: BaseView.MainListView) : Present.MainList {
        private val model: model.MainModel = ModelImp.MainModel()
        override fun getMainList(page: Int) {
            model.getMainModel(this, page)
        }

        override fun getMainSuccess(result: ArticleBean) {
            if (result.errorCode == 0)
                mainListView.getMainListSuccess(result)
            else
                mainListView.getMainListFailed(result.errorMsg)
        }

        override fun getMainFailed(error: String) {
            mainListView.getMainListFailed(error)

        }
    }

    class BannerPresentImp(private val bannerView: BaseView.BannerView) : Present.BannerPresent {
        private val model: ModelImp.BannerModel = ModelImp.BannerModel()
        override fun getBanner() {
            model.getBannerModel(this)
        }

        override fun getBannerSuccess(result: BannerBean) {
            if (result.errorCode == 0)
                bannerView.getBannerSuccess(result)
            else
                bannerView.getBannerFailed(result.errorMsg)
        }

        override fun getBannerFailed(error: String) {
            bannerView.getBannerFailed(error)
        }

    }
}