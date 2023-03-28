package com.bajenovsasha.roadsign_zakaz.common

import android.util.Log

object MyLogger {

	private const val TAG = "MY_TAG"

	fun log(message: String, error: Throwable? = null) {
		Log.d(TAG, message, error)
	}

}