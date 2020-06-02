package com.michaelcorral.mvptemplate.base

interface BasePresenter {

    fun initialize()

    // We must detach the view when destroyed to prevent memory leaks
    fun detachView()
}