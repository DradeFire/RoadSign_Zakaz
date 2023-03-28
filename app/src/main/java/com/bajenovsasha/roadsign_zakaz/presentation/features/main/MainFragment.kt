package com.bajenovsasha.roadsign_zakaz.presentation.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bajenovsasha.roadsign_zakaz.databinding.FragmentMainBinding
import com.bajenovsasha.roadsign_zakaz.presentation.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

	override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
		get() = FragmentMainBinding::inflate
	override val viewModelClass: Class<MainViewModel>
		get() = MainViewModel::class.java

	// TODO: Реализовать список из 6 "плюсиков" и кнопки "сохранить"

	companion object {

		@JvmStatic
		fun newInstance() = MainFragment()
	}
}