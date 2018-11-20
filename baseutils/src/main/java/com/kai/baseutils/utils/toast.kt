package com.kai.baseutils.utils

import android.widget.Toast

fun toastShort(content: String) {
    Toast.makeText(app, content, Toast.LENGTH_SHORT).show()
}

fun toastLong(content: String) {
    Toast.makeText(app, content, Toast.LENGTH_LONG).show()
}