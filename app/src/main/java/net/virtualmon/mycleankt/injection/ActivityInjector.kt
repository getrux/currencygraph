package net.virtualmon.mycleankt.injection

import dagger.Module
import dagger.android.ContributesAndroidInjector
import net.virtualmon.mycleankt.injection.modules.MainActivityModule
import net.virtualmon.mycleankt.injection.scope.PerActivity
import net.virtualmon.mycleankt.CurrencyMain.MainActivity

/**
 * Created by AlbertM on 28/11/17.
 */
@Module
abstract class ActivityInjector {


    @PerActivity
    @ContributesAndroidInjector(modules = arrayOf(MainActivityModule::class))
    abstract fun contributeMainActivityInjector(): MainActivity


}