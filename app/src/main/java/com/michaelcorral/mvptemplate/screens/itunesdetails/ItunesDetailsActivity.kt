package com.michaelcorral.mvptemplate.screens.itunesdetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import com.michaelcorral.mvptemplate.R
import com.michaelcorral.mvptemplate.api.models.ItunesContentResults
import com.michaelcorral.mvptemplate.base.BasePresenter
import com.michaelcorral.mvptemplate.base.MvpActivity
import kotlinx.android.synthetic.main.itunesdetails_activity.*
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf
import timber.log.Timber

private const val ITUNES_ITEM_KEY = "ItunesItemKey"

class ItunesDetailsActivity : MvpActivity(), ItunesDetailsContract.View {

    private val presenter: ItunesDetailsContract.Presenter by currentScope.inject { parametersOf(this) }

    private var itunesItem: ItunesContentResults? = null

    override fun getActivityLayout(): Int = R.layout.itunesdetails_activity

    override fun getActivityPresenter(): BasePresenter = presenter

    companion object {
        fun start(context: Context, itunesItem: ItunesContentResults?) {
            val intent = Intent(context, ItunesDetailsActivity::class.java)
            intent.putExtra(ITUNES_ITEM_KEY, itunesItem)
            context.startActivity(intent)
        }
    }

    override fun onActivityReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onActivityReady(savedInstanceState, intent)

        presenter.initialize()
    }

    override fun getItunesItemFromBundle(): ItunesContentResults? {
        itunesItem = intent.getParcelableExtra(ITUNES_ITEM_KEY)
        return itunesItem
    }

    override fun displayItunesItem(itunesItem: ItunesContentResults?) {
        itunesItem?.let { item ->
            Glide.with(this)
                .load(item.artworkUrl600)
                .into(itunesDetailsImageViewArtwork)

            itunesDetailsTextViewTrackName.text = item.trackName
            itunesDetailsTextViewGenre.text = item.primaryGenreName
            itunesDetailsTextViewDescription.text = item.longDescription
        }
    }
}