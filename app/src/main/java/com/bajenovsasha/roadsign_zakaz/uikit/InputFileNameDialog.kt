package com.bajenovsasha.roadsign_zakaz.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bajenovsasha.roadsign_zakaz.databinding.DialogInputFilenameBinding

class InputFileNameDialog(private val function: (String) -> Unit) : DialogFragment() {

	private var binding: DialogInputFilenameBinding? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = DialogInputFilenameBinding.inflate(inflater, container, false)
		return binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding?.apply {
			btSaveRoadNumberFileName.setOnClickListener {
				if(!etInputFilename.text.isNullOrBlank()) {
					dismiss()
					function(etInputFilename.text.toString())
				}
			}
		}
	}

}