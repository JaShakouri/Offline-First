package ir.jaShakouri.tuturial.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import ir.jaShakouri.tuturial.R
import ir.jaShakouri.tuturial.databinding.ActivityMainBinding
import ir.jaShakouri.tuturial.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {

    private val TAG = "MVVM_MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding: ActivityMainBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_main)
        val userViewModel = UserViewModel(this)
        activityMainBinding.user = userViewModel

    }

}