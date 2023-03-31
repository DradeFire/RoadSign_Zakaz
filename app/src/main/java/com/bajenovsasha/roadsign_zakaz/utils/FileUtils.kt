package com.bajenovsasha.roadsign_zakaz.utils

import android.graphics.Bitmap
import android.os.Environment
import com.bajenovsasha.roadsign_zakaz.common.Consts.DIRECTORY_IMAGE
import java.io.File
import java.io.FileOutputStream
import java.util.*

object FileUtils {

	fun saveImage(bitmap: Bitmap, fileName: String) {
		val downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
		if (downloadDir.canWrite()) {
			val fname = "$fileName.png"
			val file = File(downloadDir, fname)
			if (file.exists()) file.delete()
			try {
				val out = FileOutputStream(file)

				bitmap.compress(Bitmap.CompressFormat.PNG, 100, out)
				out.flush()
				out.close()
			} catch (e: java.lang.Exception) {
				e.printStackTrace()
			}
		} else {
			val myDir = File(DIRECTORY_IMAGE)
			myDir.mkdirs()
			val fname = "$fileName.png"
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
}