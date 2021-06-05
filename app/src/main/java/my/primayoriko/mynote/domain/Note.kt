package my.primayoriko.mynote.domain

import java.util.*

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val createdTime: Date = Calendar.getInstance().time,
    var isFavourite: Boolean = false
)
