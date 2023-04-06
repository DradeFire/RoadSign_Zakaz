package com.bajenovsasha.roadsign_zakaz.uikit

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.net.Uri
import android.provider.MediaStore
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.core.content.res.ResourcesCompat
import androidx.core.graphics.drawable.toBitmap
import com.bajenovsasha.roadsign_zakaz.R
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignInfo
import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType
import com.bajenovsasha.roadsign_zakaz.utils.FileUtils
import java.io.File
import java.net.URI
import kotlin.math.roundToInt

class DrawView @JvmOverloads constructor(
	context: Context,
	attrs: AttributeSet? = null,
	defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

	private val paint: Paint = Paint(Paint.FILTER_BITMAP_FLAG)
	private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		typeface =
			Typeface.create(ResourcesCompat.getFont(context, R.font.roadn_font)!!, Typeface.BOLD)
		textSize = 27.dp
	}
	private val textNumPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		typeface =
			Typeface.create(ResourcesCompat.getFont(context, R.font.roadn_font)!!, Typeface.BOLD)
		textSize = 29.dp
	}
	private val textMiniNumPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
		typeface =
			Typeface.create(ResourcesCompat.getFont(context, R.font.roadn_font)!!, Typeface.BOLD)
		textSize = 22.dp
	}

	private val testSign = "О999ОО99"

	private val bitmapSourceRoad2: Bitmap = context.getDrawable(R.drawable.j1)!!.toBitmap()
	private val bitmapSourceRoad3: Bitmap = context.getDrawable(R.drawable.j2)!!.toBitmap()
	private val bitmapImage: Bitmap = context.getDrawable(R.drawable.back_image)!!.toBitmap()
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
			val h = bitmapSourceRoad2.height.toFloat() * i + 2.7.dp * (2 * i + 1) + 5.5.dp

//			if (i == 5) {
//				bitmapCanvas.drawBitmap(
//					bitmapImage,
//					w,
//					h,
//					paint
//				)
//				break
//			}

			bitmapCanvas.drawBitmap(
				bitmapSourceRoad2,
				w,
				h,
				paint
			)
			bitmapCanvas.drawText(
				testSign.elementAt(0).toString(),
				w + 6.5.dp,
				h + 25.3.dp,
				textPaint
			)
			bitmapCanvas.drawText(
				testSign.elementAt(1).toString(),
				w + 6.5.dp + 16.7.dp,
				h + 25.3.dp,
				textNumPaint
			)
			bitmapCanvas.drawText(
				testSign.elementAt(2).toString(),
				w + 6.5.dp + 31.3.dp,
				h + 25.3.dp,
				textNumPaint
			)
			bitmapCanvas.drawText(
				testSign.elementAt(3).toString(),
				w + 6.5.dp + 45.7.dp,
				h + 25.3.dp,
				textNumPaint
			)
			bitmapCanvas.drawText(
				testSign.elementAt(4).toString(),
				w + 6.5.dp + 62.5.dp,
				h + 25.3.dp,
				textPaint
			)
			bitmapCanvas.drawText(
				testSign.elementAt(5).toString(),
				w + 6.5.dp + 77.dp,
				h + 25.3.dp,
				textPaint
			)
			bitmapCanvas.drawText(
				testSign.elementAt(6).toString(),
				w + 6.5.dp + 97.5.dp,
				h + 20.1.dp,
				textMiniNumPaint
			)
			bitmapCanvas.drawText(
				testSign.elementAt(7).toString(),
				w + 6.5.dp + 109.dp,
				h + 20.1.dp,
				textMiniNumPaint
			)
		}

		canvas?.drawBitmap(bitmap, 0f, 0f, paint)
	}

	fun setAndSaveRoadNumbers(map: HashMap<Int, RoadSignInfo>, fileName: String) {
		val bitmapCanvas = Canvas(bitmap)
		bitmapCanvas.drawColor(Color.WHITE)

		for (i in 0..5) {
			val w = 8.dp
			val h = bitmapSourceRoad2.height.toFloat() * i + 2.7.dp * (2 * i + 1) + 5.5.dp

			map[i + 1]?.let { roadSignInfo ->
				when (roadSignInfo.type) {
					RoadSignType.RUS_2 -> {
						bitmapCanvas.drawBitmap(
							bitmapSourceRoad2,
							w,
							h,
							paint
						)
						if (roadSignInfo.signOrUri.length == 1) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(0).toString(),
								w + 6.5.dp,
								h + 25.3.dp,
								textPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 2) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(1).toString(),
								w + 6.5.dp + 16.7.dp,
								h + 25.3.dp,
								textNumPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 3) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(2).toString(),
								w + 6.5.dp + 31.3.dp,
								h + 25.3.dp,
								textNumPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 4) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(3).toString(),
								w + 6.5.dp + 45.7.dp,
								h + 25.3.dp,
								textNumPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 5) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(4).toString(),
								w + 6.5.dp + 62.5.dp,
								h + 25.3.dp,
								textPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 6) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(5).toString(),
								w + 6.5.dp + 77.dp,
								h + 25.3.dp,
								textPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 7) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(6).toString(),
								w + 6.5.dp + 97.5.dp,
								h + 20.1.dp,
								textMiniNumPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 8) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(7).toString(),
								w + 6.5.dp + 109.dp,
								h + 20.1.dp,
								textMiniNumPaint
							)
						}
					}
					RoadSignType.RUS_3 -> {
						bitmapCanvas.drawBitmap(
							bitmapSourceRoad3,
							w,
							h,
							paint
						)
						if (roadSignInfo.signOrUri.length == 1) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(0).toString(),
								w + 5.dp,
								h + 25.3.dp,
								textPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 2) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(1).toString(),
								w + 5.dp + 14.dp,
								h + 25.3.dp,
								textNumPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 3) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(2).toString(),
								w + 5.dp + 29.dp,
								h + 25.3.dp,
								textNumPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 4) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(3).toString(),
								w + 5.dp + 43.2.dp,
								h + 25.3.dp,
								textNumPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 5) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(4).toString(),
								w + 5.dp + 58.dp,
								h + 25.3.dp,
								textPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 6) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(5).toString(),
								w + 5.dp + 72.5.dp,
								h + 25.3.dp,
								textPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 7) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(6).toString(),
								w + 5.dp + 90.dp,
								h + 20.1.dp,
								textMiniNumPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 8) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(7).toString(),
								w + 5.dp + 101.dp,
								h + 20.1.dp,
								textMiniNumPaint
							)
						}
						if (roadSignInfo.signOrUri.length == 9) {
							bitmapCanvas.drawText(
								roadSignInfo.signOrUri.elementAt(8).toString(),
								w + 5.dp + 112.dp,
								h + 20.1.dp,
								textMiniNumPaint
							)
						}
					}
					RoadSignType.IMAGE -> {
						val bitmapIm = MediaStore.Images.Media.getBitmap(
							context.contentResolver,
							Uri.fromFile(File(roadSignInfo.signOrUri))
						)
						bitmapCanvas.drawBitmap(
							bitmapIm,
							w,
							h,
							paint
						)
					}
				}
			}

		}
		FileUtils.saveImage(bitmap, fileName)
		Toast.makeText(context, "Успешное сохранение файла!", Toast.LENGTH_SHORT).show()
	}

	private val Number.dp
		get() = TypedValue.applyDimension(
			TypedValue.COMPLEX_UNIT_DIP,
			this.toFloat(),
			Resources.getSystem().displayMetrics
		)
}