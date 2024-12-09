package day_04

import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun part1(input: List<String>): String {
    var xmasCount = 0
    for (y in input.indices) {
        for (x in input[y].indices) {
            if (input[y][x] == 'X') {
                if (y - 3 >= 0) {
                    // Check if XMAS is spelled up
                    if (input[y - 1][x] == 'M' && input[y - 2][x] == 'A' && input[y - 3][x] == 'S')
                        xmasCount++
                }
                if (y + 3 < input.size) {
                    // Check if XMAS is spelled down
                    if (input[y + 1][x] == 'M' && input[y + 2][x] == 'A' && input[y + 3][x] == 'S')
                        xmasCount++
                }
                if (y - 3 >= 0 && x - 3 >= 0) {
                    // Check if XMAS is spelled up left
                    if (input[y - 1][x - 1] == 'M' && input[y - 2][x - 2] == 'A' && input[y - 3][x - 3] == 'S')
                        xmasCount++
                }
                if (y - 3 >= 0 && x + 3 < input[y].length) {
                    // Check if XMAS is spelled up right
                    if (input[y - 1][x + 1] == 'M' && input[y - 2][x + 2] == 'A' && input[y - 3][x + 3] == 'S')
                        xmasCount++
                }
                if (x - 3 >= 0) {
                    // Check if XMAS is spelled left
                    if (input[y][x - 1] == 'M' && input[y][x - 2] == 'A' && input[y][x - 3] == 'S')
                        xmasCount++
                }
                if (x + 3 < input[y].length) {
                    // Check if XMAS is spelled right
                    if (input[y][x + 1] == 'M' && input[y][x + 2] == 'A' && input[y][x + 3] == 'S')
                        xmasCount++
                }
                if (y + 3 < input.size && x + 3 < input[y].length) {
                    // Check if XMAS is spelled down right
                    if (input[y + 1][x + 1] == 'M' && input[y + 2][x + 2] == 'A' && input[y + 3][x + 3] == 'S')
                        xmasCount++
                }
                if (y + 3 < input.size && x - 3 >= 0) {
                    // Check if XMAS is spelled down left
                    if (input[y + 1][x - 1] == 'M' && input[y + 2][x - 2] == 'A' && input[y + 3][x - 3] == 'S')
                        xmasCount++
                }
            }
        }
    }
    return "Total XMAS count: $xmasCount"
}

fun part2(input: List<String>): String {
    var xmasCount = 0
    for (y in input.indices) {
        for (x in input[y].indices) {
            if (input[y][x] == 'A' && y - 1 >= 0 && x - 1 >= 0 && y + 1 < input.size && x + 1 < input[y].length) {
                if (((input[y - 1][x - 1] == 'M' && input[y + 1][x + 1] == 'S')
                            || (input[y - 1][x - 1] == 'S' && input[y + 1][x + 1] == 'M'))
                    &&
                    ((input[y - 1][x + 1] == 'M' && input[y + 1][x - 1] == 'S')
                            || (input[y - 1][x + 1] == 'S' && input[y + 1][x - 1] == 'M'))
                ) {
                    xmasCount++
                }
            }
        }
    }
    return "Total X-MAS count: $xmasCount"
}

fun main() {
    val inputFile = File("src/main/kotlin/day_04/input.txt")
    print("\n----- Part 1 -----\n")
    val p1Time = measureTime {
        println(part1(inputFile.readLines()))
    }
    println(p1Time.toString(DurationUnit.SECONDS, 4))
    print("\n----- Part 2 -----\n")
    val p2Time = measureTime {
        println(part2(inputFile.readLines()))
    }
    println(p2Time.toString(DurationUnit.SECONDS, 4))

    println("\n----- Total Time -----")
    println((p1Time + p2Time).toString(DurationUnit.SECONDS, 4))
}