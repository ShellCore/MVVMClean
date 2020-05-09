package page.shell.tech.android.core.usecase

import page.shell.tech.android.core.data.Note
import page.shell.tech.android.core.repository.NoteRepository

class RemoveNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(note: Note) = noteRepository.removeNote(note)
}