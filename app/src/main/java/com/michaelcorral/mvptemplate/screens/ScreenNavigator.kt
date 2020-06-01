package com.michaelcorral.mvptemplate.screens

import android.app.Activity
import com.michaelcorral.mvptemplate.api.models.ItunesContentResults
import com.michaelcorral.mvptemplate.screens.itunesdetails.ItunesDetailsActivity

class ScreenNavigator(private val context: Activity) {

    fun redirectToItunesDetailsScreen(itunesItem: ItunesContentResults) {
        ItunesDetailsActivity.start(context, itunesItem)
    }
}