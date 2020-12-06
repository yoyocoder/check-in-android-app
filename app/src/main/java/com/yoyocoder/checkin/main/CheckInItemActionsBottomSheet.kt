package com.yoyocoder.checkin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.yoyocoder.checkin.databinding.FragmentCheckInEntryActionsBinding
import com.yoyocoder.checkin.model.CheckInEntryAction

class CheckInItemActionsBottomSheet : BottomSheetDialogFragment() {

    private lateinit var viewBinding: FragmentCheckInEntryActionsBinding
    private val viewModel: MainActivityViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainActivityViewModel::class.java)
    }
    private val checkInEntryId: Int by lazy { arguments!!.getInt(CHECK_IN_ENTRY_ID_EXTRA) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentCheckInEntryActionsBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.editAction.setOnClickListener {
            viewModel.viewActionLiveData.value = CheckInEntryAction.Edit(checkInEntryId)
            dismiss()
        }

        viewBinding.deleteAction.setOnClickListener {
            viewModel.viewActionLiveData.value = CheckInEntryAction.Delete(checkInEntryId)
            dismiss()
        }
    }

    companion object {
        private const val CHECK_IN_ENTRY_ID_EXTRA = "check_in_entry_id_extra"

        fun newInstance(checkInEntryId: Int): CheckInItemActionsBottomSheet {
            return CheckInItemActionsBottomSheet().apply {
                arguments = Bundle().apply {
                    putInt(CHECK_IN_ENTRY_ID_EXTRA, checkInEntryId)
                }
            }
        }
    }
}