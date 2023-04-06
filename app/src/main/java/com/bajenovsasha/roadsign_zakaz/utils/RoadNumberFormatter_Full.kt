package com.bajenovsasha.roadsign_zakaz.utils

import com.bajenovsasha.roadsign_zakaz.presentation.model.RoadSignType

object RoadNumberFormatter_Full {

	private fun validInteger(roadSignString: String, currentRoadNumberType: RoadSignType): Boolean {
		return when (currentRoadNumberType) {
			RoadSignType.RUS_2 -> {
				roadSignString.length in 1..3 || roadSignString.length in 6..7
			}
			RoadSignType.RUS_3 -> {
				roadSignString.length in 1..3 || roadSignString.length in 6..8
			}
			else -> false
		}
	}

	private fun validSymbol(roadSignString: String, currentRoadNumberType: RoadSignType): Boolean {
		return when (currentRoadNumberType) {
			RoadSignType.RUS_2 -> {
				!validInteger(roadSignString, currentRoadNumberType) && roadSignString.length < 8
			}
			RoadSignType.RUS_3 -> {
				!validInteger(roadSignString, currentRoadNumberType) && roadSignString.length < 9
			}
			else -> false
		}
	}


	fun format(
		primaryCode: Int,
		roadNumberType: RoadSignType,
		roadSignString: String
	): String {
		return when (primaryCode) {
			0 -> {
				if (validInteger(roadSignString, roadNumberType)) {
					// Ввод 0
					roadSignString + '0'
				} else {
					roadSignString
				}
			}
			1 -> {
				if (validInteger(roadSignString, roadNumberType)) {
					// Ввод 1
					roadSignString + '1'
				} else {
					roadSignString
				}
			}
			2 -> {
				if (validInteger(roadSignString, roadNumberType)) {
					// Ввод 2
					roadSignString + '2'
				} else {
					roadSignString
				}
			}
			3 -> {
				if (validInteger(roadSignString, roadNumberType)) {
					// Ввод 3
					roadSignString + '3'
				} else {
					roadSignString
				}
			}
			4 -> {
				if (validInteger(roadSignString, roadNumberType)) {
					// Ввод 4
					roadSignString + '4'
				} else {
					roadSignString
				}
			}
			5 -> {
				if (validInteger(roadSignString, roadNumberType)) {
					// Ввод 5
					roadSignString + '5'
				} else {
					roadSignString
				}
			}
			6 -> {
				if (validInteger(roadSignString, roadNumberType)) {
					// Ввод 6
					roadSignString + '6'
				} else {
					roadSignString
				}
			}
			7 -> {
				if (validInteger(roadSignString, roadNumberType)) {
					// Ввод 7
					roadSignString + '7'
				} else {
					roadSignString
				}
			}
			8 -> {
				if (validInteger(roadSignString, roadNumberType)) {
					// Ввод 8
					roadSignString + '8'
				} else {
					roadSignString
				}
			}
			9 -> {
				if (validInteger(roadSignString, roadNumberType)) {
					// Ввод 9
					roadSignString + '9'
				} else {
					roadSignString
				}
			}
			11 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод А
					roadSignString + 'А'
				} else {
					roadSignString
				}
			}
			12 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод В
					roadSignString + 'В'
				} else {
					roadSignString
				}
			}
			13 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод Е
					roadSignString + 'Е'
				} else {
					roadSignString
				}
			}
			14 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод К
					roadSignString + 'К'
				} else {
					roadSignString
				}
			}
			15 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод М
					roadSignString + 'М'
				} else {
					roadSignString
				}
			}
			16 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод О
					roadSignString + 'О'
				} else {
					roadSignString
				}
			}
			17 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод Т
					roadSignString + 'Т'
				} else {
					roadSignString
				}
			}
			18 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод Х
					roadSignString + 'Х'
				} else {
					roadSignString
				}
			}
			19 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод У
					roadSignString + 'У'
				} else {
					roadSignString
				}
			}
			20 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод Р
					roadSignString + 'Р'
				} else {
					roadSignString
				}
			}
			21 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод H
					roadSignString + 'Н'
				} else {
					roadSignString
				}
			}
			22 -> {
				if (validSymbol(roadSignString, roadNumberType)) {
					// Ввод С
					roadSignString + 'С'
				} else {
					roadSignString
				}
			}
			-1 -> {
				if (roadSignString.isNotEmpty()) {
					roadSignString.substring(0, roadSignString.length - 1)
				} else {
					roadSignString
				}
			}
			else -> {
				""
			}
		}
	}

}