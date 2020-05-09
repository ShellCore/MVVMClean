package page.shell.tech.android.core.usecase

import page.shell.tech.android.core.repository.NoteRepository

class GetNote(private val noteRepository: NoteRepository) {

    suspend operator fun invoke(id: Long) = noteRepository.getNote(id)
}