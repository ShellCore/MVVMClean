package page.shell.tech.android.memorynotes.presentation

import android.app.AlertDialog
import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_note.*
import page.shell.tech.android.core.data.Note
import page.shell.tech.android.memorynotes.R
import page.shell.tech.android.memorynotes.framework.NoteViewModel

class NoteFragment : Fragment() {

    private lateinit var viewModel: NoteViewModel
    private var currentNote = Note("", "", 0L, 0L)
    private var noteId = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)

        arguments?.let {
            noteId = NoteFragmentArgs.fromBundle(it).noteId
        }

        if (noteId != 0L) {
            viewModel.getNote(noteId)
        }

        btnSave.setOnClickListener {
            if (edtTitle.text.toString().isNotEmpty()
                || edtContent.text.toString().isNotEmpty()
            ) {
                val time = System.currentTimeMillis()
                currentNote.apply {
                    title = edtTitle.text.toString()
                    content = edtContent.text.toString()
                    updateTime = time
                    if (id == 0L) {
                        creationTime = time
                    }
                }
                viewModel.saveNote(currentNote)
            } else {
                Navigation.findNavController(it)
                    .popBackStack()
            }
        }

        observeViewModel()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_note, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actionDeleteNote -> {
                if (context != null && noteId != 0L) {
                    AlertDialog.Builder(context)
                        .setTitle("Delete note")
                        .setMessage("Are you sure you want to delete this note?")
                        .setPositiveButton("Yes") { dialog, which ->
                            viewModel.deleteNote(currentNote)
                        }
                        .setNegativeButton("Cancel") { dialog, which -> }
                        .create()
                        .show()
                }
            }
        }

        return true
    }

    private fun observeViewModel() {
        viewModel.saved.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(context, "Done!", Toast.LENGTH_SHORT)
                    .show()
                hideKeyBoard()
                Navigation.findNavController(edtTitle).popBackStack()
            } else {
                Toast.makeText(
                    context,
                    "Something went wrong. Please try again.",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
        })

        viewModel.currentNote.observe(viewLifecycleOwner, Observer { note ->
            note?.let {
                currentNote = it
                edtTitle.setText(it.title)
                edtContent.setText(it.content)
            }
        })
    }

    private fun hideKeyBoard() {
        val imm = context?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(edtTitle.windowToken, 0)
    }
}
