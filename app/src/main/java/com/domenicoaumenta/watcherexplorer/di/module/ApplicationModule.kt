package com.domenicoaumenta.watcherexplorer.di.module

import android.app.Application
import com.domenicoaumenta.watcherexplorer.BaseApp
import com.domenicoaumenta.watcherexplorer.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by domenicoaumenta on 14/10/2018.
 */
@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}