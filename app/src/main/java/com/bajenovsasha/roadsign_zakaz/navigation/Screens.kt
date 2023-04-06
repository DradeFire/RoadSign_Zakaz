package com.bajenovsasha.roadsign_zakaz.navigation

import com.bajenovsasha.roadsign_zakaz.presentation.features.main.MainFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

object Screens {
	fun mainScreen(): FragmentScreen = FragmentScreen { MainFragment.newInstance() }

}