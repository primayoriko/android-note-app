package my.primayoriko.mynote.util

import androidx.room.TypeConverter
import my.primayoriko.mynote.domain.Note.NoteType
import java.util.*

class Converter {

    @TypeConverter
    fun toDate(dateLong: Long?): Date? = dateLong?.let { Date(it) }

    @TypeConverter
    fun fromDate(date: Date?): Long? = date?.time

    @TypeConverter
    fun toNoteType(value: String) = enumValueOf<NoteType>(value)

    @TypeConverter
    fun fromNoteType(value: NoteType) = value.name

}