package my.primayoriko.mynote.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import my.primayoriko.mynote.domain.Note

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Update
    suspend fun update(note: Note)

    @Query("SELECT * FROM note WHERE id = :id")
    fun getById(id: Int): LiveData<Note>

    @Query("SELECT * FROM note ORDER BY updatedTime DESC")
    fun getAllSortedByUpdatedTime(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE isFavourite = 1 ORDER BY updatedTime DESC")
    fun getAllFavouriteSortedByUpdatedTime(): LiveData<List<Note>>

}