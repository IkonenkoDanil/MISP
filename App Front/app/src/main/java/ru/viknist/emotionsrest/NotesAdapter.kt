package ru.viknist.emotionsrest

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.viknist.emotionsrest.models.NoteModel
import ru.viknist.horoshkeevaapp.R

class NotesAdapter(Context: Context, private val notes: List<NoteModel>) :
    RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(Context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.noteitem, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount(): Int = notes.size

    private fun getItem(position: Int) : NoteModel = notes[position]

    class ViewHolder(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.noteImageView)
        private val time: TextView = itemView.findViewById(R.id.timeTextView)
        private val text: TextView = itemView.findViewById(R.id.textNoteTextView)

        fun bind(note: NoteModel) {
            text.text = note.text
            time.text = note.time?.subSequence(0, 5) ?: "00:00"
            image.setImageResource(
                when (note.emotion) {
                    "happy" -> R.drawable.happyface
                    "sad" -> R.drawable.sadface
                    "disappointed" -> R.drawable.disappointmentface
                    "scared" -> R.drawable.fearface
                    "angry" -> R.drawable.angryface
                    else -> R.drawable.dayweek
                }
            )
        }
    }
}