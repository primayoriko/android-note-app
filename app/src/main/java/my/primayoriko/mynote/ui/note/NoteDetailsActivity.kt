package my.primayoriko.mynote.ui.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.primayoriko.mynote.databinding.ActivityNoteDetailsBinding
import my.primayoriko.mynote.domain.Note

class NoteDetailsActivity : AppCompatActivity() {

    private lateinit var note: Note
    private lateinit var binding: ActivityNoteDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        note = intent.getParcelableExtra("note")!!
        binding = ActivityNoteDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)
//        setSupportActionBar(binding.toolbar)

        binding.etTitle.setText(note.title)
        binding.etContent.setText(note.content)
    }
}