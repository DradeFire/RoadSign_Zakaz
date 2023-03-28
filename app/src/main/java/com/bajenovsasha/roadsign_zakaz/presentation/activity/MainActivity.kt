package com.bajenovsasha.roadsign_zakaz.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bajenovsasha.roadsign_zakaz.R
import com.bajenovsasha.roadsign_zakaz.app.App
import com.bajenovsasha.roadsign_zakaz.databinding.ActivityMainBinding
import com.bajenovsasha.roadsign_zakaz.navigation.Screens
import com.github.terrakok.cicerone.androidx.AppNavigator

//class MainActivity : AppCompatActivity() {
//
//	override fun onCreate(savedInstanceState: Bundle?) {
//		super.onCreate(savedInstanceState)
//		val binding = ActivityMainBinding.inflate(layoutInflater)
//		setContentView(binding.root)
//
//		binding.testText.apply {
//			typeface = ResourcesCompat.getFont(this@MainActivity, R.font.roadn_font)
//			setTextSize(TypedValue.COMPLEX_UNIT_SP, 40f)
//			text = "ABE abe 123 456 АБЕ абе"
//		}
//	}
//}
class MainActivity : AppCompatActivity() {

	private var binding: ActivityMainBinding? = null

	private val navigator = AppNavigator(this, R.id.container)

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (binding == null) binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding?.root)

		(applicationContext as? App)?.router?.newRootScreen(Screens.roadSignScreen(0))
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