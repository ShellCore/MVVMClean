package page.shell.tech.android.memorynotes.framework.di

import dagger.Module
import dagger.Provides
import page.shell.tech.android.core.repository.NoteRepository
import page.shell.tech.android.core.usecase.AddNote
import page.shell.tech.android.core.usecase.GetAllNotes
import page.shell.tech.android.core.usecase.GetNote
import page.shell.tech.android.core.usecase.RemoveNote
import page.shell.tech.android.memorynotes.framework.UseCases

@Module
class UseCasesModule {

    @Provides
    fun getUsesCases(repository: NoteRepository) = UseCases(
        AddNote(repository),
        GetAllNotes(repository),
        GetNote(repository),
        RemoveNote(repository)
    )
}