package com.bajenovsasha.roadsign_zakaz.presentation.activity

import androidx.lifecycle.ViewModel
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignInfo

class ActivityViewModel : ViewModel() {

	val roadNumbersModelMap = hashMapOf<Int, RoadSignInfo>()

}