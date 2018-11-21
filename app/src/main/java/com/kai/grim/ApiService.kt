package com.kai.grim

import com.kai.grim.model.bean.ArticleBean
import com.kai.grim.model.bean.BannerBean
import retrofit2.http.GET
import rx.Observable

interface ApiService {

    @GET("article/list/0/json")
    fun getArticle(): Observable<ArticleBean>

    @GET("banner/json")
    fun getBanner(): Observable<BannerBean>
}