package com.bajenovsasha.roadsign_zakaz.uikit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.bajenovsasha.roadsign_zakaz.common.MyLogger
import com.bajenovsasha.roadsign_zakaz.databinding.DialogChooseRoadNumberBinding
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType

class ChooseRoadNumberDialog(
	private val funAddRoadNumb: (RoadSignType) -> Unit?,
	private val funAddImage: (Int) -> Unit?
) : DialogFragment() {

	private var binding: DialogChooseRoadNumberBinding? = null

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		MyLogger.log("ChooseRoadNumberDialog:onCreateView:info = get")
		binding = DialogChooseRoadNumberBinding.inflate(inflater, container, false)
		return binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		MyLogger.log("ChooseRoadNumberDialog:onCreateView:info = get")

		binding?.apply {
			btRoadNum2.setOnClickListener {
				dismiss()
				funAddRoadNumb(RoadSignType.RUS_2)
			}
			btRoadNum3.setOnClickListener {
				dismiss()
				funAddRoadNumb(RoadSignType.RUS_3)
			}
			btAddImage.setOnClickListener {
				ChooseImageDialog.newInstance { imageId: Int ->
					funAddImage(imageId)
					dismiss()
				}.show(requireActivity().supportFragmentManager, "choose_image_dialog")
			}
		}
	}

}