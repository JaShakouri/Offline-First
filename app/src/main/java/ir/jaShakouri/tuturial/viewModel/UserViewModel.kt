package ir.jaShakouri.tuturial.viewModel

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import ir.jaShakouri.tuturial.BR
import ir.jaShakouri.tuturial.model.User
import ir.jaShakouri.tuturial.view.adapter.UserAdapter

class UserViewModel : BaseObservable {

    private val TAG = "MVVM_UserViewModel"

    private var name = ""
    private var phone = ""
    private var context: Context? = null

    var liveDataUserList = MutableLiveData<List<UserViewModel>>()
    private var list = ArrayList<UserViewModel>()

    constructor(user: User) {
        this.name = user.fullName
        this.phone = user.phone
    }

    constructor(context: Context?) {
        this.context = context

        for (i in 1..15) {
            val user = User("User $i", i.toString())
            list.add(UserViewModel(user))
        }

        liveDataUserList.value = list

    }

    @Bindable
    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
        notifyPropertyChanged(BR.name)
        Log.i(TAG, "setName: $name")
    }

    @Bindable
    fun getPhone(): String {
        return phone
    }

    fun setPhone(phone: String) {
        this.phone = phone
        notifyPropertyChanged(BR.phone)
        Log.i(TAG, "setPhone: $phone")
    }

    companion object {

        var adapter: UserAdapter? = null

        @JvmStatic
        @BindingAdapter("bind:RecyclerViewUser")
        fun recyclerViewBinder(
            rv: RecyclerView,
            listLiveData: MutableLiveData<List<UserViewModel>>
        ) {

            listLiveData.observe((rv.context as LifecycleOwner), Observer {

                if (adapter == null) {
                    adapter = UserAdapter(it)
                    rv.adapter = adapter
                }

                adapter!!.notifyDataSetChanged()

            })

        }

    }

}