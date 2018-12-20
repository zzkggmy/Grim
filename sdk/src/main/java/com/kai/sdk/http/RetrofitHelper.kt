package com.kai.sdk.http

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.internal.http.HttpMethod
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.Subscriber
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit


fun <T> createRequest(request: Class<T>): T {
    val TAG = HttpMethod::class.java.simpleName
    val TIME_OUT = 15L
    val loggingInterceptor = HttpLoggingInterceptor()
    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return Retrofit.Builder()
        .client(
            OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
                .addInterceptor(Interceptor.headerInterceptor())
                .addInterceptor(Interceptor.logInterceptor()).build()
        )
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .baseUrl("http://www.wanandroid.com/")
        .build().create(request)

}


fun <T> httpUtils(observable: Observable<T>, subscriber: Subscriber<T>) {
    observable
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .subscribeOn(Schedulers.newThread())
        .subscribe(subscriber)
}