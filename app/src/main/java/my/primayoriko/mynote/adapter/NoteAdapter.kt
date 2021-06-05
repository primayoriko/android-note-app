package my.primayoriko.mynote.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import my.primayoriko.mynote.R
import my.primayoriko.mynote.databinding.ItemNoteBinding
import my.primayoriko.mynote.domain.Note

class NoteAdapter(private val noteList: List<Note>):
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount(): Int = noteList.size

    class NoteViewHolder(private val view: View):
        RecyclerView.ViewHolder(view) {

            private lateinit var note: Note

            fun bind(n: Note) {
                note = n
                val binding: ItemNoteBinding = ItemNoteBinding.bind(view)

                binding.tvTitle.text = note.title
                binding.tvDate.text = note.updatedTime.toString()

                // Button interaction
            }

        }

}