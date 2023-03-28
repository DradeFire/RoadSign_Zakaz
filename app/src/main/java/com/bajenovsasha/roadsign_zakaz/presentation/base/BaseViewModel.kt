package com.bajenovsasha.roadsign_zakaz.presentation.base

import androidx.lifecycle.ViewModel
import com.bajenovsasha.roadsign_zakaz.presentation.activity.ActivityViewModel

abstract class BaseViewModel : ViewModel() {

	private var _activityVM: ActivityViewModel? = null
	protected val activityVM get() = _activityVM
	fun initActivityVM(viewModel: ActivityViewModel) {
		_activityVM = viewModel
	}


	override fun onCleared() {
		_activityVM = null
		super.onCleared()
	}

}