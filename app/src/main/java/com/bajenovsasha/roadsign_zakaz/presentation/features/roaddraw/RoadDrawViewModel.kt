package com.bajenovsasha.roadsign_zakaz.presentation.features.roaddraw

import com.bajenovsasha.roadsign_zakaz.utils.RoadNumberFormatter
import com.bajenovsasha.roadsign_zakaz.presentation.base.BaseViewModel
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType
import io.reactivex.rxjava3.subjects.BehaviorSubject

class RoadDrawViewModel : BaseViewModel() {

	val roadSignString: BehaviorSubject<String> = BehaviorSubject.createDefault("")

	fun onCustomKeyClicked(primaryCode: Int, roadNumberType: RoadSignType) {
		roadSignString.value?.let {
			roadSignString.onNext(RoadNumberFormatter.format(primaryCode, roadNumberType, it))
		}
	}

	fun onSaveClicked(id: Int, roadNumberType: RoadSignType) {
		TODO("Not yet implemented")
	}
}