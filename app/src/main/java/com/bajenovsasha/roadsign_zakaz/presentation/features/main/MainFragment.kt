package com.bajenovsasha.roadsign_zakaz.presentation.features.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.bajenovsasha.roadsign_zakaz.R
import com.bajenovsasha.roadsign_zakaz.databinding.FragmentMainBinding
import com.bajenovsasha.roadsign_zakaz.presentation.base.BaseFragment
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignInfo
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType
import com.bajenovsasha.roadsign_zakaz.uikit.ChooseRoadNumberDialog
import com.bajenovsasha.roadsign_zakaz.utils.RoadNumberUiFormatter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.HashMap

class MainFragment : BaseFragment<FragmentMainBinding, MainViewModel>() {

	override val inflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainBinding
		get() = FragmentMainBinding::inflate
	override val viewModelClass: Class<MainViewModel>
		get() = MainViewModel::class.java

	override fun initUI() {
		clearUI()
		viewModel?.onInitView()
	}

	private fun clearUI() {
		binding?.txRoadNumber1?.text = ""
		binding?.txRoadNumber2?.text = ""
		binding?.txRoadNumber3?.text = ""
		binding?.txRoadNumber4?.text = ""
		binding?.txRoadNumber5?.text = ""
		binding?.txRoadNumber6?.text = ""

		binding?.imRoadNumber1?.setImageResource(R.drawable.ic_add)
		binding?.imRoadNumber2?.setImageResource(R.drawable.ic_add)
		binding?.imRoadNumber3?.setImageResource(R.drawable.ic_add)
		binding?.imRoadNumber4?.setImageResource(R.drawable.ic_add)
		binding?.imRoadNumber5?.setImageResource(R.drawable.ic_add)
		binding?.imRoadNumber6?.setImageResource(R.drawable.ic_add)
	}

	override fun initButtons() {
		binding?.apply {
			btSaveRoadNumberList.setOnClickListener {
				viewModel?.onSaveClicked { map ->
					drawView2.setAndSaveRoadNumbers(map)
				}
			}
			imRoadNumber1.setOnClickListener {
				onRoadNumberClicked(1)
			}
			imRoadNumber2.setOnClickListener {
				onRoadNumberClicked(2)
			}
			imRoadNumber3.setOnClickListener {
				onRoadNumberClicked(3)
			}
			imRoadNumber4.setOnClickListener {
				onRoadNumberClicked(4)
			}
			imRoadNumber5.setOnClickListener {
				onRoadNumberClicked(5)
			}
			imRoadNumber6.setOnClickListener {
				onRoadNumberClicked(6)
			}
		}
	}

	override fun initObservers() {
		viewModel?.let {
			compositeDisposable?.add(
				it.roadNumbersModelMap
					.subscribeOn(Schedulers.computation())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribe({ map ->
						roadNumbersModelMapHandle(map)
					}, { err ->
						err.printStackTrace()
						Toast.makeText(requireContext(), err.message, Toast.LENGTH_SHORT).show()
					})
			)
		}
	}

	private fun roadNumbersModelMapHandle(map: HashMap<Int, RoadSignInfo>) {
		binding?.apply {
			map[1]?.let {
				bindUI(it, txRoadNumber1, imRoadNumber1)
			}
			map[2]?.let {
				bindUI(it, txRoadNumber2, imRoadNumber2)
			}
			map[3]?.let {
				bindUI(it, txRoadNumber3, imRoadNumber3)
			}
			map[4]?.let {
				bindUI(it, txRoadNumber4, imRoadNumber4)
			}
			map[5]?.let {
				bindUI(it, txRoadNumber5, imRoadNumber5)
			}
			map[6]?.let {
				bindUI(it, txRoadNumber6, imRoadNumber6)
			}
		}
	}

	private fun bindUI(road: RoadSignInfo, txView: TextView, imageView: ImageView) {
		binding?.apply {
			txView.apply {
				isVisible = true
				text = RoadNumberUiFormatter.format(road.sign)
			}
			imageView.isVisible = true
			when(road.type) {
				RoadSignType.RUS_2 -> imageView.setImageResource(R.drawable.f1)
				RoadSignType.RUS_3 -> imageView.setImageResource(R.drawable.f2)
			}
		}
	}

	private fun onRoadNumberClicked(i: Int) {
		ChooseRoadNumberDialog { type: RoadSignType ->
			viewModel?.onAddRoadNumClicked(i, type)
		}.show(requireActivity().supportFragmentManager, "choose_road_number")
	}

	companion object {

		@JvmStatic
		fun newInstance() = MainFragment()
	}
}