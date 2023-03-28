package com.bajenovsasha.roadsign_zakaz.presentation.base

import androidx.lifecycle.ViewModel
import com.bajenovsasha.roadsign_zakaz.presentation.activity.ActivityViewModel
import com.github.terrakok.cicerone.Router

abstract class BaseViewModel : ViewModel() {

	private var _activityVM: ActivityViewModel? = null
	protected val activityVM get() = _activityVM
	fun initActivityVM(viewModel: ActivityViewModel) {
		_activityVM = viewModel
	}

	private var _router: Router? = null
	protected val router get() = _router
	fun initRouter(router: Router) {
		_router = router
	}


	override fun onCleared() {
		_activityVM = null
		super.onCleared()
	}

}