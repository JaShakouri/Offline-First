package ir.jaShakouri.tuturial.model

class User constructor(var fullName: String, var phone: String) {

    override fun toString(): String {
        return "$fullName\n$phone"
    }

}