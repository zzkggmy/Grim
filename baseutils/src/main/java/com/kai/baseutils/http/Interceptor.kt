package com.kai.baseutils.http

import android.util.Log
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor

object Interceptor {
    private val TAG: String = "------"
    private lateinit var chain: Interceptor.Chain
    fun logInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor { message ->
            Log.w(TAG, "log: $message")
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun headerInterceptor(): Interceptor {
        return Interceptor { it ->
            chain = it
            it.proceed(it.request())
        }
    }

    fun interceptorHeader(): Interceptor.Chain {
        return chain
    }
}