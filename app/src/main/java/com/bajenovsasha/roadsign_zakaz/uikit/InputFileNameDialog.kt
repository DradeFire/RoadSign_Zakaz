package com.bajenovsasha.roadsign_zakaz.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bajenovsasha.roadsign_zakaz.databinding.InputFilenameDialogFragmentBinding

class InputFileNameDialog(private val function: (String) -> Unit) : DialogFragment() {

	private var binding: InputFilenameDialogFragmentBinding? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = InputFilenameDialogFragmentBinding.inflate(inflater, container, false)
		return binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding?.apply {
			btSaveRoadNumberFileName.setOnClickListener {
				if(etInputFilename.text.isNullOrBlank()) {
					dismiss()
					function(etInputFilename.text.toString())
				}
			}
		}
	}

}