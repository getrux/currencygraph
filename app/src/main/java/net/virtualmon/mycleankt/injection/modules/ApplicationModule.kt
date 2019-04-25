package net.virtualmon.mycleankt.injection.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import net.virtualmon.data.ApplicationContext
import net.virtualmon.data.datasource.CurrencyApiDataSource
import net.virtualmon.domain.interactors.GetCurrencyUseCase
import net.virtualmon.domain.interactors.GetCurrencyUseCaseImpl
import net.virtualmon.domain.repository.EuroDollarRepository
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by AlbertM on 24/04/19.
 */
@Module
class ApplicationModule {

    @Provides
    @Singleton
    @ApplicationContext
    internal fun provideContext(application: Application): Context {
        return application
    }

    @Provides
    @Singleton
    internal fun provideDispatcher() : Dispatchers {
        return Dispatchers

    }

    @Provides
    @Singleton
    @Named("main")
    internal fun provideMainCortineDispatcher(mainCoroutineDispatcher: Dispatchers) : CoroutineDispatcher {
        return mainCoroutineDispatcher.Main

    }
    @Provides
    @Singleton
    @Named("back")
    internal fun provideBackgroundCortineDispatcher(backgroundCoroutineDispatcher: Dispatchers) : CoroutineDispatcher {
        return backgroundCoroutineDispatcher.IO

    }

    @Provides
    @Singleton
    internal fun provideCurrencyRepository(currencyRepository: CurrencyApiDataSource): EuroDollarRepository {
        return currencyRepository

    }

    @Provides
    @Singleton
    internal fun provideGetCurrencyUseCase(getcurrentCurrencyUseCase: GetCurrencyUseCaseImpl) : GetCurrencyUseCase
    {
        return getcurrentCurrencyUseCase
    }



}