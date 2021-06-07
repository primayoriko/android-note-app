package my.primayoriko.mynote.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import my.primayoriko.mynote.R
import my.primayoriko.mynote.databinding.ItemNoteBinding
import my.primayoriko.mynote.domain.Note
import my.primayoriko.mynote.domain.Note.NoteType
import my.primayoriko.mynote.ui.note.NoteDetailsActivity
import java.text.SimpleDateFormat

class NoteAdapter(private val noteList: List<Note>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(noteList[position])
    }

    override fun getItemCount(): Int = noteList.size

    class NoteViewHolder(private val view: View) :
        RecyclerView.ViewHolder(view) {

            private lateinit var note: Note
            private lateinit var binding: ItemNoteBinding

            fun bind(n: Note) {
                val formatter = SimpleDateFormat("E, dd MMM yyyy")

                binding = ItemNoteBinding.bind(view)
                note = n
                binding.tvTitle.text = note.title
                binding.tvDate.text = formatter.format(note.updatedTime)

                val imgId =
                    when(n.type) {
                        NoteType.WORK -> R.drawable.work_icon
                        NoteType.STUDY -> R.drawable.study_icon
                        else -> R.drawable.other_icon
                    }

                updateBtnFavourite()
                Glide.with(view.context).load(imgId).into(binding.ivType)
                view.setOnClickListener {
                    val intent = Intent(view.context, NoteDetailsActivity::class.java)
                    intent.putExtra("note", note)
                    view.context.startActivity(intent)
                }
                binding.btnFavourite.setOnClickListener {
                    note.isFavourite = !note.isFavourite
                    updateBtnFavourite()
                    // Save to db
                }
            }

            fun updateBtnFavourite(){
                var colorId: Int
                var btnText: String
                if(!note.isFavourite){
                    colorId = R.color.green
                    btnText = "Add Favourite"
                } else {
                    colorId = R.color.dark_gray
                    btnText = "Favourited"
                }
                ViewCompat.setBackgroundTintList(
                    binding.btnFavourite,
                    ContextCompat.getColorStateList(
                        view.context,
                        colorId
                    )
                )
                binding.btnFavourite.text = btnText
            }

        }

}