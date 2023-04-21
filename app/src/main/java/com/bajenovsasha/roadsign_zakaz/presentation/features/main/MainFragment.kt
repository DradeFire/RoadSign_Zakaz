package com.bajenovsasha.roadsign_zakaz.presentation.features.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.inputmethodservice.Keyboard
import android.inputmethodservice.KeyboardView
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import com.bajenovsasha.roadsign_zakaz.R
import com.bajenovsasha.roadsign_zakaz.common.MyLogger
import com.bajenovsasha.roadsign_zakaz.databinding.FragmentMainBinding
import com.bajenovsasha.roadsign_zakaz.databinding.InputRoadNumber2Binding
import com.bajenovsasha.roadsign_zakaz.databinding.InputRoadNumber3Binding
import com.bajenovsasha.roadsign_zakaz.presentation.base.BaseFragment
import com.bajenovsasha.roadsign_zakaz.presentation.features.main.helpers.InputRoadNumber2Configurator
import com.bajenovsasha.roadsign_zakaz.presentation.features.main.helpers.InputRoadNumber3Configurator
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignInfo
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType
import com.bajenovsasha.roadsign_zakaz.uikit.ChooseRoadNumberDialog
import com.bajenovsasha.roadsign_zakaz.uikit.InputFileNameDialog
import com.google.android.material.textfield.TextInputEditText
import com.hbisoft.pickit.PickiT
import com.hbisoft.pickit.PickiTCallbacks
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.ArrayList
import java.util.HashMap

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>(), PickiTCallbacks {

	override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
		get() = FragmentMainBinding::inflate
	override val viewModelClass: Class<MainViewModel>
		get() = MainViewModel::class.java

	private val mOnKeyboardActionListener: KeyboardView.OnKeyboardActionListener =
		object : KeyboardView.OnKeyboardActionListener {
			override fun onKey(primaryCode: Int, keyCodes: IntArray) {
				viewModel?.onCustomKeyClicked(primaryCode)
			}

			override fun onPress(arg0: Int) {}
			override fun onRelease(primaryCode: Int) {}
			override fun onText(text: CharSequence) {}
			override fun swipeDown() {}
			override fun swipeLeft() {}
			override fun swipeRight() {}
			override fun swipeUp() {}
		}

	private val configuratorRN2 = InputRoadNumber2Configurator()
	private val configuratorRN3 = InputRoadNumber3Configurator()

	private var pickIt: PickiT? = null
	private var lastPickRequestCode = DEFAULT_INDEX

	/**
	 * 1..6
	 */
	private val currentRoadNumberInput: MutableLiveData<Int> = MutableLiveData()

	private val imageIdList = arrayOf(
		R.drawable.plus,
		R.drawable.plus,
		R.drawable.plus,
		R.drawable.plus,
		R.drawable.plus,
		R.drawable.plus,
	)

	override fun initStartValues() {
		pickIt = PickiT(requireContext(), this, requireActivity())
		activity?.requestPermissions(
			arrayOf(
				Manifest.permission.WRITE_EXTERNAL_STORAGE,
				Manifest.permission.READ_EXTERNAL_STORAGE
			), 101
		)
	}

	override fun initUI() {
		clearUI()
		initKeyboard()
	}

	private fun initKeyboard() {
		binding?.apply {
			configureKeyboard(this)

			keyboardview.keyboard =
				Keyboard(requireContext(), R.xml.road_sign_keyboard_layout)
			keyboardview.setOnKeyboardActionListener(mOnKeyboardActionListener)

			requireActivity().window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)

			keyboardview.isVisible = false

			// Do not show the preview balloons
			keyboardview.isPreviewEnabled = false
		}
	}

	private fun configureKeyboard(fragmentMainBinding: FragmentMainBinding) =
		with(fragmentMainBinding) {
			configuratorRN2.configure(
				contRoadNum1.ed2RoadNumber,
				currentRoadNumberInput,
				INDEX_1,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
			configuratorRN2.configure(
				contRoadNum2.ed2RoadNumber,
				currentRoadNumberInput,
				INDEX_2,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
			configuratorRN2.configure(
				contRoadNum3.ed2RoadNumber,
				currentRoadNumberInput,
				INDEX_3,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
			configuratorRN2.configure(
				contRoadNum4.ed2RoadNumber,
				currentRoadNumberInput,
				INDEX_4,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
			configuratorRN2.configure(
				contRoadNum5.ed2RoadNumber,
				currentRoadNumberInput,
				INDEX_5,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
			configuratorRN2.configure(
				contRoadNum6.ed2RoadNumber,
				currentRoadNumberInput,
				INDEX_6,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }

			configuratorRN3.configure(
				contRoadNum1.ed3RoadNumber,
				currentRoadNumberInput,
				INDEX_1,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
			configuratorRN3.configure(
				contRoadNum2.ed3RoadNumber,
				currentRoadNumberInput,
				INDEX_2,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
			configuratorRN3.configure(
				contRoadNum3.ed3RoadNumber,
				currentRoadNumberInput,
				INDEX_3,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
			configuratorRN3.configure(
				contRoadNum4.ed3RoadNumber,
				currentRoadNumberInput,
				INDEX_4,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
			configuratorRN3.configure(
				contRoadNum5.ed3RoadNumber,
				currentRoadNumberInput,
				INDEX_5,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
			configuratorRN3.configure(
				contRoadNum6.ed3RoadNumber,
				currentRoadNumberInput,
				INDEX_6,
				{ showCustomKeyboard() }
			) { hideKeyboardAndElementsUnderKeyboard() }
		}

	private fun clearUI() {
		binding?.contRoadNum1?.imAddRoadNum?.setImageResource(R.drawable.plus)
		binding?.contRoadNum2?.imAddRoadNum?.setImageResource(R.drawable.plus)
		binding?.contRoadNum3?.imAddRoadNum?.setImageResource(R.drawable.plus)
		binding?.contRoadNum4?.imAddRoadNum?.setImageResource(R.drawable.plus)
		binding?.contRoadNum5?.imAddRoadNum?.setImageResource(R.drawable.plus)
		binding?.contRoadNum6?.imAddRoadNum?.setImageResource(R.drawable.plus)
	}

	override fun initButtons() {
		binding?.apply {
//			constraintBackground.setOnClickListener {
//				showElementsUnderKeyboard()
//				hideCustomKeyboard()
//			}
			btSaveRoadNumberList.setOnClickListener {
				handleSaveRoadNumberClicked()
			}
			btClearRoadNumberList.setOnClickListener {
				handleClearRoadNumberClicked()
			}
			contRoadNum1.imAddRoadNum.setOnClickListener {
				onRoadNumberClicked(INDEX_1)
			}
			contRoadNum2.imAddRoadNum.setOnClickListener {
				onRoadNumberClicked(INDEX_2)
			}
			contRoadNum3.imAddRoadNum.setOnClickListener {
				onRoadNumberClicked(INDEX_3)
			}
			contRoadNum4.imAddRoadNum.setOnClickListener {
				onRoadNumberClicked(INDEX_4)
			}
			contRoadNum5.imAddRoadNum.setOnClickListener {
				onRoadNumberClicked(INDEX_5)
			}
			contRoadNum6.imAddRoadNum.setOnClickListener {
				onRoadNumberClicked(INDEX_6)
			}
			btEdit1.setOnClickListener {
				onEditClicked(INDEX_1)
			}
			btEdit2.setOnClickListener {
				onEditClicked(INDEX_2)
			}
			btEdit3.setOnClickListener {
				onEditClicked(INDEX_3)
			}
			btEdit4.setOnClickListener {
				onEditClicked(INDEX_4)
			}
			btEdit5.setOnClickListener {
				onEditClicked(INDEX_5)
			}
			btEdit6.setOnClickListener {
				onEditClicked(INDEX_6)
			}
			btDelete1.setOnClickListener {
				onDeleteClicked(INDEX_1)
			}
			btDelete2.setOnClickListener {
				onDeleteClicked(INDEX_2)
			}
			btDelete3.setOnClickListener {
				onDeleteClicked(INDEX_3)
			}
			btDelete4.setOnClickListener {
				onDeleteClicked(INDEX_4)
			}
			btDelete5.setOnClickListener {
				onDeleteClicked(INDEX_5)
			}
			btDelete6.setOnClickListener {
				onDeleteClicked(INDEX_6)
			}
		}
	}

	private fun handleClearRoadNumberClicked() {
		binding?.apply {
			showRoadNumberInput(
				null,
				contRoadNum1.ed2RoadNumber,
				contRoadNum1.ed3RoadNumber,
				contRoadNum1.imAddRoadNum,
				INDEX_1
			)
			showRoadNumberInput(
				null,
				contRoadNum2.ed2RoadNumber,
				contRoadNum2.ed3RoadNumber,
				contRoadNum2.imAddRoadNum,
				INDEX_2
			)
			showRoadNumberInput(
				null,
				contRoadNum3.ed2RoadNumber,
				contRoadNum3.ed3RoadNumber,
				contRoadNum3.imAddRoadNum,
				INDEX_3
			)
			showRoadNumberInput(
				null,
				contRoadNum4.ed2RoadNumber,
				contRoadNum4.ed3RoadNumber,
				contRoadNum4.imAddRoadNum,
				INDEX_4
			)
			showRoadNumberInput(
				null,
				contRoadNum5.ed2RoadNumber,
				contRoadNum5.ed3RoadNumber,
				contRoadNum5.imAddRoadNum,
				INDEX_5
			)
			showRoadNumberInput(
				null,
				contRoadNum6.ed2RoadNumber,
				contRoadNum6.ed3RoadNumber,
				contRoadNum6.imAddRoadNum,
				INDEX_6
			)
		}
	}

	private fun onDeleteClicked(i: Int) {
		binding?.apply {
			when (i) {
				INDEX_1 -> showRoadNumberInput(
					null,
					contRoadNum1.ed2RoadNumber,
					contRoadNum1.ed3RoadNumber,
					contRoadNum1.imAddRoadNum,
					i
				)
				INDEX_2 -> showRoadNumberInput(
					null,
					contRoadNum2.ed2RoadNumber,
					contRoadNum2.ed3RoadNumber,
					contRoadNum2.imAddRoadNum,
					i
				)
				INDEX_3 -> showRoadNumberInput(
					null,
					contRoadNum3.ed2RoadNumber,
					contRoadNum3.ed3RoadNumber,
					contRoadNum3.imAddRoadNum,
					i
				)
				INDEX_4 -> showRoadNumberInput(
					null,
					contRoadNum4.ed2RoadNumber,
					contRoadNum4.ed3RoadNumber,
					contRoadNum4.imAddRoadNum,
					i
				)
				INDEX_5 -> showRoadNumberInput(
					null,
					contRoadNum5.ed2RoadNumber,
					contRoadNum5.ed3RoadNumber,
					contRoadNum5.imAddRoadNum,
					i
				)
				INDEX_6 -> showRoadNumberInput(
					null,
					contRoadNum6.ed2RoadNumber,
					contRoadNum6.ed3RoadNumber,
					contRoadNum6.imAddRoadNum,
					i
				)
			}
		}
	}

	private fun onEditClicked(i: Int) {
		onRoadNumberClicked(i)
	}

	private fun handleSaveRoadNumberClicked() {
		val map: HashMap<Int, RoadSignInfo> = getFullMapOfInputs()
		InputFileNameDialog { fileName: String ->
			binding?.drawView2?.setAndSaveRoadNumbers(map, fileName)
		}.show(requireActivity().supportFragmentManager, "input_file_name")
	}

	private fun getFullMapOfInputs(): HashMap<Int, RoadSignInfo> {
		binding?.apply {
			val map = hashMapOf<Int, RoadSignInfo>()
			if (contRoadNum1.ed2RoadNumber.root.isVisible) {
				map[INDEX_1] = RoadSignInfo(
					RoadSignType.RUS_2,
					configuratorRN2.getFullString(contRoadNum1.ed2RoadNumber)
				)
			} else if (contRoadNum1.ed3RoadNumber.root.isVisible) {
				map[INDEX_1] = RoadSignInfo(
					RoadSignType.RUS_3,
					configuratorRN3.getFullString(contRoadNum1.ed3RoadNumber)
				)
			}
			else {
				map[INDEX_1] = RoadSignInfo(
					RoadSignType.IMAGE,
					null,
					imageIdList[0]
				)
			}

			if (contRoadNum2.ed2RoadNumber.root.isVisible) {
				map[INDEX_2] = RoadSignInfo(
					RoadSignType.RUS_2,
					configuratorRN2.getFullString(contRoadNum2.ed2RoadNumber)
				)
			} else if (contRoadNum2.ed3RoadNumber.root.isVisible) {
				map[INDEX_2] = RoadSignInfo(
					RoadSignType.RUS_3,
					configuratorRN3.getFullString(contRoadNum2.ed3RoadNumber)
				)
			}
			else {
				map[INDEX_2] = RoadSignInfo(
					RoadSignType.IMAGE,
					null,
					imageIdList[1]
				)
			}

			if (contRoadNum3.ed2RoadNumber.root.isVisible) {
				map[INDEX_3] = RoadSignInfo(
					RoadSignType.RUS_2,
					configuratorRN2.getFullString(contRoadNum3.ed2RoadNumber)
				)
			} else if (contRoadNum3.ed3RoadNumber.root.isVisible) {
				map[INDEX_3] = RoadSignInfo(
					RoadSignType.RUS_3,
					configuratorRN3.getFullString(contRoadNum3.ed3RoadNumber)
				)
			}
			else {
				map[INDEX_3] = RoadSignInfo(
					RoadSignType.IMAGE,
					null,
					imageIdList[2]
				)
			}

			if (contRoadNum4.ed2RoadNumber.root.isVisible) {
				map[INDEX_4] = RoadSignInfo(
					RoadSignType.RUS_2,
					configuratorRN2.getFullString(contRoadNum4.ed2RoadNumber)
				)
			} else if (contRoadNum4.ed3RoadNumber.root.isVisible) {
				map[INDEX_4] = RoadSignInfo(
					RoadSignType.RUS_3,
					configuratorRN3.getFullString(contRoadNum4.ed3RoadNumber)
				)
			}
			else {
				map[INDEX_4] = RoadSignInfo(
					RoadSignType.IMAGE,
					null,
					imageIdList[3]
				)
			}

			if (contRoadNum5.ed2RoadNumber.root.isVisible) {
				map[INDEX_5] = RoadSignInfo(
					RoadSignType.RUS_2,
					configuratorRN2.getFullString(contRoadNum5.ed2RoadNumber)
				)
			} else if (contRoadNum5.ed3RoadNumber.root.isVisible) {
				map[INDEX_5] = RoadSignInfo(
					RoadSignType.RUS_3,
					configuratorRN3.getFullString(contRoadNum5.ed3RoadNumber)
				)
			}
			else {
				map[INDEX_5] = RoadSignInfo(
					RoadSignType.IMAGE,
					null,
					imageIdList[4]
				)
			}

			if (contRoadNum6.ed2RoadNumber.root.isVisible) {
				map[INDEX_6] = RoadSignInfo(
					RoadSignType.RUS_2,
					configuratorRN2.getFullString(contRoadNum6.ed2RoadNumber)
				)
			} else if (contRoadNum6.ed3RoadNumber.root.isVisible) {
				map[INDEX_6] = RoadSignInfo(
					RoadSignType.RUS_3,
					configuratorRN3.getFullString(contRoadNum6.ed3RoadNumber)
				)
			}
			else {
				map[INDEX_6] = RoadSignInfo(
					RoadSignType.IMAGE,
					null,
					imageIdList[5]
				)
			}

			return map
		}
		return HashMap<Int, RoadSignInfo>()
	}

	override fun initObservers() {
		viewModel?.let {
			compositeDisposable?.add(
				it.roadSignChar
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe({ roadSignChar ->
						bindRoadNumberOneChar(roadSignChar)
					}, { err ->
						MyLogger.log("MainFragment :: initObservers :: error:", err)
						Toast.makeText(requireContext(), err.message, Toast.LENGTH_SHORT).show()
					})
			)
		}
	}

	private fun bindRoadNumberOneChar(roadSignChar: Char) {
		(requireActivity().window.currentFocus as? TextInputEditText)?.let { edText ->
			when (currentRoadNumberInput.value) {
				INDEX_1 -> {
					if (binding?.contRoadNum1?.ed2RoadNumber?.root?.isVisible == true) {
						configuratorRN2.bindRoadNumber(
							edText, binding?.contRoadNum1?.ed2RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_1,
							{ hideCustomKeyboard() },
						) {
							showElementsUnderKeyboard()
						}
					} else {
						configuratorRN3.bindRoadNumber(
							edText, binding?.contRoadNum1?.ed3RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_1,
							{ hideCustomKeyboard() },
						) {
							showElementsUnderKeyboard()
						}
					}
				}
				INDEX_2 -> {
					if (binding?.contRoadNum2?.ed2RoadNumber?.root?.isVisible == true) {
						configuratorRN2.bindRoadNumber(
							edText, binding?.contRoadNum2?.ed2RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_2,
							{ hideCustomKeyboard() },
						) {
							showElementsUnderKeyboard()
						}
					} else {
						configuratorRN3.bindRoadNumber(
							edText, binding?.contRoadNum2?.ed3RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_2,
							{ hideCustomKeyboard() },
						) {
							showElementsUnderKeyboard()
						}
					}
				}
				INDEX_3 -> {
					if (binding?.contRoadNum3?.ed2RoadNumber?.root?.isVisible == true) {
						configuratorRN2.bindRoadNumber(
							edText, binding?.contRoadNum3?.ed2RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_3,
							{ hideCustomKeyboard() },
						) {
							showElementsUnderKeyboard()
						}
					} else {
						configuratorRN3.bindRoadNumber(
							edText, binding?.contRoadNum3?.ed3RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_3,
							{ hideCustomKeyboard() },
						) {
							showElementsUnderKeyboard()
						}
					}
				}
				INDEX_4 -> {
					if (binding?.contRoadNum4?.ed2RoadNumber?.root?.isVisible == true) {
						configuratorRN2.bindRoadNumber(
							edText, binding?.contRoadNum4?.ed2RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_4,
							{ hideCustomKeyboard() },
						) {
							showElementsUnderKeyboard()
						}
					} else {
						configuratorRN3.bindRoadNumber(
							edText, binding?.contRoadNum4?.ed3RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_4,
							{ hideCustomKeyboard() },
						) {
							showElementsUnderKeyboard()
						}
					}
				}
				INDEX_5 -> {
					if (binding?.contRoadNum5?.ed2RoadNumber?.root?.isVisible == true) {
						configuratorRN2.bindRoadNumber(
							edText, binding?.contRoadNum5?.ed2RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_5,
							{ hideCustomKeyboard() },
						) {
							showElementsUnderKeyboard()
						}
					} else {
						configuratorRN3.bindRoadNumber(
							edText, binding?.contRoadNum5?.ed3RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_5,
							{ hideCustomKeyboard() },
						) {
							showElementsUnderKeyboard()
						}
					}
				}
				INDEX_6 -> {
					if (binding?.contRoadNum6?.ed2RoadNumber?.root?.isVisible == true) {
						configuratorRN2.bindRoadNumber(
							edText, binding?.contRoadNum6?.ed2RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_6,
							{ hideCustomKeyboard() }
						) {
							showElementsUnderKeyboard()
						}
					} else {
						configuratorRN3.bindRoadNumber(
							edText, binding?.contRoadNum6?.ed3RoadNumber, roadSignChar,
							currentRoadNumberInput,
							INDEX_6,
							{ hideCustomKeyboard() }
						) {
							showElementsUnderKeyboard()
						}
					}
				}
				null -> {
					MyLogger.log("Main fragment :: bindRoadNumberUIOneChar :: error: Some shit!")
					Toast.makeText(requireContext(), "Some shit!", Toast.LENGTH_SHORT).show()
				}
			}
		}
	}

	private fun showElementsUnderKeyboard() {
		binding?.btSaveRoadNumberList?.apply {
			isEnabled = true
			isVisible = true
		}
		binding?.btClearRoadNumberList?.apply {
			isEnabled = true
			isVisible = true
		}
		binding?.contInfoText?.root?.apply {
			isEnabled = true
			isVisible = true
		}
	}

	private fun onRoadNumberClicked(i: Int) {
		ChooseRoadNumberDialog({ type: RoadSignType ->
			handleRoadNumberInput(i, type, null)
		}, { imageId: Int ->
//			openGallery(i)
//			testImage(i)
			handleRoadNumberInput(i, RoadSignType.IMAGE, imageId)
		}).show(requireActivity().supportFragmentManager, "choose_road_number")
	}

	private fun handleRoadNumberInput(i: Int, type: RoadSignType, imageId: Int?) {
		binding?.apply {
			when (i) {
				INDEX_1 -> showRoadNumberInput(
					type,
					contRoadNum1.ed2RoadNumber,
					contRoadNum1.ed3RoadNumber,
					contRoadNum1.imAddRoadNum,
					INDEX_1,
					imageId
				)
				INDEX_2 -> showRoadNumberInput(
					type,
					contRoadNum2.ed2RoadNumber,
					contRoadNum2.ed3RoadNumber,
					contRoadNum2.imAddRoadNum,
					INDEX_2,
					imageId
				)
				INDEX_3 -> showRoadNumberInput(
					type,
					contRoadNum3.ed2RoadNumber,
					contRoadNum3.ed3RoadNumber,
					contRoadNum3.imAddRoadNum,
					INDEX_3,
					imageId
				)
				INDEX_4 -> showRoadNumberInput(
					type,
					contRoadNum4.ed2RoadNumber,
					contRoadNum4.ed3RoadNumber,
					contRoadNum4.imAddRoadNum,
					INDEX_4,
					imageId
				)
				INDEX_5 -> showRoadNumberInput(
					type,
					contRoadNum5.ed2RoadNumber,
					contRoadNum5.ed3RoadNumber,
					contRoadNum5.imAddRoadNum,
					INDEX_5,
					imageId
				)
				INDEX_6 -> showRoadNumberInput(
					type,
					contRoadNum6.ed2RoadNumber,
					contRoadNum6.ed3RoadNumber,
					contRoadNum6.imAddRoadNum,
					INDEX_6,
					imageId
				)
			}
		}
	}

	private fun showRoadNumberInput(
		type: RoadSignType?,
		ed2: InputRoadNumber2Binding,
		ed3: InputRoadNumber3Binding,
		im: ImageView,
		i: Int,
		imageId: Int? = null
	) {
		currentRoadNumberInput.value = i
		configuratorRN2.clear(ed2)
		configuratorRN3.clear(ed3)
		when (type) {
			RoadSignType.RUS_2 -> {
				ed2.root.isVisible = true
				ed3.root.isVisible = false
				im.isVisible = false
				configuratorRN2.initCursor(ed2) {
					hideKeyboardAndElementsUnderKeyboard()
					showCustomKeyboard()
				}
			}
			RoadSignType.RUS_3 -> {
				ed2.root.isVisible = false
				ed3.root.isVisible = true
				im.isVisible = false
				configuratorRN3.initCursor(ed3) {
					hideKeyboardAndElementsUnderKeyboard()
					showCustomKeyboard()
				}
			}
			RoadSignType.IMAGE -> {
				ed2.root.isVisible = false
				ed3.root.isVisible = false
				im.isVisible = true
				if (imageId != null) {
					im.setImageResource(imageId)
					imageIdList[i - 1] = imageId
				}
			}
			null -> {
				ed2.root.isVisible = false
				ed3.root.isVisible = false
				im.isVisible = true
				im.setImageResource(R.drawable.plus)
				imageIdList[i - 1] = R.drawable.plus
			}
		}
	}

	private fun showCustomKeyboard() {
		binding?.keyboardview?.isVisible = true
		binding?.keyboardview?.isEnabled = true
	}

	private fun hideCustomKeyboard() {
		binding?.keyboardview?.isVisible = false
		binding?.keyboardview?.isEnabled = false
	}

	private fun hideKeyboardAndElementsUnderKeyboard() {
		binding?.btSaveRoadNumberList?.apply {
			isEnabled = false
			isInvisible = true
		}
		binding?.btClearRoadNumberList?.apply {
			isEnabled = false
			isInvisible = true
		}
		binding?.contInfoText?.root?.apply {
			isEnabled = false
			isInvisible = true
		}
		CoroutineScope(Dispatchers.Main).launch {
			delay(50)
			(requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager)
				.hideSoftInputFromWindow(
					binding?.root?.windowToken,
					0
				)
		}
	}

	override fun initOnBackPressed() {
		requireActivity()
			.onBackPressedDispatcher
			.addCallback(this, object : OnBackPressedCallback(true) {
				override fun handleOnBackPressed() {
					if (binding?.keyboardview?.isEnabled == true) {
						showElementsUnderKeyboard()
						hideCustomKeyboard()
					} else {
						isEnabled = false
						requireActivity().onBackPressed()
					}
				}

			})
	}

	override fun PickiTonUriReturned() {
		// no-op
	}

	override fun PickiTonStartListener() {
		// no-op
	}

	override fun PickiTonProgressUpdate(progress: Int) {
		// no-op
	}

	override fun PickiTonCompleteListener(
		path: String?,
		wasDriveFile: Boolean,
		wasUnknownProvider: Boolean,
		wasSuccessful: Boolean,
		reason: String?
	) {
		if (wasSuccessful) {
			val uriSrc = Uri.parse(path.toString())
			when (lastPickRequestCode) {
				REQUEST_CODE_GET_PATH_1 -> {
					viewModel?.onAddImage(INDEX_1, path.toString())
					binding?.contRoadNum1?.imAddRoadNum?.setImageURI(uriSrc)
				}
				REQUEST_CODE_GET_PATH_2 -> {
					viewModel?.onAddImage(INDEX_2, path.toString())
					binding?.contRoadNum2?.imAddRoadNum?.setImageURI(uriSrc)
				}
				REQUEST_CODE_GET_PATH_3 -> {
					viewModel?.onAddImage(INDEX_3, path.toString())
					binding?.contRoadNum3?.imAddRoadNum?.setImageURI(uriSrc)
				}
				REQUEST_CODE_GET_PATH_4 -> {
					viewModel?.onAddImage(INDEX_4, path.toString())
					binding?.contRoadNum4?.imAddRoadNum?.setImageURI(uriSrc)
				}
				REQUEST_CODE_GET_PATH_5 -> {
					viewModel?.onAddImage(INDEX_5, path.toString())
					binding?.contRoadNum5?.imAddRoadNum?.setImageURI(uriSrc)
				}
				REQUEST_CODE_GET_PATH_6 -> {
					viewModel?.onAddImage(INDEX_6, path.toString())
					binding?.contRoadNum6?.imAddRoadNum?.setImageURI(uriSrc)
				}
				else -> {
					MyLogger.log("Main fragment :: PickiTonCompleteListener :: error: $PICK_ERROR_MESSAGE")
					Toast.makeText(
						requireContext(),
						PICK_ERROR_MESSAGE,
						Toast.LENGTH_SHORT
					).show()
				}
			}
		} else {
			MyLogger.log("Main fragment :: PickiTonCompleteListener :: error: $reason")
			Toast.makeText(requireContext(), "$reason", Toast.LENGTH_SHORT)
				.show()
		}
	}

	override fun PickiTonMultipleCompleteListener(
		paths: ArrayList<String>?,
		wasSuccessful: Boolean,
		Reason: String?
	) {
		// no-op
	}

	private fun testImage(i: Int) {
		when (i) {
			INDEX_1 -> binding?.contRoadNum1?.imAddRoadNum?.setImageResource(R.drawable.back_image)
			INDEX_2 -> binding?.contRoadNum2?.imAddRoadNum?.setImageResource(R.drawable.back_image)
			INDEX_3 -> binding?.contRoadNum3?.imAddRoadNum?.setImageResource(R.drawable.back_image)
			INDEX_4 -> binding?.contRoadNum4?.imAddRoadNum?.setImageResource(R.drawable.back_image)
			INDEX_5 -> binding?.contRoadNum5?.imAddRoadNum?.setImageResource(R.drawable.back_image)
			INDEX_6 -> binding?.contRoadNum6?.imAddRoadNum?.setImageResource(R.drawable.back_image)
		}
	}

	private fun roadNumbersModelMapHandle(map: HashMap<Int, RoadSignInfo>) {
		binding?.apply {
			map[INDEX_1]?.let {
				bindUI(it, contRoadNum1.imAddRoadNum, Uri.parse(it.roadNumber))
			}
			map[INDEX_2]?.let {
				bindUI(it, contRoadNum2.imAddRoadNum, Uri.parse(it.roadNumber))
			}
			map[INDEX_3]?.let {
				bindUI(it, contRoadNum3.imAddRoadNum, Uri.parse(it.roadNumber))
			}
			map[INDEX_4]?.let {
				bindUI(it, contRoadNum4.imAddRoadNum, Uri.parse(it.roadNumber))
			}
			map[INDEX_5]?.let {
				bindUI(it, contRoadNum5.imAddRoadNum, Uri.parse(it.roadNumber))
			}
			map[INDEX_6]?.let {
				bindUI(it, contRoadNum6.imAddRoadNum, Uri.parse(it.roadNumber))
			}
		}
	}

	private fun bindUI(road: RoadSignInfo, imageView: ImageView, uri: Uri? = null) {
		binding?.apply {
			if (road.type == RoadSignType.IMAGE) {
				imageView.isVisible = true
				imageView.setImageURI(uri)
			}
		}
	}

	private fun openGallery(i: Int) {
		val reqCode = when (i) {
			INDEX_1 -> REQUEST_CODE_GET_PATH_1
			INDEX_2 -> REQUEST_CODE_GET_PATH_2
			INDEX_3 -> REQUEST_CODE_GET_PATH_3
			INDEX_4 -> REQUEST_CODE_GET_PATH_4
			INDEX_5 -> REQUEST_CODE_GET_PATH_5
			INDEX_6 -> REQUEST_CODE_GET_PATH_6
			else -> DEFAULT_INDEX
		}

		val intent = Intent()
		intent.type = IMAGE_TYPE
		intent.action = Intent.ACTION_OPEN_DOCUMENT
		startActivityForResult(intent, reqCode)
	}

	override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
		super.onActivityResult(requestCode, resultCode, data)
		if (resultCode == AppCompatActivity.RESULT_OK) {
			lastPickRequestCode = requestCode
			pickIt?.getPath(data?.data, Build.VERSION.SDK_INT)
		}
	}

	companion object {

		private const val DEFAULT_INDEX = -1
		private const val INDEX_1 = 1
		private const val INDEX_2 = 2
		private const val INDEX_3 = 3
		private const val INDEX_4 = 4
		private const val INDEX_5 = 5
		private const val INDEX_6 = 6

		private const val IMAGE_TYPE = "image/*"
		private const val PICK_ERROR_MESSAGE = "Неизвестная проблема с выбором изображения"

		private const val REQUEST_CODE_GET_PATH_1 = 201
		private const val REQUEST_CODE_GET_PATH_2 = 202
		private const val REQUEST_CODE_GET_PATH_3 = 203
		private const val REQUEST_CODE_GET_PATH_4 = 204
		private const val REQUEST_CODE_GET_PATH_5 = 205
		private const val REQUEST_CODE_GET_PATH_6 = 206

		@JvmStatic
		fun newInstance() = MainFragment()
	}

}