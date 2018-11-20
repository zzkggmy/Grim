package com.kai.grim.model

import com.kai.grim.present.getMainList

interface MainModel {
    fun getMain(mainArticle: getMainList,page: Int = 0)
}