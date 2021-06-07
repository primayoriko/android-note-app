package my.primayoriko.mynote.ui.note

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import my.primayoriko.mynote.domain.Note
import my.primayoriko.mynote.repository.NoteRepository
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    val noteRepository: NoteRepository
) : ViewModel() {

    var noteList: LiveData<List<Note>> =
        noteRepository.getAllSortedByUpdatedTime(null).asLiveData()

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

    fun get(id: Int): Note? = noteRepository.getById(id).asLiveData().value

    fun getAll(isFavourite: Boolean?): List<Note>? =
        noteRepository.getAllSortedByUpdatedTime(isFavourite).asLiveData().value

}