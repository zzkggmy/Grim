package com.kai.grim.present

import com.kai.grim.model.bean.ArticleBean

interface getMainList {
    fun getMain(page: Int = 0)
    fun getMainSuccess(result: ArticleBean)
    fun getMainFailed(error: String)
}