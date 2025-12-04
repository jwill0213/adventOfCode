package day_04

import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun part1(input: List<String>): String {
    var accessibleRolls = 0
    for (row in 0..<input.size) {
        for (col in 0..<input[row].length) {
            if (input[row][col] == '@') {
                if (findAdjacent(input, row, col) < 4) {
                    accessibleRolls += 1
                }
            }
        }
    }
    Pair(1,2) + Pair(1,3)
    return "Total accessible rolls: $accessibleRolls"
}

fun part2(input: MutableList<String>): String {
    var accessibleRolls = 0
    var newAccessibleRolls = 0
    do {
        accessibleRolls += newAccessibleRolls
        newAccessibleRolls = 0
        val rollsToRemove: MutableList<Pair<Int,Int>> = mutableListOf()
        for (row in 0..<input.size) {
            for (col in 0..<input[row].length) {
                if (input[row][col] == '@') {
                    if (findAdjacent(input, row, col) < 4) {
                        newAccessibleRolls += 1
                        rollsToRemove.add(Pair(row,col))
                    }
                }
            }
        }
        for (roll in rollsToRemove) {
            val rowArray = input[roll.first].toCharArray()
            rowArray[roll.second] = '.'
            input[roll.first] = rowArray.joinToString("")
        }
    } while (newAccessibleRolls > 0)
    return "Total accessible rolls: $accessibleRolls"
}

fun findAdjacent(input: List<String>, row: Int, col: Int): Int {
    var adjacentCount = 0
    if (row > 0 && col > 0 && input[row - 1][col - 1] == '@') {
        adjacentCount += 1
    }
    if (row > 0 && input[row - 1][col] == '@') {
        adjacentCount += 1
    }
    if (row > 0 && col < input[row].length - 1 && input[row - 1][col + 1] == '@') {
        adjacentCount += 1
    }
    if (col > 0 && input[row][col - 1] == '@') {
        adjacentCount += 1
    }
    if (col < input[row].length - 1 && input[row][col + 1] == '@') {
        adjacentCount += 1
    }
    if (row < input.size - 1 && col > 0 && input[row + 1][col - 1] == '@') {
        adjacentCount += 1
    }
    if (row < input.size - 1 && input[row + 1][col] == '@') {
        adjacentCount += 1
    }
    if (row < input.size - 1 && col < input[row].length - 1 && input[row + 1][col + 1] == '@') {
        adjacentCount += 1
    }
    return adjacentCount
}

fun main() {
    val inputFile = File("src/day_04/input.txt")
    print("\n----- Part 1 -----\n")
    val p1Time = measureTime {
        println(part1(inputFile.readLines()))
    }
    println(p1Time.toString(DurationUnit.SECONDS, 4))
    print("\n----- Part 2 -----\n")
    val p2Time = measureTime {
        println(part2(inputFile.readLines().toMutableList()))
    }
    println(p2Time.toString(DurationUnit.SECONDS, 4))

    println("\n----- Total Time -----")
    println((p1Time + p2Time).toString(DurationUnit.SECONDS, 4))
}