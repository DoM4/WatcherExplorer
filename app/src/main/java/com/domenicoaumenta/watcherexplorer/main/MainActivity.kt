package com.domenicoaumenta.watcherexplorer.main

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.domenicoaumenta.watcherexplorer.BaseActivity
import com.domenicoaumenta.watcherexplorer.R
import com.domenicoaumenta.watcherexplorer.di.component.DaggerActivityComponent
import com.domenicoaumenta.watcherexplorer.di.module.ActivityModule
import com.domenicoaumenta.watcherexplorer.model.RepoObject
import com.domenicoaumenta.watcherexplorer.repositories.RepositoriesContract
import com.domenicoaumenta.watcherexplorer.utils.action
import com.domenicoaumenta.watcherexplorer.utils.isVisible
import com.domenicoaumenta.watcherexplorer.utils.snack
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseActivity(), RepositoriesContract.View {

    override fun disconnected() {
        mainActivityContainer.snack("Oh no! No Internet connection"){
            action("Retry"){
                repositoriesPresenter.loadData(searchReposEditText.text.toString())
            }
        }
    }

    //we leave this method empty as we trigger the research with the button
    override fun connected() {
    }

    @Inject
    lateinit var repositoriesPresenter : RepositoriesContract.Presenter

    private lateinit var repoAdapter: RepoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        injectDependency()
        setupRepoLayout()

        repositoriesPresenter.attach(this)
        repositoriesPresenter.subscribe()

        searchReposEditText.setOnEditorActionListener(TextView.OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                repositoriesPresenter.loadData(searchReposEditText.text.toString())
                return@OnEditorActionListener true
            }
            false
        })    }

    override fun showProgress(show: Boolean) {
        progressBar.isVisible = show
        }

    override fun showErrorMessage(error: String) {
       mainActivityContainer.snack(error,Snackbar.LENGTH_SHORT){
           action("Retry"){
               repositoriesPresenter.loadData(searchReposEditText.text.toString())
           }
       }
    }

    override fun loadDataSuccess(list: List<RepoObject>) {
        Log.d("",list.toString())
        repoAdapter.repoList = list
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
            .activityModule(ActivityModule(this))
            .build()

        activityComponent.inject(this)
    }

    private fun setupRepoLayout(){
        // Creates a vertical Layout Manager
        reposList.layoutManager = LinearLayoutManager(this)

        repoAdapter = RepoAdapter(this,null)

        // Access the RecyclerView Adapter and load the data into it
        reposList.adapter = repoAdapter
    }

}
