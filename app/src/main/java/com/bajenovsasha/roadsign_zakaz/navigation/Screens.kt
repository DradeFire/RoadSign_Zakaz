package com.bajenovsasha.roadsign_zakaz.navigation

import com.bajenovsasha.roadsign_zakaz.presentation.features.main.MainFragment
import com.bajenovsasha.roadsign_zakaz.presentation.features.roaddraw.RoadDrawFragment
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
	fun roadSignScreen(id: Int, type: RoadSignType): FragmentScreen = FragmentScreen { RoadDrawFragment.newInstance(id, type) }

	fun mainScreen(): FragmentScreen = FragmentScreen { MainFragment.newInstance() }

}