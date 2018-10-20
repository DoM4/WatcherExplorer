package com.domenicoaumenta.watcherexplorer

import android.app.Application
import com.domenicoaumenta.watcherexplorer.di.component.ApplicationComponent
import com.domenicoaumenta.watcherexplorer.di.component.DaggerActivityComponent
import com.domenicoaumenta.watcherexplorer.di.component.DaggerApplicationComponent
import com.domenicoaumenta.watcherexplorer.di.module.ApplicationModule


/**
 * Created by domenicoaumenta on 14/10/2018.
 */
class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    private fun getApplicationComponent(): ApplicationComponent {
        return component
    }


    companion object {
        lateinit var instance: BaseApp private set
    }
}