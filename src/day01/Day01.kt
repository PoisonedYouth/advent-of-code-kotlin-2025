package day01

import println
import readInput

fun main() {
    fun Int.increment(number: Int): Int{
        return (this + number) % 100
    }

    fun Int.decrement(number: Int): Int{
        return ((this - number) % 100 + 100) % 100
    }

    fun part1(input: List<String>): Int {
        var counter = 0
        input.fold(50) { acc, s ->
            val direction = s.first()
            val number = s.drop(1).toInt()
            val result = when(direction){
                'R' -> {
                    acc.increment(number)
                }
                'L' -> {
                    acc.decrement(number)
                }
                else -> error("Invalid direction: $direction")
            }
            if (result == 0) counter++
            result
        }
        return counter
    }

    fun part2(input: List<String>): Int {
        var counter = 0
        input.fold(50) { acc, s ->
            val direction = s.first()
            val number = s.drop(1).toInt()
            val result = when(direction){
                'R' -> {
                    counter += (acc + number) / 100
                    acc.increment(number)
                }
                'L' -> {
                    if (acc < number) {
                        counter += 1 + (number - acc - 1) / 100
                    }
                    acc.decrement(number)
                }
                else -> error("Invalid direction: $direction")
            }
            result
        }
        return counter
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("day01/Day01")
    part1(input).println()
    part2(input).println()
}
