package my.primayoriko.mynote.ui.note

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import my.primayoriko.mynote.repository.NoteRepository

class NoteViewModel @ViewModelInject constructor(
    val noteRepository: NoteRepository
): ViewModel() {

}