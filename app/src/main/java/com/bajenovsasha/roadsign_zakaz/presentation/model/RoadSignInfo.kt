package com.bajenovsasha.roadsign_zakaz.presentation.model

/**
 * @param id - 1..6
 * @param type RUS_2/RUS_3
 * @sample sign = 1ABE2345
 */
data class RoadSignInfo(
	val id: Int,
	val type: RoadSignType,
	val sign: String
)
