package com.bajenovsasha.roadsign_zakaz.presentation.features.main

import com.bajenovsasha.roadsign_zakaz.navigation.Screens
import com.bajenovsasha.roadsign_zakaz.presentation.base.BaseViewModel
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignInfo
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType
import io.reactivex.rxjava3.subjects.PublishSubject

class MainViewModel : BaseViewModel() {

	val roadNumbersModelMap: PublishSubject<HashMap<Int, RoadSignInfo>> = PublishSubject.create()

	fun onInitView() {
		activityVM?.roadNumbersModelMap?.let { roadNumbersModelMap.onNext(it) }
	}

	fun onSaveClicked() {
		// Сохранить картинку с разметкой
	}

	fun onAddRoadNumClicked(idNumber: Int, type: RoadSignType) {
		router?.navigateTo(Screens.roadSignScreen(idNumber, type))
	}
}