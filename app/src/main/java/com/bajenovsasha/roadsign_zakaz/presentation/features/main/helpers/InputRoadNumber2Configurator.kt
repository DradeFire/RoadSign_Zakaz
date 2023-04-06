package com.bajenovsasha.roadsign_zakaz.presentation.features.main.helpers

import androidx.lifecycle.MutableLiveData
import com.bajenovsasha.roadsign_zakaz.R
import com.bajenovsasha.roadsign_zakaz.databinding.InputRoadNumber2Binding
import com.bajenovsasha.roadsign_zakaz.utils.RoadNumberFormatter_OneChar
import com.google.android.material.textfield.TextInputEditText

class InputRoadNumber2Configurator {

	fun configure(
		binding: InputRoadNumber2Binding,
		currentRoadNumberInput: MutableLiveData<Int>,
		openCustomKeyboardValue: Int,
		openCustomKeyboard: () -> Unit,
		hideKeyboard: () -> Unit
	) {
		binding.apply {
			edR2Text1.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR2Text2.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR2Text3.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR2Num1.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR2Num2.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR2Num3.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR2Num4.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR2Num5.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
		}
	}

	fun bindRoadNumber(
		edText: TextInputEditText,
		etInputRN: InputRoadNumber2Binding?,
		roadSignChar: Char,
		currentRoadNumberInput: MutableLiveData<Int>,
		openCustomKeyboardValue: Int,
		hideCustomKeyboard: () -> Unit,
		showButton: () -> Unit
	) {
		currentRoadNumberInput.value = openCustomKeyboardValue
		val char: Char? = when (edText.id) {
			R.id.edR2Num1 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR2Num2 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR2Num3 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR2Num4 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR2Num5 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR2Text1 -> if (RoadNumberFormatter_OneChar.validChar(roadSignChar)) roadSignChar else null
			R.id.edR2Text2 -> if (RoadNumberFormatter_OneChar.validChar(roadSignChar)) roadSignChar else null
			R.id.edR2Text3 -> if (RoadNumberFormatter_OneChar.validChar(roadSignChar)) roadSignChar else null
			else -> null
		}
		char?.let { charIt ->
			edText.setText(charIt.toString())
			when (edText.nextFocusForwardId) {
				R.id.edR2Num1 -> etInputRN?.edR2Num1?.requestFocus()
				R.id.edR2Num2 -> etInputRN?.edR2Num2?.requestFocus()
				R.id.edR2Num3 -> etInputRN?.edR2Num3?.requestFocus()
				R.id.edR2Num4 -> etInputRN?.edR2Num4?.requestFocus()
				R.id.edR2Num5 -> etInputRN?.edR2Num5?.requestFocus()
				R.id.edR2Text1 -> etInputRN?.edR2Text1?.requestFocus()
				R.id.edR2Text2 -> etInputRN?.edR2Text2?.requestFocus()
				R.id.edR2Text3 -> etInputRN?.edR2Text3?.requestFocus()
				else -> {
					etInputRN?.edR2Num5?.clearFocus()
					hideCustomKeyboard()
					showButton()
				}
			}
		}
	}

	fun clear(etInputRN: InputRoadNumber2Binding) {
		etInputRN.edR2Num1.setText("")
		etInputRN.edR2Num2.setText("")
		etInputRN.edR2Num3.setText("")
		etInputRN.edR2Num4.setText("")
		etInputRN.edR2Num5.setText("")
		etInputRN.edR2Text1.setText("")
		etInputRN.edR2Text2.setText("")
		etInputRN.edR2Text3.setText("")
	}

	fun getFullString(etInputRN: InputRoadNumber2Binding): String {
		return StringBuilder().apply {
			append(etInputRN.edR2Num1.text ?: ' ')
			append(etInputRN.edR2Num2.text ?: ' ')
			append(etInputRN.edR2Num3.text ?: ' ')
			append(etInputRN.edR2Num4.text ?: ' ')
			append(etInputRN.edR2Num5.text ?: ' ')
			append(etInputRN.edR2Text1.text ?: ' ')
			append(etInputRN.edR2Text2.text ?: ' ')
			append(etInputRN.edR2Text3.text ?: ' ')
		}.toString()
	}

}