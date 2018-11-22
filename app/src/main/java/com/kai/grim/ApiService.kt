package com.kai.grim

import com.kai.grim.model.bean.ArticleBean
import com.kai.grim.model.bean.BannerBean
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface ApiService {

    @GET("article/list/{page}/json")
    fun getArticle(@Path("page") page: Int): Observable<ArticleBean>

    @GET("banner/json")
    fun getBanner(): Observable<BannerBean>
}