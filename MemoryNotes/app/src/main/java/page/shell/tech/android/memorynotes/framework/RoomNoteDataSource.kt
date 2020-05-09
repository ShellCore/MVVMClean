package page.shell.tech.android.memorynotes.framework

import android.content.Context
import page.shell.tech.android.core.data.Note
import page.shell.tech.android.core.repository.NoteDataSource
import page.shell.tech.android.memorynotes.framework.db.DatabaseService
import page.shell.tech.android.memorynotes.framework.db.NoteEntity

class RoomNoteDataSource(context: Context) : NoteDataSource {

    private val noteDao = DatabaseService.getInstance(context).noteDao()

    override suspend fun add(note: Note) =
        noteDao.addNoteEntity(NoteEntity.fromNote(note))

    override suspend fun get(id: Long) =
        noteDao.getNoteEntity(id)?.toNote()

    override suspend fun getAll() =
        noteDao.getAllNoteEntities().map { it.toNote() }

    override suspend fun remove(note: Note) =
        noteDao.deleteNoteEntity(NoteEntity.fromNote(note))
}