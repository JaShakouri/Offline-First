package ir.jaShakouri.app.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract fun viewModel()
    abstract fun attach()
    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel()
        super.onCreate(savedInstanceState)
        init()
        attach()
    }

}