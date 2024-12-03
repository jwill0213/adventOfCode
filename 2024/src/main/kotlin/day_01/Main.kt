package day_01

import java.io.File
import kotlin.math.abs
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun part1(input: List<String>) {
    val firstNums = mutableListOf<Int>()
    val secondNums = mutableListOf<Int>()

    input.forEach {
        val nums = it.replace(Regex("\\s+"), " ").trim().split(" ")
        firstNums.add(nums[0].toInt())
        secondNums.add(nums[1].toInt())
    }
    firstNums.sort()
    secondNums.sort()

    var ans = 0
    for (i in 0 until firstNums.size) {
        ans += abs(firstNums[i] - secondNums[i])
    }
    println("Total Distance: $ans")
}

fun part2(input: List<String>) {
    val firstNums = mutableListOf<Int>()
    val secondNums = hashMapOf<Int, Int>()

    input.forEach {
        val nums = it.replace(Regex("\\s+"), " ").trim().split(" ")
        firstNums.add(nums[0].toInt())
        secondNums[nums[1].toInt()] = secondNums.getOrDefault(nums[1].toInt(), 0) + 1
    }
    firstNums.sort()

    var ans = 0
    for (i in 0 until firstNums.size) {
        ans += abs(firstNums[i] * secondNums.getOrDefault(firstNums[i], 0))
    }
    println("Total Distance: $ans")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_01/input.txt")
    print("\n----- Part 1 -----\n")
    val p1Time = measureTime {
        println(day_02.part1(inputFile.readLines()))
    }
    println(p1Time.toString(DurationUnit.SECONDS, 4))
    print("\n----- Part 2 -----\n")
    val p2Time = measureTime {
        println(day_02.part2(inputFile.readLines()))
    }
    println(p2Time.toString(DurationUnit.SECONDS, 4))

    println("\n----- Total Time -----")
    println((p1Time + p2Time).toString(DurationUnit.SECONDS, 4))
}