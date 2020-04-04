package com.michaelcorral.mvptemplate.screens.mainscreen

import com.michaelcorral.mvptemplate.api.models.ItunesContentResults
import com.michaelcorral.mvptemplate.data.itunes.ItunesRepository
import com.michaelcorral.mvptemplate.utils.extensions.today
import io.reactivex.disposables.CompositeDisposable
import java.util.*

class MainScreenPresenter(
    private var view: MainScreenContract.View?,
    private val repository: ItunesRepository
) : MainScreenContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    private var lastScreenId: String = ""

    override fun initialize() {
        retrieveScreenId()
        displayUserLastVisited()
        retrieveItunesContent()
        saveUserLastVisited()
    }

    private fun retrieveScreenId() {
        lastScreenId = repository.retrieveLastScreenId()
        view?.redirectToLastScreen(lastScreenId)
    }

    private fun displayUserLastVisited() {
        view?.displayUserLastVisited(repository.retrieveUserLastVisitDate())
    }

    private fun retrieveItunesContent() {
        compositeDisposable.add(
            repository
                .retrieveItunesContent()
                .doOnSubscribe { view?.showLoading() }
                .doFinally { view?.hideLoading() }
                .subscribe { response ->
                    view?.displayItunesContent(response.results)
                }
        )
    }

    private fun saveUserLastVisited() {
        repository.saveUserLastVisitDate(Date().today())
    }

    override fun onItunesContentClick(itunesItem: ItunesContentResults) {
        view?.redirectToItunesDetailsScreen(itunesItem)
    }

    override fun detachView() {
        view = null
        compositeDisposable.dispose()
    }
}