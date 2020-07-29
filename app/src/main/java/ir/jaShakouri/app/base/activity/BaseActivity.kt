package ir.jaShakouri.app.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
abstract class BaseActivity : AppCompatActivity() {

    abstract fun init()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

}