package ir.jaShakouri.tuturial.model

class User {

    var fullName: String = ""
    var email: String = ""

    override fun toString(): String {
        return "$fullName\n$email"
    }

}