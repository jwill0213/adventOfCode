package day_02

import java.io.File
import kotlin.math.abs
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun part1(input: List<String>): String {
    var safeReports = 0

    for (report in input) {
        val reported = report.split(" ")
        if (isSafe(reported)) {
            safeReports += 1
        }
    }

    return "Safe Reports: $safeReports"
}

fun part2(input: List<String>): String {
    var safeReports = 0

    for (report in input) {
        val reported = report.split(" ")
        if (isSafe(reported)) {
            safeReports += 1
        } else {
            for (toRemove in reported.indices) {
                if (isSafe(reported.filterIndexed { index, _ -> index != toRemove })) {
                    safeReports += 1
                    break
                }
            }
        }
    }

    return "Safe Reports: $safeReports"
}

fun isSafe(reported: List<String>): Boolean {
    var safe = true
    var first = true
    var isIncreasing = false

    for (window in reported.windowed(2)) {
        val report1 = window[0].toInt()
        val report2 = window[1].toInt()
        if (first) {
            first = false
            isIncreasing = report1 < report2
        }
        if ((isIncreasing && report1 >= report2) || (!isIncreasing && report1 <= report2) || (abs(report1 - report2) > 3)) {
            safe = false
            break
        }
    }
    return safe
}

fun main() {
    val inputFile = File("src/main/kotlin/day_02/input.txt")
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