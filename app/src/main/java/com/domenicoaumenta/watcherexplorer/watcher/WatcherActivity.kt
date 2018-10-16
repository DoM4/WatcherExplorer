package com.domenicoaumenta.watcherexplorer.watcher

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.domenicoaumenta.watcherexplorer.R
import com.domenicoaumenta.watcherexplorer.di.component.DaggerActivityComponent
import com.domenicoaumenta.watcherexplorer.di.module.ActivityModule
import com.domenicoaumenta.watcherexplorer.model.RepoOwner
import javax.inject.Inject


/**
 * Created by domenicoaumenta on 14/10/2018.
 */
class WatcherActivity : AppCompatActivity(),WatcherContract.View{
    override fun showProgress(show: Boolean) {
    }

    override fun showErrorMessage(error: String) {
    }

    override fun loadDataSuccess(list: List<RepoOwner>) {
        Log.d("",list.toString())
    }

    @Inject lateinit var watcherPresenter: WatcherContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchers)

        injectDependency()
//        setupRepoLayout()

        watcherPresenter.attach(this)
        watcherPresenter.subscribe()

        watcherPresenter.loadWatchers("hongyangAndroid","AndroidAutoLayout")
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }
}