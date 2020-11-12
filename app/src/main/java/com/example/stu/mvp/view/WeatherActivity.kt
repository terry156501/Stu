package com.example.stu.mvp.view

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.stu.R
import com.example.stu.adapter.WeatherAdapter
import com.example.stu.base.BaseActivity
import com.example.stu.entity.WeatherResponse
import com.example.stu.mvp.contect.WeatherContect
import com.example.stu.mvp.presenter.WeatherPresenter
import kotlinx.android.synthetic.main.activity_3dweather.*

@Suppress("DEPRECATION")
class WeatherActivity : BaseActivity(), WeatherContect.IView{

    var presenter:WeatherPresenter ?= null
    var data:ArrayList<WeatherResponse.DailyBean> ?= null
    var adapter:WeatherAdapter ?=null
    var progressDialog: ProgressDialog?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_3dweather)
    }

    init {
        if(presenter == null){
            presenter = WeatherPresenter(this)
        }
        presenter?.getWeather3d();
    }

    override val curContext: Context = this

    override fun showProgress() {
        progressDialog = ProgressDialog.show(this,"提示","加载中...")
    }

    override fun hideProgress() {
        progressDialog?.hide()
    }

    override fun showData(weather3d: List<WeatherResponse.DailyBean>) {
        if(data==null){
            data = ArrayList();
        }
        data?.clear();
        data?.addAll(weather3d)
        rvWeather.layoutManager = LinearLayoutManager(this);
        if(adapter == null){
            adapter = WeatherAdapter(this,data);
            rvWeather.adapter = adapter;
        }else{
            adapter?.notifyDataSetChanged();
        }
    }

    override fun showInfo(info: String) {
        TODO("Not yet implemented")
    }
}