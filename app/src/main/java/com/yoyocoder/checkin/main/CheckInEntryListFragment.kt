package com.yoyocoder.checkin.main

import android.graphics.Rect
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yoyocoder.checkin.R
import com.yoyocoder.checkin.add.AddNewCheckInEntryFragment
import com.yoyocoder.checkin.databinding.FragmentMainBinding
import com.yoyocoder.checkin.model.CheckInEntryAction
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckInEntryListFragment : Fragment() {

    private lateinit var viewBinding: FragmentMainBinding
    private val viewModel: MainActivityViewModel by activityViewModels()

    private val checkInEntryAdapter by lazy { CheckInEntryAdapter(::showActionsOnCheckInEntryItem) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentMainBinding.inflate(inflater, container, false)
        (requireActivity() as AppCompatActivity).setSupportActionBar(viewBinding.toolbar)
        setHasOptionsMenu(true)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.checkInEntryList.apply {
            adapter = checkInEntryAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(CheckInEntryItemDecoration())
        }

        observeLiveData()
        viewModel.getAllCheckInEntries()
    }

    private fun observeLiveData() {
        viewModel.checkInEntriesLiveData.observe(requireActivity(), {
            checkInEntryAdapter.submitList(it)
        })

        viewModel.viewActionLiveData.observe(requireActivity(), {
            when (it) {
                is CheckInEntryAction.Edit -> {
                    // start edit screen
                }
                is CheckInEntryAction.Delete -> {
                    viewModel.deleteCheckInEntry(it.id)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        requireActivity().menuInflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.add_check_in_entry) {
            requireActivity().supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, AddNewCheckInEntryFragment())
                .addToBackStack(null)
                .commit()
            true
        } else {
            false
        }
    }

    private fun showActionsOnCheckInEntryItem(checkInEntryId: Int) {
        CheckInItemActionsBottomSheet.newInstance(checkInEntryId)
            .show(requireActivity().supportFragmentManager, null)
    }

    class CheckInEntryItemDecoration : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect[1, 1, 1] = 1
        }
    }
}