package ir.jaShakouri.data.api.config

import io.reactivex.Observable
import io.reactivex.functions.Function
import ir.jaShakouri.domain.AppKeys
import java.util.concurrent.TimeUnit

class RetryWithDelay : Function<Observable<out Throwable?>?, Observable<*>?> {

    constructor() {

    }

    constructor(retryCount: Int, retryDelay: Int) {
        AppKeys.RetryCount = retryCount
        AppKeys.RetryDelay = retryDelay
    }

    private var retryCount = 0
    override fun apply(attempts: Observable<out Throwable?>): Observable<*> {
        return attempts.flatMap { throwable ->
            if (++retryCount < AppKeys.RetryCount) {
                Observable.timer(
                    AppKeys.RetryDelay.toLong(),
                    TimeUnit.MILLISECONDS
                )
            } else Observable.error<Any>(throwable)
        }
    }
}