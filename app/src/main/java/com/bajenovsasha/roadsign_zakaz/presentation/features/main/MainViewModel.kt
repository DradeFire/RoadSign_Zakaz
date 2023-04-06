package com.bajenovsasha.roadsign_zakaz.presentation.features.main

import com.bajenovsasha.roadsign_zakaz.presentation.base.BaseViewModel
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignInfo
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType
import com.bajenovsasha.roadsign_zakaz.utils.RoadNumberFormatter_OneChar
import io.reactivex.rxjava3.subjects.BehaviorSubject

class MainViewModel : BaseViewModel() {

	val roadSignChar: BehaviorSubject<Char> = BehaviorSubject.createDefault(' ')

	fun onSaveClicked(func: (HashMap<Int, RoadSignInfo>) -> Unit) {
		activityVM?.roadNumbersModelMap?.let { func(it) }
	}

	fun onCustomKeyClicked(primaryCode: Int) {
		roadSignChar.value?.let {
			roadSignChar.onNext(RoadNumberFormatter_OneChar.format(primaryCode))
		}
	}

	fun onAddImage(i: Int, uriStr: String) {
		activityVM?.roadNumbersModelMap?.let { it[i] = RoadSignInfo(RoadSignType.IMAGE, uriStr) }
	}

}