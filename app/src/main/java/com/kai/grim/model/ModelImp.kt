package com.kai.grim.model

import com.kai.grim.present.getMainList

class ModelImp : MainModel {
    override fun getMain(mainArticle: getMainList, page: Int) {
        mainArticle.getMain(page)
    }
}