package ir.jaShakouri.app.base.fragment

abstract class ObserverFragment : BaseFragment() {

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