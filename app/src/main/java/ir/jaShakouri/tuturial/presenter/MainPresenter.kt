package ir.jaShakouri.tuturial.presenter

import ir.jaShakouri.tuturial.model.User

class MainPresenter constructor(private val view: View) {

    private val user = User()

    fun updateFullName(fullName: String) {
        user.fullName = fullName
        view.updateView(user.toString())
    }

    fun updateEmail(email: String) {
        user.email = email
        view.updateView(user.toString())
    }

    interface View {
        fun updateView(info: String)
        fun showProgress()
        fun hideProgress()
    }

}