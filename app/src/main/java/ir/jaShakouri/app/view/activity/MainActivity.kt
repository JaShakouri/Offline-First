package ir.jaShakouri.app.view.activity

import androidx.databinding.DataBindingUtil
import ir.jaShakouri.app.R
import ir.jaShakouri.app.base.activity.BaseActivity
import ir.jaShakouri.app.databinding.ActivityMainBinding

class MainActivity : BaseActivity() {

    override fun init() {
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
    }

}