package my.primayoriko.mynote.ui.note

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import my.primayoriko.mynote.R
import my.primayoriko.mynote.databinding.ActivityNoteDetailsBinding
import my.primayoriko.mynote.domain.Note
import my.primayoriko.mynote.domain.Note.NoteType
import java.text.SimpleDateFormat

class NoteDetailsActivity : AppCompatActivity() {

    private lateinit var note: Note
    private lateinit var binding: ActivityNoteDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val formatter = SimpleDateFormat("E, dd MMM yyyy")

        note = intent.getParcelableExtra("note")!!
        binding = ActivityNoteDetailsBinding.inflate(layoutInflater)

        val view = binding.root

        setContentView(view)

        binding.etTitle.setText(note.title)
        binding.etContent.setText(note.content)
        binding.etCreatedTime.setText(formatter.format(note.createdTime))
        binding.etUpdatedTime.setText(formatter.format(note.updatedTime))
        binding.rgIsFavourite.check(
            if(note.isFavourite) binding.rYes.id
            else binding.rNo.id
        )
        binding.rgNoteType.check(
            when(note.type) {
                NoteType.WORK -> binding.rNoteTypeWork.id
                NoteType.STUDY -> binding.rNoteTypeStudy.id
                else -> binding.rNoteTypeOther.id
            }
        )

        val imageId =
            when(note.type) {
                NoteType.WORK -> R.drawable.work
                NoteType.STUDY -> R.drawable.study
                else -> R.drawable.life
            }
        Glide.with(view.context).load(imageId).into(binding.ivQuote)
    }
}