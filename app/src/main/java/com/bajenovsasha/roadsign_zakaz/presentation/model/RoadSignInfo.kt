package com.bajenovsasha.roadsign_zakaz.presentation.model

import android.graphics.drawable.Drawable

/**
 * @param type RUS_2/RUS_3/IMAGE
 * @param roadNumber = 1ABE2345/1ABE23456
 */
data class RoadSignInfo(
	val type: RoadSignType,
	val roadNumber: String? = null,
	val imageDrawable: Drawable? = null
)
