package day_06

import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

enum class Direction {
    UP, DOWN, LEFT, RIGHT
}

fun part1(input: List<String>): String {
    var guardPos = Pair(0, 0)
    var currDir = Direction.UP
    for (i in input.indices) {
        val guardIndex = input[i].indexOf("^")
        if (guardIndex >= 0) {
            // Guard X,Y coord
            guardPos = Pair(guardIndex, i)
            break
        }
    }

    val maxX = input[0].length
    val maxY = input.size
    val visited = mutableSetOf(guardPos)
    while (guardPos.first in 0 until maxX && guardPos.second in 0 until maxY) {
        val nextPos = when (currDir) {
            Direction.UP -> Pair(guardPos.first, guardPos.second - 1)
            Direction.DOWN -> Pair(guardPos.first, guardPos.second + 1)
            Direction.LEFT -> Pair(guardPos.first - 1, guardPos.second)
            Direction.RIGHT -> Pair(guardPos.first + 1, guardPos.second)
        }
        if (nextPos.first in 0 until maxX && nextPos.second in 0 until maxY) {
            if (input[nextPos.second][nextPos.first] != '#') {
                guardPos = nextPos
                visited.add(guardPos)
            } else {
                currDir = when (currDir) {
                    Direction.UP -> Direction.RIGHT
                    Direction.RIGHT -> Direction.DOWN
                    Direction.DOWN -> Direction.LEFT
                    Direction.LEFT -> Direction.UP
                }
            }
        } else {
            guardPos = nextPos
        }
    }

    return "Distinct Positions ${visited.size}"
}

fun part2(input: List<String>): String {
    return "TBD"
}

fun main() {
    val inputFile = File("src/main/kotlin/day_06/input.txt")
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