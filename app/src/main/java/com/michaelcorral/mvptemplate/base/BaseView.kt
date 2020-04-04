package com.michaelcorral.mvptemplate.base

interface BaseView {

    fun showLoading()
    fun hideLoading()

    fun showMessage(message: String)
}