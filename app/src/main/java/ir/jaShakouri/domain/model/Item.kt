package ir.jaShakouri.domain.model

import androidx.annotation.NonNull
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import ir.jaShakouri.app.BR
import ir.jaShakouri.data.local.dataBase.converter.VenueConverter
import ir.jaShakouri.domain.AppKeys
import java.io.Serializable

@Entity(tableName = AppKeys.Item_TableName)
class Item : BaseObservable(), Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "referralId")
    @SerializedName("referralId")
    var referralId: String? = null
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.referralId)
        }

    @ColumnInfo(name = "reasonName")
    @SerializedName("reasonName")
    var reasonName: String? = null
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.reasonName)
        }

    @ColumnInfo(name = "summary")
    @SerializedName("summary")
    var summary: String? = null
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.summary)
        }

    @ColumnInfo(name = "type")
    @SerializedName("type")
    var type: String? = null
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.type)
        }


    @TypeConverters(VenueConverter::class)
    @ColumnInfo(name = "venue")
    @SerializedName("venue")
    var venue: Venue? = null
        @Bindable
        set(value) {
            field = value
            notifyPropertyChanged(BR.venue)
        }

}