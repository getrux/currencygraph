package net.virtualmon.mycleankt.injection.modules

import dagger.Module
import dagger.Provides
import net.virtualmon.mycleankt.CurrencyMain.CurrencyListView
import net.virtualmon.mycleankt.CurrencyMain.MainActivity

/**
 * Created by AlbertM on 28/11/17.
 */
@Module
class MainActivityModule {

    @Provides
    internal fun provideMainActivity(mainActivity: MainActivity): CurrencyListView = mainActivity
}