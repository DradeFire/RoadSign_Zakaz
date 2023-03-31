package com.bajenovsasha.roadsign_zakaz.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bajenovsasha.roadsign_zakaz.R
import com.bajenovsasha.roadsign_zakaz.app.App
import com.bajenovsasha.roadsign_zakaz.databinding.ActivityMainBinding
import com.bajenovsasha.roadsign_zakaz.navigation.Screens
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType
import com.github.terrakok.cicerone.androidx.AppNavigator

class MainActivity : AppCompatActivity() {

	private var binding: ActivityMainBinding? = null

	private val navigator = AppNavigator(this, R.id.container)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (binding == null) binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding?.root)

//		(applicationContext as? App)?.router?.newRootScreen(Screens.mainScreen())
//		(applicationContext as? App)?.router?.newRootScreen(Screens.roadSignScreen(1, RoadSignType.RUS_2))
	}

	override fun onResumeFragments() {
		super.onResumeFragments()
		(applicationContext as? App)?.navigatorHolder?.setNavigator(navigator)
	}

	override fun onPause() {
		(applicationContext as? App)?.navigatorHolder?.removeNavigator()
		super.onPause()
	}

	override fun onDestroy() {
		binding = null
		super.onDestroy()
	}

}