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

class NotesListAdapter(private var notes: ArrayList<Note>, val actions: ListAction) :
    RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(notes[position])

    override fun getItemCount() =
        notes.size

    fun updateNotes(notes: List<Note>) {
        this.notes.clear()
        this.notes.addAll(notes)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val container = itemView.itemNoteContainer
        private val title = itemView.txtItemNoteTitle
        private val content = itemView.txtItemNoteContent
        private val date = itemView.txtItemNoteDate
        private val wordCount = itemView.txtWordCount

        fun bind(note: Note) {
            title.text = note.title
            content.text = note.content

            val dateString =
                itemView.context.getString(
                    R.string.item_note_lastDate,
                    note.updateTime.toFormattedDate()
                )
            date.text = dateString

            val wordString =
                itemView.context.getString(R.string.item_note_wordCount, note.wordCount)
            wordCount.text = wordString

            container.setOnClickListener {
                actions.onClick(note.id)
            }
        }
    }
}

private fun Long.toFormattedDate() =
    SimpleDateFormat("yyyy MM dd, HH:mm:ss", Locale.ROOT).format(this)
