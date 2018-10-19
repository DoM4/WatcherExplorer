package com.domenicoaumenta.watcherexplorer.watcher

import com.domenicoaumenta.puc.ui.BaseContract
import com.domenicoaumenta.watcherexplorer.model.RepoObject
import com.domenicoaumenta.watcherexplorer.model.RepoOwner
import com.domenicoaumenta.watcherexplorer.model.RepoOwnerResponse


/**
 * Created by domenicoaumenta on 14/10/2018.
 */
class WatcherContract{
    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<RepoOwner>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadWatchers(repoOwner : String, repoName : String)
    }}