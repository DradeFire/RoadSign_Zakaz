package com.bajenovsasha.roadsign_zakaz.presentation.features.roaddraw

import android.Manifest
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.bajenovsasha.roadsign_zakaz.R
import com.bajenovsasha.roadsign_zakaz.databinding.FragmentRoadDrawBinding
import com.bajenovsasha.roadsign_zakaz.presentation.base.BaseFragment
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class RoadDrawFragment(private val idNumber: Int, private val roadNumberType: RoadSignType = RoadSignType.RUS_2) :
	BaseFragment<FragmentRoadDrawBinding, RoadDrawViewModel>() {

	override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentRoadDrawBinding
		get() = FragmentRoadDrawBinding::inflate
	override val viewModelClass: Class<RoadDrawViewModel>
		get() = RoadDrawViewModel::class.java

	private val mOnKeyboardActionListener: KeyboardView.OnKeyboardActionListener =
		object : KeyboardView.OnKeyboardActionListener {
			override fun onKey(primaryCode: Int, keyCodes: IntArray) {
				viewModel?.onCustomKeyClicked(primaryCode, roadNumberType)
			}

			override fun onPress(arg0: Int) {}
			override fun onRelease(primaryCode: Int) {}
			override fun onText(text: CharSequence) {}
			override fun swipeDown() {}
			override fun swipeLeft() {}
			override fun swipeRight() {}
			override fun swipeUp() {}
		}


	override fun initObservers() {
		viewModel?.let {
			compositeDisposable?.add(
				it.roadSignString
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe({ roadSignStr ->
						binding?.txSign?.text = roadSignStr
					}, { err ->
						err.printStackTrace()
						Toast.makeText(requireContext(), err.message, Toast.LENGTH_SHORT).show()
					})
			)
		}
	}

	override fun initStartValues() {
		activity?.requestPermissions(
			arrayOf(
				Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.READ_EXTERNAL_STORAGE
			), 101
		)
	}

	override fun initUI() {
		initKeyboard()
		openKeyboard()
	}

	override fun initButtons() {
		binding?.btSave?.setOnClickListener {
			viewModel?.onSaveClicked(idNumber, roadNumberType)
		}
	}

	private fun initKeyboard() {
		binding?.keyboardview?.keyboard =
			Keyboard(requireContext(), R.xml.road_sign_keyboard_layout)
		binding?.keyboardview?.setOnKeyboardActionListener(mOnKeyboardActionListener)
	}

	private fun openKeyboard() {
		binding?.keyboardview?.isVisible = true
		binding?.keyboardview?.isEnabled = true
	}

	companion object {

		@JvmStatic
		fun newInstance(id: Int) = RoadDrawFragment(id)
	}

}