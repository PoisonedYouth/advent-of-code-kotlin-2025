package day01

import println
import readInput

fun main() {
    fun Int.dialRight(number: Int): Int{
        return (this + number).mod(100)
    }

    fun Int.dialLeft(number: Int): Int{
        return ((this - number).mod(100) + 100).mod(100)
    }

    fun part1(input: List<String>): Int {
        var clicks = 0
        input.fold(50) { acc, s ->
            val direction = s.first()
            val dials = s.drop(1).toInt()
            val result = when(direction){
                'R' -> {
                    acc.dialRight(dials)
                }
                'L' -> {
                    acc.dialLeft(dials)
                }
                else -> error("Invalid direction: $direction")
            }
            if (result == 0) clicks++
            result
        }
        return clicks
    }

    fun part2(input: List<String>): Int {
        var clicks = 0
        input.fold(50) { acc, s ->
            val direction = s.first()
            val number = s.drop(1).toInt()
            val result = when(direction){
                'R' -> {
                    clicks += (acc + number) / 100
                    acc.dialRight(number)
                }
                'L' -> {
                    clicks += (number - acc + 99) / 100
                    acc.dialLeft(number)
                }
                else -> error("Invalid direction: $direction")
            }
            result
        }
        return clicks
    }

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("day01/Day01")
    part1(input).println()
    part2(input).println()
}
