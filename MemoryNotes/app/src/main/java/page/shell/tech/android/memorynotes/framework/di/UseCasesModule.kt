package page.shell.tech.android.memorynotes.framework.di

import dagger.Module
import dagger.Provides
import page.shell.tech.android.core.repository.NoteRepository
import page.shell.tech.android.core.usecase.*
import page.shell.tech.android.memorynotes.framework.UseCases

@Module
class UseCasesModule {

    @Provides
    fun getUsesCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository),
        GetWordCount()
    )
}