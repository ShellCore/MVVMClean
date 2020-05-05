package page.shell.tech.android.core.usecase

import page.shell.tech.android.core.data.Note
import page.shell.tech.android.core.repository.NoteRepository

class AddNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.addNote(note)
}