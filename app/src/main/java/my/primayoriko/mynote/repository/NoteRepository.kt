package my.primayoriko.mynote.repository

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import my.primayoriko.mynote.db.dao.NoteDao
import my.primayoriko.mynote.domain.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(
    val noteDao: NoteDao
) {

    suspend fun insert(note: Note) = noteDao.insert(note)

    suspend fun delete(note: Note) = noteDao.delete(note)

    suspend fun update(note: Note) = noteDao.update(note)

    fun getById(id: Int): Flow<Note> = noteDao.getById(id)

    fun getAllSortedByUpdatedTime(isFavourite: Boolean?): Flow<List<Note>> =
        noteDao.getAllSortedByUpdatedTime(isFavourite)

//    fun getAllFavouriteSortedByUpdatedTime(): LiveData<List<Note>> = noteDao.getAllFavouriteSortedByUpdatedTime()

}