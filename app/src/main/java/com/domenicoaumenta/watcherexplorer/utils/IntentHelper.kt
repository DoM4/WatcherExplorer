package com.domenicoaumenta.watcherexplorer.utils

import android.content.Context
import android.content.Intent
import com.domenicoaumenta.watcherexplorer.model.RepoObject
import com.domenicoaumenta.watcherexplorer.watcher.REPO_OBJECT
import com.domenicoaumenta.watcherexplorer.watcher.WatcherActivity


/**
 * Created by domenicoaumenta on 19/10/2018.
 */
fun Context.launchWatcherActivity(repoObject: RepoObject?) : Intent {
    return Intent(this,WatcherActivity::class.java).apply {
        putExtra(REPO_OBJECT,repoObject)
    }
}