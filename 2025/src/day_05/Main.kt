package day_05

import java.io.File
import kotlin.math.max
import kotlin.time.DurationUnit
import kotlin.time.measureTime

fun part1(input: List<String>): String {
    var freshIngredients = 0
    val ingredientRanges: MutableList<Pair<Long, Long>> = mutableListOf()
    var gettingRanges = true
    input.forEach { text ->
        if (gettingRanges) {
            if (text == "") {
                gettingRanges = false
            } else {
                val (first, second) = text.split("-")
                ingredientRanges.add(Pair(first.toLong(), second.toLong()))
            }
        } else {
            if (ingredientRanges.any { it.inRange(text.toLong()) }) {
                freshIngredients += 1
            }
        }
    }
    return "Total Fresh Ingredients $freshIngredients"
}

fun part2(input: List<String>): String {
    var freshIngredients = 0L
    val ingredientRanges: MutableList<Pair<Long, Long>> = mutableListOf()
    for (range in input) {
        if (range == "") {
            break
        } else {
            val (first, second) = range.split("-").map { it.toLong() }
            ingredientRanges.add(Pair(first, second))
        }
    }
    ingredientRanges.sortBy { it.first }
    var currentRange: Pair<Long, Long>? = null
    for (r in ingredientRanges) {
        if (currentRange == null) {
            currentRange = r
            continue
        }
        if (currentRange.inRange(r.first)) {
            currentRange = Pair(currentRange.first, max(r.second, currentRange.second))
        } else {
            freshIngredients += currentRange.numsInRange()
            currentRange = r
        }
    }
    if (currentRange != null) {
        freshIngredients += currentRange.numsInRange()
    }
    return "Total Fresh Ingredients $freshIngredients"
}

private fun Pair<Long, Long>.inRange(ingredient: Long): Boolean {
    return ingredient in first..second
}

private fun Pair<Long, Long>.numsInRange(): Long {
    return second - first + 1
}

fun main() {
    val inputFile = File("src/day_05/input.txt")
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