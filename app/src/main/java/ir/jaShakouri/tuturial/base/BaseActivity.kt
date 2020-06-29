package ir.jaShakouri.tuturial.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun layout(): Int

    abstract fun init()

    abstract fun view(): BaseView

    abstract fun presenter(): BasePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout())
        init()
    }

    override fun onStart() {
        super.onStart()
        presenter().attachView(view())
    }

    override fun onStop() {
        super.onStop()
        presenter().detachView()
    }


}