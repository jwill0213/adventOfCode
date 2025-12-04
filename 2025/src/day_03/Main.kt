package day_03

import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun part1(input: List<String>): String {
    var outputJoltage = 0
    for (bank in input) {
        val batList = bank.map { it - '0' }
        // List not including last value
        val firstDigitList = batList.subList(0, batList.size - 1)
        val firstDigitIndex = firstDigitList.indexOfFirst { it == firstDigitList.max() }
        val secondDigitList = batList.drop(firstDigitIndex + 1)
        val secondDigitIndex = secondDigitList.indexOfFirst { it == secondDigitList.max() }
        outputJoltage += "${firstDigitList[firstDigitIndex]}${secondDigitList[secondDigitIndex]}".toInt()
    }
    return "Total output Joltage $outputJoltage"
}

fun part2(input: List<String>): String {
    var outputJoltage = 0L
    for (bank in input) {
        val batList = bank.map { it - '0' }
        var turnedOnBatteries = ""
        var lastDigitIndex = 0
        for (drop in 11 downTo 0) {
            val digitList = batList.subList(lastDigitIndex, batList.size - drop)
            val digitIndex = digitList.indexOfFirst { it == digitList.max() }
            lastDigitIndex += digitIndex + 1
            turnedOnBatteries += digitList[digitIndex]
        }
        outputJoltage += turnedOnBatteries.toLong()
    }
    return "Total output Joltage $outputJoltage"
}

fun main() {
    val inputFile = File("src/day_03/input.txt")
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