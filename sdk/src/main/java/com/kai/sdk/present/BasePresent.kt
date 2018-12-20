package com.kai.sdk.present

interface BasePresent<T> {
        fun getData(page: Int)
        fun onSuccess(result: T)
        fun onFailed(error: String)
}