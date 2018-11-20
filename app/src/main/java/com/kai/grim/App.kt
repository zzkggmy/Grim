package com.kai.grim

import android.app.Application
import com.kai.baseutils.utils.with

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        with(this)
    }
}