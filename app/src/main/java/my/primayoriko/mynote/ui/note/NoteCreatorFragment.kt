package my.primayoriko.mynote.ui.note

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import my.primayoriko.mynote.R
import my.primayoriko.mynote.databinding.FragmentNoteCreatorBinding
import my.primayoriko.mynote.domain.Note
import my.primayoriko.mynote.domain.Note.NoteType

@AndroidEntryPoint
class NoteCreatorFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()
    private var _binding: FragmentNoteCreatorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteCreatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(requireContext(), R.style.alert_dialog)
                .setTitle("Save")
                .setIcon(R.drawable.ic_baseline_save_alt_24)
                .setMessage("Save the new note?")
                .setPositiveButton("Yes") { _, _ ->
                    val toast = Toast.makeText(context, "Note data saved successfully", Toast.LENGTH_LONG)
                    saveNote()
                    toast.setGravity(Gravity.CENTER, 0, 0)
                    toast.show()
                    findNavController()
                        .navigate(R.id.action_NoteCreatorFragment_to_NoteListFragment)
                }
                .setNegativeButton("No") { _, _ ->  }
                .create()
            dialog.show()
        }
        binding.btnCancel.setOnClickListener {
            val dialog = MaterialAlertDialogBuilder(requireContext(), R.style.alert_dialog)
                .setTitle("Cancel")
                .setIcon(R.drawable.ic_baseline_cancel_24)
                .setMessage("Are you sure to cancel the creation of new note?")
                .setPositiveButton("Yes") { _, _ ->
                    findNavController()
                        .navigate(R.id.action_NoteCreatorFragment_to_NoteListFragment)
                }
                .setNegativeButton("No") { _, _ ->  }
                .create()
            dialog.show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun saveNote() {
        val title: String = binding.etTitle.text.toString()
        val content: String = binding.etContent.text.toString()
        val isFavourite = binding.rgIsFavourite.checkedRadioButtonId == binding.rYes.id
        val noteType =
            when (binding.rgNoteType.checkedRadioButtonId) {
                binding.rNoteTypeStudy.id -> NoteType.STUDY
                binding.rNoteTypeWork.id -> NoteType.WORK
                else -> NoteType.OTHER
            }
        val note = Note(
            title,
            content,
            noteType,
            isFavourite
        )
        viewModel.insert(note)
    }
}