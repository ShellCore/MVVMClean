package page.shell.tech.android.memorynotes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import page.shell.tech.android.memorynotes.R
import page.shell.tech.android.memorynotes.framework.ListViewModel

class ListFragment : Fragment() {

    private val noteListAdapter = NotesListAdapter(arrayListOf())
    private lateinit var viewModel: ListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = noteListAdapter
        }

        btnAdd.setOnClickListener {
            goToNoteDetails()
        }

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)

        observeViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getNotes()
    }

    private fun observeViewModel() {
        viewModel.notes.observe(viewLifecycleOwner, Observer { notesList ->
            prgLoading.visibility = View.GONE
            recList.visibility = View.VISIBLE
            noteListAdapter.updateNotes(notesList.sortedByDescending { it.updateTime })
        })
    }

    private fun goToNoteDetails(id: Long = 0L) {
        val action = ListFragmentDirections.actionGoToNote(id)
        Navigation.findNavController(recList).navigate(action)
    }
}
