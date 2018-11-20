package com.kai.baseutils.utils

import android.app.Application
import android.content.Context

lateinit var app : Application
fun with(application: Application) {
    app = application
}