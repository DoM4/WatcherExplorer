package com.domenicoaumenta.watcherexplorer.repositories

import android.util.Log
import com.domenicoaumenta.watcherexplorer.model.RepositoriesResponse
import com.domenicoaumenta.watcherexplorer.network.GitHubWatcherAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * Created by domenicoaumenta on 14/10/2018.
 */
class RepositoriesPresenter : RepositoriesContract.Presenter {
    override fun attach(view: RepositoriesContract.View) {
        this.view = view
    }

    private val subscriptions = CompositeDisposable()
    private val api: GitHubWatcherAPI = GitHubWatcherAPI.create()
    private lateinit var view: RepositoriesContract.View

    override fun loadData(keyword : String) {
        Log.d("RepositoriesPresenter","loadData : $keyword")
        view.showProgress(true)
        var subscribe = api.searchRepositoriesByKeyword(keyword).subscribeOn(Schedulers.io())
                .debounce(300, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ dataResponse: RepositoriesResponse? ->
                    view.showProgress(false)
                    view.loadDataSuccess(dataResponse!!.items)
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

}