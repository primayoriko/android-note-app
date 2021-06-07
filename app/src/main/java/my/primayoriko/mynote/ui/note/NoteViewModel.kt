package my.primayoriko.mynote.ui.note

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import my.primayoriko.mynote.domain.Note
import my.primayoriko.mynote.repository.NoteRepository
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    val noteRepository: NoteRepository
) : ViewModel() {

    var noteList: LiveData<List<Note>> = getAll(false)
    var favouriteOnly: Boolean = false
        get() = field
        set(value) {
            field = value
            noteList = getAll(value)
        }

    fun insert(note: Note) =
        viewModelScope.launch {
            noteRepository.insert(note)
        }

    fun update(note: Note) =
        viewModelScope.launch {
            noteRepository.update(note)
        }

    fun delete(note: Note) =
        viewModelScope.launch {
            noteRepository.delete(note)
        }

    fun get(id: Int): LiveData<Note> = noteRepository.getById(id)

    fun getAll(favouriteOnly: Boolean): LiveData<List<Note>> =
        if (favouriteOnly) noteRepository.getAllFavouriteSortedByUpdatedTime()
        else noteRepository.getAllSortedByUpdatedTime()

//    fun getAll(favouriteOnly: Boolean): LiveData<List<Note>> {
//        var x: LiveData<List<Note>>
//        Timber.d("test1")
//        if (favouriteOnly) {
//            x = noteRepository.getAllFavouriteSortedByUpdatedTime()
//            Timber.d("test2")
//        }
//        else {
//            x = noteRepository.getAllSortedByUpdatedTime()
//            Timber.d("test2")
//        }
//        return x
//    }

}