package net.virtualmon.mycleankt.injection.components

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import net.virtualmon.mycleankt.base.AndroidApplication
import net.virtualmon.mycleankt.injection.ActivityInjector
import net.virtualmon.mycleankt.injection.modules.ApplicationModule
import net.virtualmon.mycleankt.injection.modules.DataModule
import javax.inject.Singleton

/**
 * Created by AlbertM on 20/04/19.
 */
@Singleton
@Component(modules = arrayOf(ApplicationModule::class,
        AndroidInjectionModule::class,
        DataModule::class,
        ActivityInjector::class))
interface ApplicationComponent{

    fun inject(application: AndroidApplication)

    @Component.Builder
    interface  Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }
}