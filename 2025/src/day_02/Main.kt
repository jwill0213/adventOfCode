package day_02

import java.io.File
import kotlin.math.abs
import kotlin.math.floor
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun part1(input: List<String>): String {
    var currentPosition = 50
    var actualCombo = 0
    for (rotation in input) {
        val direction = rotation[0]
        val amount = rotation.substring(1).toInt()
        if (direction == 'L') {
            currentPosition -= amount
        } else {
            currentPosition += amount
        }
        while (currentPosition !in 0..99) {
            if (currentPosition < 0) {
                currentPosition += 100
            } else {
                currentPosition -= 100
            }
        }
        if (currentPosition == 0) {
            actualCombo += 1
        }
    }
    return "The password is $actualCombo"
}

fun part2(input: List<String>): String {
    var currentPosition = 50
    var actualCombo = 0
    for (rotation in input) {
        val direction = rotation[0]
        val amount = rotation.substring(1).toInt()
        val previous = currentPosition
        if (direction == 'L') {
            currentPosition -= amount
            actualCombo += abs((-previous).floorDiv(100) - (-currentPosition).floorDiv(100))
        } else {
            currentPosition += amount
            actualCombo += abs(previous.floorDiv(100) - currentPosition.floorDiv(100))
        }

        currentPosition %= 100
    }
    return "The password is $actualCombo"
}

fun main() {
    val inputFile = File("src/day_01/input.txt")
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