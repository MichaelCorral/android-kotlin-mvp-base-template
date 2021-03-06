package com.michaelcorral.mvptemplate.screens.mainscreen

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.michaelcorral.mvptemplate.R
import com.michaelcorral.mvptemplate.api.models.ItunesContentResults
import com.michaelcorral.mvptemplate.screens.ScreenNavigator
import com.michaelcorral.mvptemplate.base.BasePresenter
import com.michaelcorral.mvptemplate.base.MvpActivity
import kotlinx.android.synthetic.main.mainscreen_activity.*
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf
import org.koin.ext.getFullName

class MainScreenActivity : MvpActivity(), MainScreenContract.View {

    private val presenter: MainScreenContract.Presenter by currentScope.inject { parametersOf(this) }
    private val screenNavigator: ScreenNavigator by currentScope.inject { parametersOf(this) }

    override fun getActivityLayout(): Int {
        return R.layout.mainscreen_activity
    }

    override fun getActivityPresenter(): BasePresenter {
        return presenter
    }

    override fun onActivityReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onActivityReady(savedInstanceState, intent)

        presenter.initialize()
    }

    override fun redirectToLastScreen(screenId: String) {
        if (screenId.isEmpty() || this::class.getFullName() == screenId) {
            return
        }

        val lastScreenActivity = Class.forName(screenId)
        startActivity(Intent(this, lastScreenActivity))
    }

    override fun displayUserLastVisited(date: String) {
        mainScreenTextViewLastVisited.text =
            String.format(resources.getString(R.string.mainscreen_last_visited, date))
    }

    override fun displayItunesContent(itunesResults: List<ItunesContentResults>) {
        mainScreenRecyclerView.layoutManager = LinearLayoutManager(this)

        mainScreenRecyclerView.adapter =
            MainScreenAdapter(
                itunesResults = itunesResults,
                onItunesContentClick = { item -> onItunesContentClick(item) })
    }

    private fun onItunesContentClick(itunesItem: ItunesContentResults) {
        presenter.onItunesContentClick(itunesItem)
    }

    override fun redirectToItunesDetailsScreen(itunesItem: ItunesContentResults) {
        screenNavigator.redirectToItunesDetailsScreen(itunesItem)
    }

    override fun showLoading() {
        showLoadingDialog()
    }

    override fun hideLoading() {
        hideLoadingDialog()
    }

    override fun showMessage(message: String) {
        showToast(message)
    }
}