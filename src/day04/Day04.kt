package day04

import println
import readInput

fun main() {
    val directions = listOf(
        -1 to -1, -1 to 0, -1 to 1,  // top-left, top, top-right
        0 to -1,           0 to 1,    // left, right
        1 to -1,  1 to 0,  1 to 1     // bottom-left, bottom, bottom-right
    )

    fun countAdjacentRolls(grid: Array<CharArray>, row: Int, col: Int): Int {
        var count = 0
        for ((dr, dc) in directions) {
            val newRow = row + dr
            val newCol = col + dc
            if (newRow in grid.indices && newCol in grid[newRow].indices && grid[newRow][newCol] == '@') {
                count++
            }
        }

        return count
    }

    fun part1(input: List<String>): Long {
        val grid = input.map { it.toCharArray() }.toTypedArray()
        var accessibleCount = 0L

        // Iterate through each cell in the grid
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (grid[row][col] == '@') {
                    // Count adjacent rolls
                    val adjacentCount = countAdjacentRolls(grid, row, col)
                    if (adjacentCount < 4) {
                        accessibleCount++
                    }
                }
            }
        }
        return accessibleCount
    }

    fun part2(input: List<String>): Long {
        // Create a mutable grid
        val grid = input.map { it.toCharArray() }.toTypedArray()
        var totalRemoved = 0L

        while (true) {
            // Find all accessible rolls in the current state
            val toRemove = mutableListOf<Pair<Int, Int>>()

            for (row in grid.indices) {
                for (col in grid[row].indices) {
                    if (grid[row][col] == '@') {
                        val adjacentCount = countAdjacentRolls(grid, row, col)
                        if (adjacentCount < 4) {
                            toRemove.add(row to col)
                        }
                    }
                }
            }

            // If no rolls can be removed, we're done
            if (toRemove.isEmpty()) {
                break
            }

            // Remove all accessible rolls
            for ((row, col) in toRemove) {
                grid[row][col] = '.'
            }

            totalRemoved += toRemove.size
        }

        return totalRemoved
    }

    // Test with example
    val testInput = readInput("day04/Day04_test")
    check(part1(testInput) == 13L)
    check(part2(testInput) == 43L)

    // Read the input from the `src/Day05.txt` file.
    val input = readInput("day04/Day04")
    part1(input).println()
    part2(input).println()
}
