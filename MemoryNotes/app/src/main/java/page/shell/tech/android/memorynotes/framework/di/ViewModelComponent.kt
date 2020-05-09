package page.shell.tech.android.memorynotes.framework.di

import dagger.Component
import page.shell.tech.android.memorynotes.framework.ListViewModel
import page.shell.tech.android.memorynotes.framework.NoteViewModel

@Component(
    modules = [
        ApplicationModule::class,
        RepositoryModule::class,
        UseCasesModule::class
    ]
)
interface ViewModelComponent {

    fun inject(noteViewModel: NoteViewModel)
    fun inject(listViewModel: ListViewModel)
}