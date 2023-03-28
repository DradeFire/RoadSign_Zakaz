package com.bajenovsasha.roadsign_zakaz.utils

import android.graphics.Bitmap
import java.io.File
import java.io.FileOutputStream
import java.util.*

object FileUtils {

	fun saveImage(bitmap: Bitmap) {
		val myDir = File("/sdcard/saved_images")
		myDir.mkdirs()
		val fname = "${Calendar.getInstance().time.time}.png"
		val file = File(myDir, fname)
		if (file.exists()) file.delete()
		try {
			val out = FileOutputStream(file)

			bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
			out.flush()
			out.close()
		} catch (e: java.lang.Exception) {
			e.printStackTrace()
		}
	}
}