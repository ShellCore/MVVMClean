package page.shell.tech.android.memorynotes.framework

import page.shell.tech.android.core.usecase.AddNote
import page.shell.tech.android.core.usecase.GetAllNotes
import page.shell.tech.android.core.usecase.GetNote
import page.shell.tech.android.core.usecase.RemoveNote

data class UseCases(
    val addNote: AddNote,
    val getAllNotes: GetAllNotes,
    val getNote: GetNote,
    val removeNote: RemoveNote
)