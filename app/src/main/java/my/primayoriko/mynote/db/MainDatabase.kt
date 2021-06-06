package my.primayoriko.mynote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import my.primayoriko.mynote.db.dao.NoteDao
import my.primayoriko.mynote.domain.Note
import my.primayoriko.mynote.util.Converter

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(Converter::class)
abstract class MainDatabase: RoomDatabase() {

    abstract fun getNoteDao(): NoteDao

}