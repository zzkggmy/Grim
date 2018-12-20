package com.kai.sdk.model

import com.kai.sdk.ApiService
import com.kai.sdk.http.HttpObserver
import com.kai.sdk.http.createRequest
import com.kai.sdk.http.httpUtils
import com.kai.sdk.present.BasePresent

object BaseModelImp {
    class MainModel<T> : BaseModel<T> {
        override fun getModel(present: BasePresent<T>, page: Int) {
            httpUtils(createRequest(ApiService::class.java).getArticle(page), object : HttpObserver<T>() {
                override fun onSuccess(t: T) {
                    present.onSuccess(t)
                }

                override fun onFailure(e: String) {
                    present.onFailed(e)
                }
            })
        }
    }
}