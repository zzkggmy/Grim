package com.kai.baseutils.utils

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat


fun findColor(@ColorRes colorId: Int): kotlin.Int {
    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
        return ContextCompat.getColor(app, colorId)
    else return app.getColor(colorId)
}

fun getDrawble(@DrawableRes drawbleId: kotlin.Int): Drawable? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        ContextCompat.getDrawable(app, drawbleId)
    else getDrawble(drawbleId)
}

val dm = app.resources.displayMetrics
fun getScreeHeight(): Int {

    return dm.heightPixels
}

fun getScreeWidth(): Int {
    return dm.widthPixels
}

val sp: SharedPreferences by lazy { app.getSharedPreferences(app.packageName, Context.MODE_PRIVATE) }

fun setString(key: kotlin.String, value: kotlin.String) = sp.edit().putString(key, value).commit()

fun setInt(key: kotlin.String, value: kotlin.Int) = sp.edit().putInt(key, value).commit()

fun setBoolean(key: kotlin.String, value: kotlin.Boolean) = sp.edit().putBoolean(key, value).commit()

fun setFloat(key: kotlin.String, value: kotlin.Float) = sp.edit().putFloat(key, value).commit()

fun setLong(key: kotlin.String, value: kotlin.Long) = sp.edit().putLong(key, value).commit()

fun getString(key: kotlin.String, defaultValue: kotlin.String) = sp.getString(key, defaultValue)

fun getInt(key: kotlin.String, defaultValue: kotlin.Int) = sp.getInt(key, defaultValue)

fun getBoolean(key: kotlin.String, defaultValue: kotlin.Boolean) = sp.getBoolean(key, defaultValue)

fun getFloat(key: kotlin.String, defaultValue: kotlin.Float) = sp.getFloat(key, defaultValue)

fun getLong(key: kotlin.String, defaultValue: kotlin.Long) = sp.getLong(key, defaultValue)

fun spClear() = sp.edit().clear()!!