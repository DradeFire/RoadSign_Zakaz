package com.bajenovsasha.roadsign_zakaz.presentation.model

/**
 * @param type RUS_2/RUS_3/IMAGE
 * @param signOrUri = 1ABE2345/1ABE23456/storage:...
 */
data class RoadSignInfo(
	val type: RoadSignType,
	val signOrUri: String
)
