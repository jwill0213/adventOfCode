package day_01

import java.io.File
import java.math.BigInteger
import kotlin.math.abs
import kotlin.math.floor
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun part1(input: List<String>): String {
    var finalSum = 0L
    // Input should be a single line
    for (idRange in input[0].split(',')) {
        val (first, second) = idRange.split('-')
        for (num in first.toLong()..second.toLong()) {
            val idString = num.toString();
            if (idString.length % 2 == 0) {
                val mid = idString.length / 2
                val start = idString.take(mid)
                val end = idString.drop(mid)
                if (start == end) {
                    finalSum += num
                }
            }
        }
    }
    return "The final sum is $finalSum"
}

fun part2(input: List<String>): String {
    var finalSum = 0L
    // Input should be a single line
    for (idRange in input[0].split(',')) {
        val (first, second) = idRange.split('-')
        for (num in first.toLong()..second.toLong()) {
            val idString = num.toString();
            for (i in 1..idString.length/2) {
                if (idString.chunked(i).distinct().size <= 1) {
                    finalSum += num
                    break
                }
            }
        }
    }
    return "The final sum is $finalSum"
}

fun main() {
    val inputFile = File("src/day_02/input.txt")
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