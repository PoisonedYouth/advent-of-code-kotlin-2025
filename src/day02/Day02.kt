package day02

import println
import readInput

fun main() {
    fun isInvalidId(n: Long): Boolean {
        val str = n.toString()
        if (str.length % 2 != 0) return false
        val mid = str.length / 2
        val firstHalf = str.take(mid)
        val secondHalf = str.substring(mid)
        return firstHalf == secondHalf
    }

    fun countInvalidIdsInRange(start: Long, end: Long): List<Long> {
        val invalidIds = mutableListOf<Long>()
        for (n in start..end) {
            if (isInvalidId(n)) {
                invalidIds.add(n)
            }
        }
        return invalidIds
    }

    fun createRanges(input: List<String>): List<Pair<Long, Long>> = input.first().split(",").map { range ->
        val (start, end) = range.split("-").map { it.toLong() }
        start to end
    }

    fun part1(input: List<String>): Long {
        val ranges = createRanges(input)

        return ranges.sumOf { (start, end) ->
            val invalidIds = countInvalidIdsInRange(start, end)
            invalidIds.sum()
        }
    }

    fun isInvalidIdPart2(n: Long): Boolean {
        val str = n.toString()
        val len = str.length

        // Try all possible pattern lengths from 1 to len/2
        for (patternLen in 1..len/2) {
            // Only check if the length is divisible by pattern length
            if (len % patternLen == 0) {
                val pattern = str.take(patternLen)
                val repeated = pattern.repeat(len / patternLen)
                if (repeated == str) {
                    return true
                }
            }
        }
        return false
    }

    fun countInvalidIdsInRangePart2(start: Long, end: Long): List<Long> {
        val invalidIds = mutableListOf<Long>()
        for (n in start..end) {
            if (isInvalidIdPart2(n)) {
                invalidIds.add(n)
            }
        }
        return invalidIds
    }

    fun part2(input: List<String>): Long {
        val ranges = createRanges(input)

        return ranges.sumOf { (start, end) ->
            val invalidIds = countInvalidIdsInRangePart2(start, end)
            invalidIds.sum()
        }
    }

    // Read the input from the `src/Day02.txt` file.
    val input = readInput("day02/Day02")
    part1(input).println()
    part2(input).println()
}
