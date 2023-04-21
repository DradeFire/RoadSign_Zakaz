package com.bajenovsasha.roadsign_zakaz.uikit

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bajenovsasha.roadsign_zakaz.R
import com.bajenovsasha.roadsign_zakaz.common.MyLogger
import com.bajenovsasha.roadsign_zakaz.databinding.DialogChooseImageBinding
import com.bajenovsasha.roadsign_zakaz.databinding.ItemImageToChooseBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class ChooseImageDialog(
	private val funAddImage: (Int) -> Unit?,
) : DialogFragment() {

	private var binding: DialogChooseImageBinding? = null
	private val textChangeListener: BehaviorSubject<String> = BehaviorSubject.create()
	private var textChangeListenerDisposable: Disposable? = null

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		MyLogger.log("ChooseImageDialog:onCreateDialog:info = get")
		binding = DialogChooseImageBinding.inflate(LayoutInflater.from(context))

		return AlertDialog.Builder(requireContext())
			.setView(binding?.root)
			.create()
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
//		binding = DialogChooseImageBinding.inflate(inflater, container, false)
		return binding?.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		textChangeListenerDisposable = textChangeListener
			.subscribeOn(AndroidSchedulers.mainThread())
			.observeOn(AndroidSchedulers.mainThread())
			.distinctUntilChanged()
			.debounce(300, TimeUnit.MILLISECONDS)
			.subscribe ({ inputText ->
				requireActivity().runOnUiThread {
					val imageIdList = imageNameToImageId.keys
						.filter { it.contains(inputText) }
						.mapNotNull {
							imageNameToImageId[it]
						}
					(binding?.recyclerImageList?.adapter as? ImageRecyclerAdapter)?.setNewData(imageIdList)
				}
			}, { err ->
				MyLogger.log("ChooseImageDialog:onCreateDialog:error = ", err)
			})

		binding?.apply {
			etSearchImage.addTextChangedListener(object : TextWatcher {
				override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
					// no-op
				}

				override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
					textChangeListener.onNext(text.toString())
				}

				override fun afterTextChanged(p0: Editable?) {
					// no-op
				}

			})
			val imageList: ArrayList<Int> = imageNameToImageId.values.toList() as ArrayList<Int>
			recyclerImageList.layoutManager = LinearLayoutManager(requireActivity())
			recyclerImageList.adapter = ImageRecyclerAdapter(imageList)
		}
	}

	override fun onDestroyView() {
		super.onDestroyView()
		binding = null
		textChangeListenerDisposable?.dispose()
		textChangeListenerDisposable = null
	}

	inner class ImageRecyclerAdapter(private val imageList: ArrayList<Int>) :
		RecyclerView.Adapter<ImageRecyclerAdapter.VH>() {

		init {
			MyLogger.log("imageList size = ${imageList.size}")
		}

		inner class VH(private val binding: ItemImageToChooseBinding) :
			RecyclerView.ViewHolder(binding.root) {

			fun bind(imageId: Int) = with(binding) {
				MyLogger.log("ImageRecyclerAdapter:VM:bind = get")
				image.setImageResource(imageId)
				root.setOnClickListener {
					dismiss()
					funAddImage(imageId)
				}
			}
		}

		override fun onBindViewHolder(holder: VH, position: Int) {
			holder.bind(imageList[position])
		}

		override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
			return VH(
				ItemImageToChooseBinding.inflate(
					LayoutInflater.from(parent.context),
					parent,
					false
				)
			)
		}

		override fun getItemCount(): Int { return imageList.size }

		@SuppressLint("NotifyDataSetChanged")
		fun setNewData(newImageList: List<Int>) {
			imageList.clear()
			imageList.addAll(newImageList)
			notifyDataSetChanged()
		}
	}

	private val imageNameToImageId = hashMapOf(
		"im_bmw" to R.drawable.im_bmw,
		"im_ford" to R.drawable.im_ford,
		"im_honda" to R.drawable.im_honda,
		"im_jeep" to R.drawable.im_jeep,
		"im_kia" to R.drawable.im_kia,
		"im_lada" to R.drawable.im_lada,
		"im_lamb" to R.drawable.im_lamb,
	)

	companion object {

		fun newInstance(funAddImage: (Int) -> Unit?): ChooseImageDialog = ChooseImageDialog(funAddImage)
	}
}