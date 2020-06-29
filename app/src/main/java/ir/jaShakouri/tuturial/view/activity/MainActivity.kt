package ir.jaShakouri.tuturial.view.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import ir.jaShakouri.tuturial.R
import ir.jaShakouri.tuturial.presenter.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val presenter = MainPresenter(this)
        presenter.updateFullName("javad shakouri")
        presenter.updateEmail("jashakouri@gmail.com")

    }

    override fun updateView(info: String) {
        main_tvTitle.text = info
    }

    override fun showProgress() {
        Log.i(TAG, "showProgress")
    }

    override fun hideProgress() {
        Log.i(TAG, "hideProgress")
    }


}