package my.primayoriko.mynote.db

import androidx.room.Database
import androidx.room.RoomDatabase
import my.primayoriko.mynote.domain.Note

@Database(
    entities = [Note::class],
    version = 1
)
abstract class MainDatabase: RoomDatabase() {

}