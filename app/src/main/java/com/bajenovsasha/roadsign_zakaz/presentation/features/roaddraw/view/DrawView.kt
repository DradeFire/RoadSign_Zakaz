package com.bajenovsasha.roadsign_zakaz.presentation.features.roaddraw.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import com.bajenovsasha.roadsign_zakaz.R
import kotlin.math.roundToInt

class DrawView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private var paint: Paint = Paint(Paint.FILTER_BITMAP_FLAG)
	var bitmapSource: Bitmap? = context?.getDrawable(R.drawable.f2)?.toBitmap()
	var bitmap: Bitmap

	init {
		bitmap = Bitmap.createBitmap(
			180.dp.roundToInt(),
			270.dp.roundToInt(),
			Bitmap.Config.RGBA_F16,
			true
		)
		val _canvas = Canvas(bitmap)
		_canvas.drawColor(Color.GREEN)
		for (i in 0..5) {
			_canvas.drawBitmap(
				bitmapSource!!,
				10.dp,
				bitmapSource!!.height.toFloat() * i + 5.dp * (2 * i + 1) + 3.dp,
				paint
			)
		}
//		FileUtils.saveImage(bitmap)
	}

	override fun onDraw(canvas: Canvas) {
		canvas.drawBitmap(bitmap, 0f, 0f, paint)
	}

	val Number.dp
		get() = TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_DIP,
			this.toFloat(),
			Resources.getSystem().displayMetrics
		)
}