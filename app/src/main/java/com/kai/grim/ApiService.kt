package com.kai.grim

import com.kai.grim.model.bean.PoetryBean
import retrofit2.http.GET
import rx.Observable

interface ApiService {
    @GET("recommendPoetry")
    fun getArticle(): Observable<PoetryBean>
}