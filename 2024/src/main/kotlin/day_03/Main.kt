package day_03

import java.io.File
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun part1(input: List<String>): String {
    val matcher = "mul\\(([0-9]{1,3}),([0-9]{1,3})\\)".toRegex()

    val answer = matcher
        .findAll(input.joinToString())
        .map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
        .sum()

    return "Multiplications sum: $answer"
}

fun part2(input: List<String>): String {
    val matcher = "(mul\\(([0-9]{1,3}),([0-9]{1,3})\\)|do\\(\\)|don't\\(\\))".toRegex()

    var shouldMult = true
    val multPairs = mutableListOf<Pair<Int, Int>>()
    matcher
        .findAll(input.joinToString())
        .forEach {
            if (it.value == "don't()") {
                shouldMult = false
            } else if (it.value == "do()") {
                shouldMult = true
            }
            if (shouldMult && it.value.startsWith("mul")) {
                multPairs.add(Pair(it.groupValues[2].toInt(), it.groupValues[3].toInt()))
            }
        }
    return "Multiplications sum: ${multPairs.sumOf { it.first * it.second }}"
}

fun main() {
    val inputFile = File("src/main/kotlin/day_03/input.txt")
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