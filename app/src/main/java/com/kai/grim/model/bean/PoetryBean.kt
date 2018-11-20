package com.kai.grim.model.bean

/**
 * Created by kai on 2018-11-20
 */
data class PoetryBean(
    var code: Int,
    var message: String,
    var result: Result
) {
    data class Result(
        var title: String,
        var content: String,
        var authors: String
    )
}