package com.bajenovsasha.roadsign_zakaz.utils

object RoadNumberUiFormatter {

	fun format(roadNum: String): String {
		return when(roadNum.length) {
			2 -> roadNum[0] + " " + roadNum[1]
			3 -> roadNum[0] + " " + roadNum[1] + roadNum[2]
			4 -> roadNum[0] + " " + roadNum[1] + roadNum[2] + roadNum[3]
			5 -> roadNum[0] + " " + roadNum[1] + roadNum[2] + roadNum[3] + ' ' + roadNum[4]
			6 -> roadNum[0] + " " + roadNum[1] + roadNum[2] + roadNum[3] + ' ' + roadNum[4] + roadNum[5]
			7 -> roadNum[0] + " " + roadNum[1] + roadNum[2] + roadNum[3] + ' ' + roadNum[4] + roadNum[5] + ' ' + roadNum[6]
			8 -> roadNum[0] + " " + roadNum[1] + roadNum[2] + roadNum[3] + ' ' + roadNum[4] + roadNum[5] + ' ' + roadNum[6] + roadNum[7]
			9 -> roadNum[0] + " " + roadNum[1] + roadNum[2] + roadNum[3] + ' ' + roadNum[4] + roadNum[5] + ' ' + roadNum[6] + roadNum[7] + roadNum[8]
			else -> roadNum
		}
	}

}