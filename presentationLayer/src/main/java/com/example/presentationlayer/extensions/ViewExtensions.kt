package com.example.presentationlayer.extensions

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}