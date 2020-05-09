package page.shell.tech.android.core.usecase

import page.shell.tech.android.core.repository.NoteRepository

class GetAllNotes(private val noteRepository: NoteRepository) {

    suspend operator fun invoke() = noteRepository.getAllNotes()
}