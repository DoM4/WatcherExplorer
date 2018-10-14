package com.domenicoaumenta.watcherexplorer.di.module

import android.app.Activity
import com.domenicoaumenta.watcherexplorer.network.GitHubWatcherAPI
import com.domenicoaumenta.watcherexplorer.repositories.RepositoriesContract
import com.domenicoaumenta.watcherexplorer.repositories.RepositoriesPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun provideGitHubWatcherAPI() : GitHubWatcherAPI {
        return GitHubWatcherAPI.create()
    }

    @Provides
    fun provideRepositoriesPresenter(): RepositoriesContract.Presenter {
        return RepositoriesPresenter()
    }

}