package com.domenicoaumenta.watcherexplorer.watcher

import com.domenicoaumenta.watcherexplorer.model.RepoOwner
import com.domenicoaumenta.watcherexplorer.network.GitHubWatcherAPI
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * Created by domenicoaumenta on 14/10/2018.
 */
class WatcherPresenter : WatcherContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: GitHubWatcherAPI = GitHubWatcherAPI.create()
    private lateinit var view: WatcherContract.View

    override fun loadWatchers(repoOwner: String, repoName: String) {
        view.showProgress(true)
        var subscribe = api.getWatchersByRepo(repoOwner,repoName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ dataResponse: List<RepoOwner>? ->
                view.showProgress(false)
                view.loadDataSuccess(dataResponse!!)
            }, { error ->
                view.showProgress(false)
                view.showErrorMessage(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun attach(view: WatcherContract.View) {
        this.view = view
    }


    override fun subscribe() {
    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

}
