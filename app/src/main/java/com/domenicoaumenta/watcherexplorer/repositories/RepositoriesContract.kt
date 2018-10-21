package com.domenicoaumenta.watcherexplorer.repositories


import com.domenicoaumenta.puc.ui.BaseContract
import com.domenicoaumenta.watcherexplorer.model.RepoObject


/**
 * Created by domenicoaumenta on 14/10/2018.
 */
class RepositoriesContract {
    interface View: BaseContract.View {
    fun showProgress(show: Boolean)
    fun showErrorMessage(error: String)
    fun loadDataSuccess(list: List<RepoObject>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData(keyword : String)
    }}