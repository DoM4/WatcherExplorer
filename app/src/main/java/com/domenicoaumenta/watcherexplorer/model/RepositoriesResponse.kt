package com.domenicoaumenta.watcherexplorer.model


/**
 * Created by domenicoaumenta on 14/10/2018.
 */

data class RepositoriesResponse(val total_count : Int,
                                val incomplete_results : Boolean,
                                val items : List<RepoObject>)