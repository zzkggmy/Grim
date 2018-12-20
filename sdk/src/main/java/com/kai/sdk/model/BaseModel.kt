package com.kai.sdk.model

import com.kai.sdk.present.BasePresent

interface BaseModel<T> {
    fun getModel(present: BasePresent<T>,page: Int)
}