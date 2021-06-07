package my.primayoriko.mynote.ui.note

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import my.primayoriko.mynote.R
import my.primayoriko.mynote.adapter.NoteAdapter
import my.primayoriko.mynote.databinding.FragmentNoteListBinding
import my.primayoriko.mynote.domain.Note
import my.primayoriko.mynote.domain.Note.NoteType
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class NoteListFragment : Fragment() {

    private val viewModel: NoteViewModel by viewModels()
    private var _binding: FragmentNoteListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("test0a")
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("test0b")

        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        val gridColumnCount = if(isLandscape) 2 else 1

        binding.rvNote.layoutManager = GridLayoutManager(context, gridColumnCount)

        Timber.d("test0c")

        val note1 = Note(
            "uhuhu",
            "qwerty123",
            NoteType.STUDY
        )
        val note2 = Note(
            "ahah sad sads sadas sadda",
            "shoot123",
            NoteType.WORK
        )
        val note3 = Note(
            "qweqweqsadsadsadsadsa",
            "7637 123830921 91283++",
            NoteType.OTHER
        )
        val myList: List<Note> = listOf(note1, note2, note3)
        binding.rvNote.adapter = NoteAdapter(myList)
//        viewModel.noteList.observe(viewLifecycleOwner,
//            { list ->
//                Timber.d("testPost")
//
//                binding.rvNote.adapter = NoteAdapter(list)
//            })
        binding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_NoteListFragment_to_NoteCreatorFragment)
        }
        binding.rgViewMode.setOnCheckedChangeListener { _, id ->
            if(id == binding.rAll.id) {
                binding.rvNote.adapter = NoteAdapter(myList)
                val data = viewModel.getAll(false)
                Timber.d(data.value.toString())
            } else {
                binding.rvNote.adapter = NoteAdapter(myList.filter { it.isFavourite })
                val data = viewModel.getAll(true)
                Timber.d(data.value.toString())
            }
        }
//        binding.btnViewMode.setOnClickListener {
//            viewModel.favouriteOnly = !viewModel.favouriteOnly
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}