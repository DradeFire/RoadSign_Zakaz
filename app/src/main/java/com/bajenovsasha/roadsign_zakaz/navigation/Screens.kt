package com.bajenovsasha.roadsign_zakaz.navigation

import com.bajenovsasha.roadsign_zakaz.presentation.features.roaddraw.RoadDrawFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
	fun roadSignScreen(id: Int): FragmentScreen = FragmentScreen { RoadDrawFragment.newInstance(id) }

}