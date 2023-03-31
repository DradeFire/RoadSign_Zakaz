package com.bajenovsasha.roadsign_zakaz.app

import android.app.Application
import com.github.terrakok.cicerone.Cicerone

class App : Application() {

	private val cicerone = Cicerone.create()
	val router get() = cicerone.router
	val navigatorHolder get() = cicerone.getNavigatorHolder()

}