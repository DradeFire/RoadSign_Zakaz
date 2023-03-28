package com.bajenovsasha.roadsign_zakaz.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.bajenovsasha.roadsign_zakaz.app.App
import com.bajenovsasha.roadsign_zakaz.presentation.activity.ActivityViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseFragment<B: ViewBinding, VM: BaseViewModel> : Fragment() {

	protected abstract val inflater: (LayoutInflater, ViewGroup?, Boolean) -> B
	private var _binding: B? = null
	protected val binding get() = _binding

	private var _viewModel: VM? = null
	protected val viewModel get() = _viewModel
	protected abstract val viewModelClass: Class<VM>

	private var _activityVM: ActivityViewModel? = null

	private var _compositeDisposable: CompositeDisposable? = null
	protected val compositeDisposable get() = _compositeDisposable

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		if (_binding == null) {
			_binding = inflater(inflater, container, false)
		}
		return _binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		if (_viewModel == null) {
			_viewModel = ViewModelProvider(requireActivity())[viewModelClass]
			_compositeDisposable = CompositeDisposable()

			_activityVM = ViewModelProvider(requireActivity())[ActivityViewModel::class.java].also {
				(_viewModel as? BaseViewModel)?.initActivityVM(it)
			}

			(_viewModel as? BaseViewModel)?.initRouter((requireContext().applicationContext as App).router)

			initStartValues()
			initUI()
			initRecycler()
			initObservers()
			initButtons()
		}
	}

	protected open fun initUI() {

	}

	protected open fun initObservers() {

	}

	protected open fun initStartValues() {

	}

	protected open fun initButtons() {

	}

	protected open fun initRecycler() {

	}

	override fun onDetach() {
		_activityVM = null
		_binding = null
		_viewModel = null
		_compositeDisposable?.dispose()
		_compositeDisposable?.clear()
		_compositeDisposable = null
		super.onDetach()
	}

}