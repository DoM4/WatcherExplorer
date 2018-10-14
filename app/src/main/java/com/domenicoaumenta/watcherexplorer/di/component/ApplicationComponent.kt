package com.domenicoaumenta.watcherexplorer.di.component

import com.domenicoaumenta.watcherexplorer.BaseApp
import com.domenicoaumenta.watcherexplorer.di.module.ApplicationModule
import dagger.Component


/**
 * Created by domenicoaumenta on 14/10/2018.
 */
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: BaseApp)

}