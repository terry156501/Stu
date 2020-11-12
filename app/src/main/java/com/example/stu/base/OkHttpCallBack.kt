package com.example.stu.base

interface OkHttpCallBack<T> {
    fun onSuccess(t:T);

    fun onFailure(msg:String);
}