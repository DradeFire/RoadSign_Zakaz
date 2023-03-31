package com.bajenovsasha.roadsign_zakaz.uikit

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.bajenovsasha.roadsign_zakaz.R
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignInfo
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType
import com.bajenovsasha.roadsign_zakaz.utils.FileUtils
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

	private val bitmapSourceRoad2: Bitmap = context.getDrawable(R.drawable.f1)!!.toBitmap()
	private val bitmapSourceRoad3: Bitmap = context.getDrawable(R.drawable.f2)!!.toBitmap()
	private val bitmap: Bitmap = Bitmap.createBitmap(
		150.dp.roundToInt(),
		228.dp.roundToInt(),
		Bitmap.Config.RGBA_F16,
		true
	)

	override fun onDraw(canvas: Canvas?) {
		super.onDraw(canvas)
		val bitmapCanvas = Canvas(bitmap)
		bitmapCanvas.drawColor(Color.GREEN)

		for (i in 0..5) {
			val w = 8.dp
			val h = bitmapSourceRoad2.height.toFloat() * i + 2.5.dp * (2 * i + 1) + 3.dp

			bitmapCanvas.drawBitmap(
				bitmapSourceRoad2,
				w,
				h,
				paint
			)
			bitmapCanvas.drawText(
				"О999ОО99".elementAt(0).toString(),
				w + 6.5.dp,
				h + 24.7.dp,
				textPaint
			)
			bitmapCanvas.drawText(
				"О999ОО99".elementAt(1).toString(),
				w + 6.5.dp + 17.dp,
				h + 24.7.dp,
				textNumPaint
			)
			bitmapCanvas.drawText(
				"О999ОО99".elementAt(2).toString(),
				w + 6.5.dp + 31.dp,
				h + 24.7.dp,
				textNumPaint
			)
			bitmapCanvas.drawText(
				"О999ОО99".elementAt(3).toString(),
				w + 6.5.dp + 46.dp,
				h + 24.7.dp,
				textNumPaint
			)
			bitmapCanvas.drawText(
				"О999ОО99".elementAt(4).toString(),
				w + 6.5.dp + 62.5.dp,
				h + 24.7.dp,
				textPaint
			)
			bitmapCanvas.drawText(
				"О999ОО99".elementAt(5).toString(),
				w + 6.5.dp + 77.dp,
				h + 24.7.dp,
				textPaint
			)
			bitmapCanvas.drawText(
				"О999ОО99".elementAt(6).toString(),
				w + 6.5.dp + 97.5.dp,
				h + 19.5.dp,
				textMiniNumPaint
			)
			bitmapCanvas.drawText(
				"О999ОО99".elementAt(7).toString(),
				w + 6.5.dp + 109.dp,
				h + 19.5.dp,
				textMiniNumPaint
			)
		}

		canvas?.drawBitmap(bitmap, 0f, 0f, paint)
	}

	fun setAndSaveRoadNumbers(map: HashMap<Int, RoadSignInfo>) {
		val bitmapCanvas = Canvas(bitmap)
		bitmapCanvas.drawColor(Color.WHITE)

		for (i in 0..5) {
			val w = 8.dp
			val h = bitmapSourceRoad2.height.toFloat() * i + 2.5.dp * (2 * i + 1) + 3.dp

			map[i + 1]?.let { roadSignInfo ->
				when (roadSignInfo.type) {
					RoadSignType.RUS_2 -> {
						bitmapCanvas.drawBitmap(
							bitmapSourceRoad2,
							w,
							h,
							paint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(0).toString(),
							w + 6.5.dp,
							h + 24.7.dp,
							textPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(1).toString(),
							w + 6.5.dp + 17.dp,
							h + 24.7.dp,
							textNumPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(2).toString(),
							w + 6.5.dp + 31.dp,
							h + 24.7.dp,
							textNumPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(3).toString(),
							w + 6.5.dp + 46.dp,
							h + 24.7.dp,
							textNumPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(4).toString(),
							w + 6.5.dp + 62.5.dp,
							h + 24.7.dp,
							textPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(5).toString(),
							w + 6.5.dp + 77.dp,
							h + 24.7.dp,
							textPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(6).toString(),
							w + 6.5.dp + 97.5.dp,
							h + 19.5.dp,
							textMiniNumPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(7).toString(),
							w + 6.5.dp + 109.dp,
							h + 19.5.dp,
							textMiniNumPaint
						)
					}
					RoadSignType.RUS_3 -> {
						bitmapCanvas.drawBitmap(
							bitmapSourceRoad3,
							w,
							h,
							paint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(0).toString(),
							w + 5.dp,
							h + 24.7.dp,
							textPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(1).toString(),
							w + 5.dp + 14.dp,
							h + 24.7.dp,
							textNumPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(2).toString(),
							w + 5.dp + 29.dp,
							h + 24.7.dp,
							textNumPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(3).toString(),
							w + 5.dp + 43.2.dp,
							h + 24.7.dp,
							textNumPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(4).toString(),
							w + 5.dp + 58.dp,
							h + 24.7.dp,
							textPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(5).toString(),
							w + 5.dp + 72.5.dp,
							h + 24.7.dp,
							textPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(6).toString(),
							w + 5.dp + 90.dp,
							h + 19.dp,
							textMiniNumPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(7).toString(),
							w + 5.dp + 101.dp,
							h + 19.dp,
							textMiniNumPaint
						)
						bitmapCanvas.drawText(
							roadSignInfo.sign.elementAt(8).toString(),
							w + 5.dp + 112.dp,
							h + 19.dp,
							textMiniNumPaint
						)
					}
				}
			}

		}
		FileUtils.saveImage(bitmap)
		Toast.makeText(context, "Успешное сохранение файла!", Toast.LENGTH_SHORT).show()
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