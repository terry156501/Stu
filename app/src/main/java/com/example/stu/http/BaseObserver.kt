package com.example.stu.http

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class BaseObserver<T> : Observer<T> {
    override fun onSubscribe(d: Disposable) {

    }
    override fun onError(e: Throwable) {
        onFailure(e);
        onEnd();
    }

    override fun onNext(t: T) {
        onSuccess(t);
    }

    override fun onComplete() {
        onEnd();
    }

    abstract fun onEnd();
    abstract fun onSuccess(data : T);
    abstract fun onFailure(msg : Throwable?);

}