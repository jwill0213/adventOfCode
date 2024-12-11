package day_05

import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun part1(input: List<String>): String {
    val allRules = mutableListOf<Pair<String, String>>()
    var answer = 0
    for (line in input) {
        if (line.contains("|")) {
            val rules = line.split("|")
            allRules.add(Pair(rules[0], rules[1]))
            continue
        }
        if (line.isBlank()) {
            continue
        }
        val pageUpdates = line.split(",")
        var validPages = true
        for (rule in allRules) {
            val r1 = pageUpdates.indexOf(rule.first)
            val r2 = pageUpdates.indexOf(rule.second)
            if (r1 >= 0 && r2 >= 0 && r1 > r2) {
                validPages = false
                break
            }
        }
        if (validPages) {
            answer += pageUpdates[pageUpdates.size / 2].toInt()
        }
    }
    return "Correct Updates: $answer"
}

fun part2(input: List<String>): String {
    val allRules = mutableListOf<Pair<String, String>>()
    var answer = 0
    for (line in input) {
        if (line.contains("|")) {
            val rules = line.split("|")
            allRules.add(Pair(rules[0], rules[1]))
            continue
        }
        if (line.isBlank()) {
            continue
        }
        val pageUpdates = line.split(",").toMutableList()
        var validPages = true
        var correctionMade = false
        while (validPages) {
            validPages = false
            for (rule in allRules) {
                val r1 = pageUpdates.indexOf(rule.first)
                val r2 = pageUpdates.indexOf(rule.second)
                if (r1 >= 0 && r2 >= 0 && r1 > r2) {
                    pageUpdates.removeAt(r1)
                    pageUpdates.add(r2, rule.first)
                    validPages = true
                    correctionMade = true
                }
            }
        }
        if (correctionMade) {
            answer += pageUpdates[pageUpdates.size / 2].toInt()
        }
    }
    return "Correct Updates: $answer"
}

fun main() {
    val inputFile = File("src/main/kotlin/day_05/input.txt")
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