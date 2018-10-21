package com.domenicoaumenta.watcherexplorer.watcher

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.MenuItem
import com.domenicoaumenta.watcherexplorer.BaseActivity
import com.domenicoaumenta.watcherexplorer.R
import com.domenicoaumenta.watcherexplorer.di.component.DaggerActivityComponent
import com.domenicoaumenta.watcherexplorer.di.module.ActivityModule
import com.domenicoaumenta.watcherexplorer.model.RepoObject
import com.domenicoaumenta.watcherexplorer.model.RepoOwner
import com.domenicoaumenta.watcherexplorer.utils.action
import com.domenicoaumenta.watcherexplorer.utils.isVisible
import com.domenicoaumenta.watcherexplorer.utils.snack
import kotlinx.android.synthetic.main.activity_watchers.*
import javax.inject.Inject


/**
 * Created by domenicoaumenta on 14/10/2018.
 */

const val REPO_OBJECT = "REPO_OBJECT"

class WatcherActivity : BaseActivity(),WatcherContract.View{

    private lateinit var repoWatcherAdapter :  RepoWatchersAdapter
    private lateinit var repoObject: RepoObject

    @Inject lateinit var watcherPresenter: WatcherContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_watchers)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        injectDependency()

        setupRepoLayout()

        watcherPresenter.attach(this)
        watcherPresenter.subscribe()

        parseIntent(intent)

    }

    private fun parseIntent(intent: Intent){
        repoObject = intent.getParcelableExtra(REPO_OBJECT)
        watcherPresenter.loadWatchers(repoObject.owner.login,repoObject.name)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                this.finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    private fun setupRepoLayout(){
        // Creates a vertical Layout Manager
        repoWatchersList.layoutManager = LinearLayoutManager(this)

        repoWatcherAdapter = RepoWatchersAdapter(this,null)

        // Access the RecyclerView Adapter and load the data into it
        repoWatchersList.adapter = repoWatcherAdapter
    }

    override fun showProgress(show: Boolean) {
        progressBarWatcher.isVisible = show
    }

    override fun showErrorMessage(error: String) {

    }

    override fun loadDataSuccess(list: List<RepoOwner>) {
        Log.d("",list.toString())
        repoWatcherAdapter.repoList = list
    }

    override fun disconnected() {
        watcherActivityContainer.snack("Oh no! No Internet connection"){
            action("Retry"){
                watcherPresenter.loadWatchers(repoObject.owner.login,repoObject.name)
            }
        }
    }

    override fun connected() {
        watcherPresenter.loadWatchers(repoObject.owner.login,repoObject.name)
    }
}