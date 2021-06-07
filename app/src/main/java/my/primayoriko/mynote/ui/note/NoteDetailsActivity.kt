package my.primayoriko.mynote.ui.note

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import my.primayoriko.mynote.R
import my.primayoriko.mynote.databinding.ActivityNoteDetailsBinding
import my.primayoriko.mynote.domain.Note
import my.primayoriko.mynote.domain.Note.NoteType
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class NoteDetailsActivity : AppCompatActivity() {

    private val viewModel: NoteViewModel by viewModels()

    private lateinit var note: Note
    private lateinit var binding: ActivityNoteDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        note = intent.getParcelableExtra("note")!!
        binding = ActivityNoteDetailsBinding.inflate(layoutInflater)

        val view = binding.root
        val formatter = SimpleDateFormat("E, dd MMM yyyy")
        val colorDrawable =
            ColorDrawable(ContextCompat.getColor(view.context, R.color.color_primary))

        setContentView(view)
        updateQuoteImg()

        supportActionBar?.setBackgroundDrawable(colorDrawable)

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
        binding.btnUpdate.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(view.context, R.style.alert_dialog)
                .setTitle("Update")
                .setIcon(R.drawable.ic_baseline_save_alt_24)
                .setMessage("Update this note?")
                .setPositiveButton("Yes") { _, _ ->
                    val toast = Toast.makeText(view.context, "Note data updated successfully", Toast.LENGTH_LONG)
                    updateNote()
                    updateQuoteImg()
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                }
                .setNegativeButton("No") { _, _ ->  }
                .create()
            dialog.show()
        }
        binding.btnDelete.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(view.context, R.style.alert_dialog)
                .setTitle("Delete")
                .setIcon(R.drawable.ic_baseline_delete_24)
                .setMessage("Delete this note?")
                .setPositiveButton("Yes") { _, _ ->
                    val toast = Toast.makeText(view.context, "Note data deleted successfully", Toast.LENGTH_LONG)
                    viewModel.delete(note)
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    navigateBack()
                }
                .setNegativeButton("No") { _, _ ->  }
                .create()
            dialog.show()
        }
        binding.btnBack.setOnClickListener {
            navigateBack()
        }
    }

    private fun updateQuoteImg() {
        val view = binding.root
        val imageId =
            when(note.type) {
                NoteType.WORK -> R.drawable.work
                NoteType.STUDY -> R.drawable.study
                else -> R.drawable.life
            }
        Glide.with(view.context).load(imageId).into(binding.ivQuote)
    }

    private fun updateNote() {
        note.title = binding.etTitle.text.toString()
        note.content = binding.etContent.text.toString()
        note.updatedTime = Calendar.getInstance().time
        note.isFavourite = binding.rgIsFavourite.checkedRadioButtonId == binding.rYes.id
        note.type =
            when (binding.rgNoteType.checkedRadioButtonId) {
                binding.rNoteTypeStudy.id -> NoteType.STUDY
                binding.rNoteTypeWork.id -> NoteType.WORK
                else -> NoteType.OTHER
            }
        viewModel.update(note)
    }

    private fun navigateBack() {
        val view = binding.root
        val intent = Intent(view.context, NoteActivity::class.java)
        view.context.startActivity(intent)
    }

}