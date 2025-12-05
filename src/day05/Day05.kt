package day05

import println
import readInput

fun main() {

    fun part1(input: List<String>): Long {
        val splitIndex = input.indexOf("")
        val ranges = input.subList(0, splitIndex).map { line ->
            val (start, end) = line.split("-").map { it.toLong() }
            start..end
        }
        val ids = input.subList(splitIndex + 1, input.size).map { it.toLong() }

        return ids.count { id ->
            ranges.any { range -> id in range }
        }.toLong()
    }

    fun part2(input: List<String>): Long {
        val splitIndex = input.indexOf("")
        val ranges = input.subList(0, splitIndex).map { line ->
            val (start, end) = line.split("-").map { it.toLong() }
            start to end
        }.sortedBy { it.first }

        // Merge overlapping ranges
        val mergedRanges = mutableListOf<Pair<Long, Long>>()
        var currentRange = ranges.first()

        for (i in 1 until ranges.size) {
            val nextRange = ranges[i]
            if (nextRange.first <= currentRange.second + 1) {
                // Ranges overlap or are adjacent, merge them
                currentRange = currentRange.first to maxOf(currentRange.second, nextRange.second)
            } else {
                // No overlap, save current and start new
                mergedRanges.add(currentRange)
                currentRange = nextRange
            }
        }
        mergedRanges.add(currentRange)

        // Count total IDs in all merged ranges
        return mergedRanges.sumOf { (start, end) -> end - start + 1 }
    }

    // Test with example
    val testInput = readInput("day05/Day05_test")
    check(part1(testInput) == 3L)
    check(part2(testInput) == 14L)

    // Read the input from the `src/Day05.txt` file.
    val input = readInput("day05/Day05")
    part1(input).println()
    part2(input).println()
}
