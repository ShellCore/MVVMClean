package page.shell.tech.android.memorynotes.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_note.view.*
import page.shell.tech.android.core.data.Note
import page.shell.tech.android.memorynotes.R
import java.text.SimpleDateFormat
import java.util.*

class NotesListAdapter(private var notes: ArrayList<Note>) :
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(notes[position])

    override fun getItemCount() = notes.size

    fun updateNotes(notes: ArrayList<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val title = itemView.txtItemNoteTitle
        private val content = itemView.txtItemNoteContent
        private val date = itemView.txtItemNoteDate

        fun bind(note: Note) {
            title.text = note.title
            content.text = note.content
            val dateString =
                itemView.context.getString(R.string.lastDate, note.updateTime.toFormattedDate())
            date.text = dateString
        }
    }
}

private fun Long.toFormattedDate() =
    SimpleDateFormat("yyyy MM dd, HH:mm:ss", Locale.ROOT).format(this)
