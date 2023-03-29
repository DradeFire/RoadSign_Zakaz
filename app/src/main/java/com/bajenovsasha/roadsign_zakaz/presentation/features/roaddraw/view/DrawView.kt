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
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.bajenovsasha.roadsign_zakaz.R
import kotlin.math.roundToInt

class DrawView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val paint: Paint = Paint(Paint.FILTER_BITMAP_FLAG)
	private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		typeface = ResourcesCompat.getFont(context, R.font.roadn_font)
		textSize = 27.sp
	}
	private val textNumPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		typeface = ResourcesCompat.getFont(context, R.font.roadn_font)
		textSize = 29.sp
	}
	private val textMiniNumPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		typeface = ResourcesCompat.getFont(context, R.font.roadn_font)
		textSize = 22.sp
	}

	var bitmapSource: Bitmap? = context.getDrawable(R.drawable.f2)?.toBitmap()
	var bitmap: Bitmap

	init {
		bitmap = Bitmap.createBitmap(
			150.dp.roundToInt(),
			247.dp.roundToInt(),
			Bitmap.Config.RGBA_F16,
			true
		)
		val _canvas = Canvas(bitmap)
		_canvas.drawColor(Color.GREEN)

		for (i in 0..5) {
			val w = 10.dp
			val h = bitmapSource!!.height.toFloat() * i + 5.dp * (2 * i + 1) + 3.dp
			_canvas.drawBitmap(
				bitmapSource!!,
				w,
				h,
				paint
			)

			_canvas.drawText("О", w + 5.dp, h + 24.7.dp, textPaint)
			_canvas.drawText("Н", w + 5.dp + 58.dp, h + 24.7.dp, textPaint)

			_canvas.drawText("9", w + 5.dp + 14.dp, h + 24.7.dp, textNumPaint)
			_canvas.drawText("1", w + 5.dp + 29.dp, h + 24.7.dp, textNumPaint)
			_canvas.drawText("6", w + 5.dp + 43.2.dp, h + 24.7.dp, textNumPaint)

			_canvas.drawText("К", w + 5.dp + 72.5.dp, h + 24.7.dp, textPaint)

			_canvas.drawText("7", w + 5.dp + 90.dp, h + 19.dp, textMiniNumPaint)
			_canvas.drawText("5", w + 5.dp + 101.dp, h + 19.dp, textMiniNumPaint)
			_canvas.drawText("0", w + 5.dp + 112.dp, h + 19.dp, textMiniNumPaint)
		}
//		FileUtils.saveImage(bitmap)
	}

	override fun onDraw(canvas: Canvas) {
		canvas.drawBitmap(bitmap, 0f, 0f, paint)
	}

	private val Number.dp
		get() = TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_DIP,
			this.toFloat(),
			Resources.getSystem().displayMetrics
		)
	private val Number.sp
		get() = TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_SP,
			this.toFloat(),
			Resources.getSystem().displayMetrics
		)
}