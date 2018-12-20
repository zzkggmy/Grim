package com.kai.sdk

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface ApiService {

    @GET("article/list/{page}/json")
    fun <K> getArticle(@Path("page") page: Int): Observable<K>

    @GET("banner/json")
    fun <T> getBanner(): Observable<T>
}