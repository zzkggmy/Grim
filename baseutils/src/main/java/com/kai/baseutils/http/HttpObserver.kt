package com.kai.baseutils.http

import android.accounts.NetworkErrorException
import android.os.Build
import android.support.annotation.RequiresApi
import rx.Subscriber
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException


abstract class HttpObserver<T> : Subscriber<ResponseResult<T>>() {

    @RequiresApi(Build.VERSION_CODES.ECLAIR)
    override fun onError(e: Throwable) {
        try {
            when (e) {
                is ConnectException -> onFailure("连接错误")
                is TimeoutException -> onFailure("TimeOut")
                is NetworkErrorException -> onFailure("网络异常")
                is UnknownHostException -> onFailure("UnknownHostException")
                else -> onFailure(e.toString())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        onCompleted()
    }

    override fun onNext(t: ResponseResult<T>) {
            onSuccess(t)
    }

    override fun onCompleted() {

    }

    protected abstract fun onSuccess(t: ResponseResult<T>)

    protected abstract fun onFailure(e: String)

}