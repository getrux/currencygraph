package net.virtualmon.mycleankt.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

abstract class BasePresenter( private val  mainDispatcher: CoroutineDispatcher, private val backgroundDispatcher: CoroutineDispatcher) {

    private val job = Job()
    protected val uiScope = CoroutineScope(mainDispatcher + job)
    protected val backgroundScope = CoroutineScope(backgroundDispatcher + job)

    fun onStop() {
        job.cancel()
    }

}