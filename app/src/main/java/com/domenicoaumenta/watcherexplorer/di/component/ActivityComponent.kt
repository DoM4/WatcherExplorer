package com.domenicoaumenta.watcherexplorer.di.component

import com.domenicoaumenta.watcherexplorer.main.MainActivity
import com.domenicoaumenta.watcherexplorer.di.module.ActivityModule
import dagger.Component


/**
 * Created by domenicoaumenta on 14/10/2018.
 */
@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}