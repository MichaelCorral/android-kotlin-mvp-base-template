package com.michaelcorral.mvptemplate.screens.itunesdetails


import com.michaelcorral.mvptemplate.api.models.ItunesContentResults
import com.michaelcorral.mvptemplate.data.itunes.ItunesRepository
import io.reactivex.disposables.CompositeDisposable

class ItunesDetailsPresenter(
    private var view: ItunesDetailsContract.View?,
    private val repository: ItunesRepository
) : ItunesDetailsContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private var itunesItem: ItunesContentResults? = null

    override fun initialize() {
        itunesItem = view?.getItunesItemFromBundle()

        if (itunesItem == null) {
            retrieveLastItunesContent()
        } else {
            view?.displayItunesItem(itunesItem)
        }
    }

    private fun removeLastItunesContent() {
        compositeDisposable.add(
            repository
                .deleteAllItunesConent()
                .subscribe { saveLastItunesContent() }
        )
    }

    private fun saveLastItunesContent() {
        itunesItem?.let { itunesItem ->
            compositeDisposable.add(
                repository
                    .saveLastItunesContent(itunesItem)
                    .subscribe()
            )
        }
    }

    private fun retrieveLastItunesContent() {
        compositeDisposable.add(
            repository
                .retrieveItunesContent()
                .subscribe { itunesContent ->
                    itunesItem = itunesContent.results[itunesContent.results.lastIndex]
                    view?.displayItunesItem(itunesItem)
                }
        )
    }

    override fun detachView() {
        view = null
        compositeDisposable.dispose()
    }
}