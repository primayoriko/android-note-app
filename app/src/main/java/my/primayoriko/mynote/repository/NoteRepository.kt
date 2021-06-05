package my.primayoriko.mynote.repository

import my.primayoriko.mynote.db.dao.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(
    val noteDao: NoteDao
) {

}