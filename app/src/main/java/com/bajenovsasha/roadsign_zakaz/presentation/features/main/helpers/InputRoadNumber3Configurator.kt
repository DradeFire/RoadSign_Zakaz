package com.bajenovsasha.roadsign_zakaz.presentation.features.main.helpers

import androidx.lifecycle.MutableLiveData
import com.bajenovsasha.roadsign_zakaz.R
import com.bajenovsasha.roadsign_zakaz.databinding.InputRoadNumber3Binding
import com.bajenovsasha.roadsign_zakaz.utils.RoadNumberFormatter_OneChar
import com.google.android.material.textfield.TextInputEditText

class InputRoadNumber3Configurator {

	fun configure(
		binding: InputRoadNumber3Binding,
		currentRoadNumberInput: MutableLiveData<Int>,
		openCustomKeyboardValue: Int,
		openCustomKeyboard: () -> Unit,
		hideKeyboard: () -> Unit
	) {
		binding.apply {
			edR3Text1.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR3Text2.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR3Text3.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR3Num1.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR3Num2.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR3Num3.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR3Num4.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR3Num5.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
			edR3Num6.setOnTouchListener { _, _ ->
				currentRoadNumberInput.value = openCustomKeyboardValue
				openCustomKeyboard()
				hideKeyboard()
				false
			}
		}
	}

	fun bindRoadNumber(
		edText: TextInputEditText,
		etInputRN: InputRoadNumber3Binding?,
		roadSignChar: Char,
		currentRoadNumberInput: MutableLiveData<Int>,
		openCustomKeyboardValue: Int,
		hideCustomKeyboard: () -> Unit,
		showButton: () -> Unit
	) {
		currentRoadNumberInput.value = openCustomKeyboardValue
		val char: Char? = when (edText.id) {
			R.id.edR3Num1 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR3Num2 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR3Num3 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR3Num4 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR3Num5 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR3Num6 -> if (RoadNumberFormatter_OneChar.validNumber(roadSignChar)) roadSignChar else null
			R.id.edR3Text1 -> if (RoadNumberFormatter_OneChar.validChar(roadSignChar)) roadSignChar else null
			R.id.edR3Text2 -> if (RoadNumberFormatter_OneChar.validChar(roadSignChar)) roadSignChar else null
			R.id.edR3Text3 -> if (RoadNumberFormatter_OneChar.validChar(roadSignChar)) roadSignChar else null
			else -> null
		}
		if (char == ' ') {
			when (edText.id) {
				R.id.edR3Num1 -> {
					if (edText.text?.length == 1)
						etInputRN?.edR3Num1?.setText("")
					else {
						etInputRN?.edR3Text1?.setText("")
						etInputRN?.edR3Text1?.requestFocus()
					}
				}
				R.id.edR3Num2 -> {
					if (edText.text?.length == 1)
						etInputRN?.edR3Num2?.setText("")
					else {
						etInputRN?.edR3Num1?.setText("")
						etInputRN?.edR3Num1?.requestFocus()
					}
				}
				R.id.edR3Num3 -> {
					if (edText.text?.length == 1)
						etInputRN?.edR3Num3?.setText("")
					else {
						etInputRN?.edR3Num2?.setText("")
						etInputRN?.edR3Num2?.requestFocus()
					}
				}
				R.id.edR3Num4 -> {
					if (edText.text?.length == 1)
						etInputRN?.edR3Num4?.setText("")
					else {
						etInputRN?.edR3Text3?.setText("")
						etInputRN?.edR3Text3?.requestFocus()
					}
				}
				R.id.edR3Num5 -> {
					if (edText.text?.length == 1)
						etInputRN?.edR3Num5?.setText("")
					else {
						etInputRN?.edR3Num4?.setText("")
						etInputRN?.edR3Num4?.requestFocus()
					}
				}
				R.id.edR3Num6 -> {
					if (edText.text?.length == 1)
						etInputRN?.edR3Num6?.setText("")
					else {
						etInputRN?.edR3Num5?.setText("")
						etInputRN?.edR3Num5?.requestFocus()
					}
				}
				R.id.edR3Text1 -> {
					if (edText.text?.length == 1)
						etInputRN?.edR3Text1?.setText("")
				}
				R.id.edR3Text2 -> {
					if (edText.text?.length == 1)
						etInputRN?.edR3Text2?.setText("")
					else {
						etInputRN?.edR3Num3?.setText("")
						etInputRN?.edR3Num3?.requestFocus()
					}
				}
				R.id.edR3Text3 -> {
					if (edText.text?.length == 1)
						etInputRN?.edR3Text3?.setText("")
					else {
						etInputRN?.edR3Text2?.setText("")
						etInputRN?.edR3Text2?.requestFocus()
					}
				}
				else -> null
			}
		} else {
			char?.let { charIt ->
				edText.setText(charIt.toString())
				when (edText.nextFocusForwardId) {
					R.id.edR3Num1 -> etInputRN?.edR3Num1?.requestFocus()
					R.id.edR3Num2 -> etInputRN?.edR3Num2?.requestFocus()
					R.id.edR3Num3 -> etInputRN?.edR3Num3?.requestFocus()
					R.id.edR3Num4 -> etInputRN?.edR3Num4?.requestFocus()
					R.id.edR3Num5 -> etInputRN?.edR3Num5?.requestFocus()
					R.id.edR3Num6 -> etInputRN?.edR3Num6?.requestFocus()
					R.id.edR3Text1 -> etInputRN?.edR3Text1?.requestFocus()
					R.id.edR3Text2 -> etInputRN?.edR3Text2?.requestFocus()
					R.id.edR3Text3 -> etInputRN?.edR3Text3?.requestFocus()
					else -> {
						etInputRN?.edR3Num6?.clearFocus()
						hideCustomKeyboard()
						showButton()
					}
				}
			}
		}
	}

	fun clear(ed3: InputRoadNumber3Binding) {
		ed3.edR3Num1.setText("")
		ed3.edR3Num2.setText("")
		ed3.edR3Num3.setText("")
		ed3.edR3Num4.setText("")
		ed3.edR3Num5.setText("")
		ed3.edR3Num6.setText("")
		ed3.edR3Text1.setText("")
		ed3.edR3Text2.setText("")
		ed3.edR3Text3.setText("")
	}

	fun getFullString(ed3: InputRoadNumber3Binding): String {
		return StringBuilder().apply {
			append(ed3.edR3Text1.text ?: ' ')
			append(ed3.edR3Num1.text ?: ' ')
			append(ed3.edR3Num2.text ?: ' ')
			append(ed3.edR3Num3.text ?: ' ')
			append(ed3.edR3Text2.text ?: ' ')
			append(ed3.edR3Text3.text ?: ' ')
			append(ed3.edR3Num4.text ?: ' ')
			append(ed3.edR3Num5.text ?: ' ')
			append(ed3.edR3Num6.text ?: ' ')
		}.toString()
	}

	fun initCursor(ed3: InputRoadNumber3Binding, function: () -> Unit) {
		function()
		ed3.edR3Text1.requestFocus()
	}

}