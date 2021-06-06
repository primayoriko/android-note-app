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
import my.primayoriko.mynote.R
import my.primayoriko.mynote.adapter.NoteAdapter
import my.primayoriko.mynote.databinding.FragmentNoteListBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class NoteListFragment: Fragment() {

    private val viewModel: NoteViewModel by viewModels()
    private var _binding: FragmentNoteListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
        val gridColumnCount = if(isLandscape) 2 else 1

        binding.rvNote.layoutManager = GridLayoutManager(context, gridColumnCount)
        viewModel.noteList.observe(viewLifecycleOwner,
            { list ->
                binding.rvNote.adapter = NoteAdapter(list)
            })
        binding.fabAddNote.setOnClickListener {
            findNavController().navigate(R.id.action_NoteListFragment_to_NoteCreatorFragment)
        }
//        binding.fabAddNote.setOnClickListener {
//            viewModel.favouriteOnly = !viewModel.favouriteOnly
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}