package com.example.stu

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.stu.http.BaseObserver
import com.example.stu.http.HttpMethods
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        load?.setOnClickListener{
            getWeather(101190201,"59078b481b6d4b11a4553eeb815b68a8")
        }
    }

    private fun getWeather(local:Int, id:String) {
        runRxLambda(HttpMethods.getHttpMethods().getHttpAPI().getWeather(local, id), {

        }, {
            it?.printStackTrace()
        })
    }

    fun <T> runRxLambda(observable: Observable<T>, next: (T) -> Unit, error: (e: Throwable?) -> Unit, completed: () -> Unit = { Log.e("completed", "completed") }) {
        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : BaseObserver<T>() {
                override fun onEnd() {

                }

                override fun onSuccess(data: T) {

                }

                override fun onFailure(msg: Throwable?) {

                }
            })
    }
}

