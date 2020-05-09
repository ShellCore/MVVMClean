package page.shell.tech.android.memorynotes.framework

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import page.shell.tech.android.core.data.Note
import page.shell.tech.android.core.repository.NoteRepository
import page.shell.tech.android.memorynotes.framework.di.ApplicationModule
import page.shell.tech.android.memorynotes.framework.di.DaggerViewModelComponent
import javax.inject.Inject

class ListViewModel(application: Application) : AndroidViewModel(application) {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = NoteRepository(RoomNoteDataSource(application))

    @Inject
    lateinit var useCases: UseCases

    init {
        DaggerViewModelComponent.builder()
            .applicationModule(ApplicationModule(getApplication()))
            .build()
            .inject(this)
    }

    val notes = MutableLiveData<List<Note>>()

    fun getNotes() {
        coroutineScope.launch {
            val noteList = useCases.getAllNotes()
            notes.postValue(noteList)
        }
    }
}