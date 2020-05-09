package page.shell.tech.android.memorynotes.framework.di

import android.app.Application
import dagger.Module
import dagger.Provides
import page.shell.tech.android.core.repository.NoteRepository
import page.shell.tech.android.memorynotes.framework.RoomNoteDataSource

@Module
class RepositoryModule {

    @Provides
    fun provideRepository(app: Application) = NoteRepository(RoomNoteDataSource(app))
}