package day03

import println
import readInput

fun main() {

    fun part1(input: List<String>): Long {
        return input.sumOf { line ->
            var maxJoltage = 0
            for (i in line.indices) {
                for (j in i + 1 until line.length) {
                    val twoDigit = "${line[i]}${line[j]}".toInt()
                    maxJoltage = maxOf(maxJoltage, twoDigit)
                }
            }
            maxJoltage.toLong()
        }
    }


    fun part2(input: List<String>): Long {
        return input.sumOf { line ->
            val n = line.length
            val k = 12 // number of batteries to select
            val result = StringBuilder()
            var lastPos = -1

            for (i in 0 until k) {
                // For position i, we can search from lastPos+1 to n-k+i
                // because we need k-i-1 more digits after this one
                val searchEnd = n - k + i
                var maxDigit = '0'
                var maxPos = lastPos + 1

                for (j in lastPos + 1..searchEnd) {
                    if (line[j] > maxDigit) {
                        maxDigit = line[j]
                        maxPos = j
                    }
                }

                result.append(maxDigit)
                lastPos = maxPos
            }

            result.toString().toLong()
        }
    }

    // Test with example
    val testInput = readInput("day03/Day03_test")
    check(part1(testInput) == 357L)
    check(part2(testInput) == 3121910778619L)

    // Read the input from the `src/Day03.txt` file.
    val input = readInput("day03/Day03")
    part1(input).println()
    part2(input).println()
}
