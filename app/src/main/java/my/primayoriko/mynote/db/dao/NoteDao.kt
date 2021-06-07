package my.primayoriko.mynote.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
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
    fun getById(id: Int): Flow<Note>

    @Query("SELECT * FROM note as n " +
            "WHERE n.isFavourite = :isFavourite or :isFavourite IS NULL " +
            "ORDER BY updatedTime DESC")
    fun getAllSortedByUpdatedTime(isFavourite: Boolean?): Flow<List<Note>>

//    @Query("SELECT * FROM note WHERE isFavourite = 1 ORDER BY updatedTime DESC")
//    fun getAllFavouriteSortedByUpdatedTime(): LiveData<List<Note>>

}