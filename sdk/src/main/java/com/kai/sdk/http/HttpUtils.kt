package com.kai.sdk.http

import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import java.util.concurrent.TimeUnit

object HttpUtils {
    private val client = OkHttpClient
        .Builder()
        .connectTimeout(15, TimeUnit.SECONDS)
        .writeTimeout(15, TimeUnit.SECONDS)
        .readTimeout(15, TimeUnit.SECONDS)
        .build()
    private val JSON = MediaType.parse("application/json; charset=utf-8")

    fun asyncGet(url: String) {
        val response: Response = client.newCall(
            Request.Builder().url(url).build()
        ).execute()
        if (response.isSuccessful) {

        }
    }

    fun get(url: String, callBack: CallBack) {
        client.newCall(Request.Builder().url(url).build()).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callBack.onFailed(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                callBack.onSuccess(response.body().toString())
            }
        })
    }

    fun post(url: String, obj: JSONObject, callBack: CallBack) {
        val body = okhttp3.RequestBody.create(JSON, obj.toString())
        client.newCall(
            Request.Builder().url(url)
                .post(body).build()
        ).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callBack.onFailed(e.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                callBack.onSuccess(response.body().toString())
            }
        })
    }

    fun onFailed() {

    }

    fun onResponse() {

    }

    interface CallBack {
        fun onSuccess(result: String)
        fun onFailed(error: String)
    }
}