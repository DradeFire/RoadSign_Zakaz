package com.bajenovsasha.roadsign_zakaz.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bajenovsasha.roadsign_zakaz.databinding.ChooseRoadNumberDialogFragmentBinding
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType

class ChooseRoadNumberDialog(private val function: (RoadSignType) -> Unit?) : DialogFragment() {

	private var binding: ChooseRoadNumberDialogFragmentBinding? = null

	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
		binding = ChooseRoadNumberDialogFragmentBinding.inflate(inflater, container, false)
		return binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding?.apply {
			btRoadNum2.setOnClickListener {
				dismiss()
				function(RoadSignType.RUS_2)
			}
			btRoadNum3.setOnClickListener {
				dismiss()
				function(RoadSignType.RUS_3)
			}
		}
	}

}