package com.kai.baseutils.http

class ResponseResult<T>(
    var code: Int = 0,
    var message: String,
    var result: T,
    var isError: Boolean = false
) {
    val isSuccess: Boolean
        get() = code == SUCCESS_CODE

    companion object {
        private val SUCCESS_CODE = 200
    }
}
