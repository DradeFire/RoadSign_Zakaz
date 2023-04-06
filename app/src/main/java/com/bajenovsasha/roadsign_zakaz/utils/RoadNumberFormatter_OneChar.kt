package com.bajenovsasha.roadsign_zakaz.utils

object RoadNumberFormatter_OneChar {

	fun validChar(char: Char): Boolean {
		return when (char) {
			'А' -> true
			'В' -> true
			'Е' -> true
			'К' -> true
			'М' -> true
			'О' -> true
			'Т' -> true
			'Х' -> true
			'У' -> true
			'Р' -> true
			'Н' -> true
			'С' -> true
			else -> false
		}
	}

	fun validNumber(char: Char): Boolean {
		return when (char) {
			'0' -> true
			'1' -> true
			'2' -> true
			'3' -> true
			'4' -> true
			'5' -> true
			'6' -> true
			'7' -> true
			'8' -> true
			'9' -> true
			else -> false
		}
	}

	fun format(
		primaryCode: Int
	): Char {
		return when (primaryCode) {
			0 -> '0'
			1 -> '1'
			2 -> '2'
			3 -> '3'
			4 -> '4'
			5 -> '5'
			6 -> '6'
			7 -> '7'
			8 -> '8'
			9 -> '9'
			11 -> 'А'
			12 -> 'В'
			13 -> 'Е'
			14 -> 'К'
			15 -> 'М'
			16 -> 'О'
			17 -> 'Т'
			18 -> 'Х'
			19 -> 'У'
			20 -> 'Р'
			21 -> 'Н'
			22 -> 'С'
			-1 -> ' '
			else -> ' '
		}
	}

}