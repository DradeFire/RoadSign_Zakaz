package com.bajenovsasha.roadsign_zakaz.presentation.model

/**
 * @param type RUS_2/RUS_3
 * @sample sign = 1ABE2345/1ABE23456
 */
data class RoadSignInfo(
	val type: RoadSignType,
	val sign: String
)
