package my.primayoriko.mynote.adapter

import android.content.Intent
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import my.primayoriko.mynote.R
import my.primayoriko.mynote.databinding.ItemNoteBinding
import my.primayoriko.mynote.domain.Note
import my.primayoriko.mynote.ui.note.NoteDetailsActivity
import my.primayoriko.mynote.util.Converter
import timber.log.Timber

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
                val converter = Converter()
                val binding: ItemNoteBinding = ItemNoteBinding.bind(view)
                note = n

                binding.tvTitle.text = note.title
                binding.tvDate.text = note.updatedTime.toString()

                // Button interaction
                view.setOnClickListener {
                    val intent = Intent(view.context, NoteDetailsActivity::class.java)
                    intent.putExtra("note", note)
                    view.context.startActivity(intent)
                }

                binding.btnFavourite.setOnClickListener {
//                    val toast = Toast.makeText(context, "Clicked!!", Toast.LENGTH_LONG)
//
//                    toast.setGravity(Gravity.CENTER, 0, 0)
//                    toast.show()
                    Timber.d("1test\n1test\n1test\n1test\n")
                }

            }

        }

}