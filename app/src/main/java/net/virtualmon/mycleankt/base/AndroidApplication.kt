package net.virtualmon.mycleankt.base

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.virtualmon.mycleankt.injection.components.DaggerApplicationComponent
import javax.inject.Inject

/**
 * Created by AlbertM on 08/04/19.
 */
class AndroidApplication : Application(), HasActivityInjector {

    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }
}