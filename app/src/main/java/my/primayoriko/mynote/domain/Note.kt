package my.primayoriko.mynote.domain

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

//@SuppressLint("ParcelCreator")
@Entity(tableName = "note")
@Parcelize
data class Note(
    var title: String,
    var content: String,
    var type: NoteType,
    var isFavourite: Boolean = false,
    val createdTime: Date = Calendar.getInstance().time,
    var updatedTime: Date = Calendar.getInstance().time,
    @PrimaryKey(autoGenerate = true) var id: Int? = null
) : Parcelable {

    enum class NoteType {
        WORK, STUDY, OTHER
    }
    
}
