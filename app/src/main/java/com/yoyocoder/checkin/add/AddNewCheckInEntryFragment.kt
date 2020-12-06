package com.yoyocoder.checkin.add

import android.os.Bundle
import android.view.*
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.yoyocoder.checkin.R
import com.yoyocoder.checkin.databinding.FragmentAddCheckInEntryBinding
import com.yoyocoder.checkin.main.MainActivityViewModel
import com.yoyocoder.checkin.model.CheckInEntry

class AddNewCheckInEntryFragment : Fragment() {

    private lateinit var viewBinding: FragmentAddCheckInEntryBinding
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentAddCheckInEntryBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        (requireActivity() as? AppCompatActivity)?.apply {
            setSupportActionBar(viewBinding.myToolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
        viewBinding.myToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        return viewBinding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.add_check_in_entry_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.finish_add_check_in_entry -> {
                saveNewCheckInEntry()
                true
            }
            else -> false
        }
    }

    private fun saveNewCheckInEntry() {
        val checkInEntryName = viewBinding.checkInEntryName.text.toString()

        viewBinding.checkInEntryName.onEditorAction(EditorInfo.IME_ACTION_DONE)
        if (checkInEntryName.isNotBlank()) {
            val checkInEntry = CheckInEntry(checkInEntryName)
            viewModel.addCheckInEntry(checkInEntry)
            requireActivity().supportFragmentManager.popBackStack()
        } else {
            Toast.makeText(
                requireContext(),
                "Please fill the name of the check-in entry!!",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}