package day06

import println
import readInput

fun main() {

    fun part1(input: List<String>): Long {
        val numberRows = input.dropLast(1).map {
            it.trim().split(Regex("\\s+")).map { num -> num.toInt() }
        }
        val operations = input.last().trim().split(Regex("\\s+"))

        return numberRows.first().indices.sumOf { i ->
            var result = numberRows.first()[i].toLong()
            numberRows.drop(1).forEach { numbers ->
                when (operations[i]) {
                    "+" -> result += numbers[i]
                    "*" -> result *= numbers[i]
                }
            }
            result
        }
    }

    fun part2(input: List<String>): Long {
        val numRows = input.size
        val numCols = input.maxOf { it.length }
        val grid = input.map { it.padEnd(numCols, ' ') }

        val isSeparator = (0 until numCols).map { col ->
            (0 until numRows - 1).all { row -> grid[row][col] == ' ' }
        }

        val problems = buildList {
            var start = 0
            while (start < numCols) {
                while (start < numCols && isSeparator[start]) start++
                if (start >= numCols) break

                var end = start
                while (end < numCols && !isSeparator[end]) end++

                add(start until end)
                start = end
            }
        }

        return problems.sumOf { problemRange ->
            val operator = grid[numRows - 1][problemRange.first]
            val numbers = problemRange.reversed().mapNotNull { col ->
                val digits = (0 until numRows - 1)
                    .mapNotNull { row -> grid[row][col].takeIf { it != ' ' } }
                    .joinToString("")
                digits.toLongOrNull()
            }

            numbers.reduceOrNull { acc, num ->
                when (operator) {
                    '+' -> acc + num
                    '*' -> acc * num
                    else -> acc
                }
            } ?: 0L
        }
    }

    // Test with example
    val testInput = readInput("day06/Day06_test")
    check(part1(testInput) == 4277556L)
    check(part2(testInput) == 3263827L)

    // Read the input from the `src/Day06.txt` file.
    val input = readInput("day06/Day06")
    part1(input).println()
    part2(input).println()
}
