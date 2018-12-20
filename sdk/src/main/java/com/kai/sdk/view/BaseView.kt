package com.kai.sdk.view

interface BaseView<T> {
    fun getDataSuccess(result: T)
    fun getDataFailed(error: String)
}