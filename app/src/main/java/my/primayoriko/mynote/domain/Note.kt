package my.primayoriko.mynote.domain

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "note")
data class Note(
    val title: String,
    val content: String,
    val createdTime: Date = Calendar.getInstance().time,
    var updatedTime: Date = Calendar.getInstance().time,
    var isFavourite: Boolean = false
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}
