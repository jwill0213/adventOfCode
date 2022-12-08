package day_08

import java.io.File

fun part1(input: List<String>) {
    var visibleTrees = 0
    for (y in input.indices) {
        if (y == 0 || y == input.size - 1) {
            // first and last row all trees are visible
            visibleTrees += input[y].length
        } else {
            for (x in 0 until input[y].length) {
                if (x == 0 || x == input[y].length - 1) {
                    // edges always visible
                    visibleTrees++
                } else {
                    val maxUp = input.subList(0, y).stream().map { it[x].digitToInt() }.toList().max()
                    val maxDown = input.subList(y + 1, input.size).map { it[x].digitToInt() }.toList().max()
                    val maxLeft = input[y].subSequence(0, x).map { it.digitToInt() }.toList().max()
                    val maxRight = input[y].subSequence(x + 1, input[y].length).map { it.digitToInt() }.toList().max()

                    if (listOf(maxUp, maxDown, maxLeft, maxRight).any { it < input[y][x].digitToInt() }) {
                        // Tallest tree in at least one direction
                        visibleTrees++
                    }
                }
            }
        }
    }
    println("Visible trees: $visibleTrees")
}

fun part2(input: List<String>) {
    var bestBeautyScore = 0
    for (y in input.indices) {
        for (x in 0 until input[y].length) {
            val currHeight = input[y][x].digitToInt()
            val upTrees = input.subList(0, y).stream().map { it[x].digitToInt() }.toList().reversed()
            val downTrees = input.subList(y + 1, input.size).map { it[x].digitToInt() }.toList()
            val leftTrees = input[y].subSequence(0, x).map { it.digitToInt() }.toList().reversed()
            val rightTrees = input[y].subSequence(x + 1, input[y].length).map { it.digitToInt() }.toList()

            val currentBeauty = findBlocked(currHeight, upTrees) * findBlocked(currHeight, downTrees) * findBlocked(currHeight, leftTrees) * findBlocked(currHeight, rightTrees)
            if (currentBeauty > bestBeautyScore) {
                bestBeautyScore = currentBeauty
            }
        }
    }
    println("Best beauty score: $bestBeautyScore")
}

fun findBlocked(height: Int, otherTrees: List<Int>): Int {
    for (t in otherTrees.indices) {
        if (height <= otherTrees[t]) {
            // If any trees block view, return distance from current tree
            return t + 1
        }
    }
    // If no trees block view you can see them all.
    return otherTrees.size
}

fun main() {
    val inputFile = File("src/main/kotlin/day_08/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}