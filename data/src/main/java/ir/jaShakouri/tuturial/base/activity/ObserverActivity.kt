package ir.jaShakouri.tuturial.base.activity

abstract class ObserverActivity : BaseActivity() {

    private var loaded = false

    abstract fun subscribe()

    override fun onStart() {
        super.onStart()

        if (!loaded) {
            loaded = true
            subscribe()
        }

    }

}