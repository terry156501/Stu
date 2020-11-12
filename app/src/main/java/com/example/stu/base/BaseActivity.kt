package com.example.stu.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.stu.utils.ZTLUtils

/**
 * Created By Terry on 2019/9/10
 */
open class BaseActivity : AppCompatActivity() {
    lateinit var mActivity: AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivity = this
        ZTLUtils(mActivity).setTranslucentStatus()
    }
}
