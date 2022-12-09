package day_05

import java.io.File

fun part1(input: List<String>) {
    val vowelRegex = Regex("(.*[aeuio].*){3,}")
    val doubleLetterRegex = Regex("(\\D)\\1")
    val naughtyRegex = Regex("ab|cd|pq|xy")

    var niceCount = 0
    for (st in input) {
        if (!naughtyRegex.containsMatchIn(st) && vowelRegex.matches(st) && doubleLetterRegex.containsMatchIn(st)) {
            //nice
            niceCount++
        }
    }
    println("Nice string count $niceCount")
}

fun part2(input: List<String>) {
    val doubleLetterRegex = Regex("(\\D).\\1")
    val repeatPatternRegex = Regex("(\\D\\D).*\\1")

    var niceCount = 0
    for (st in input) {
        if (doubleLetterRegex.containsMatchIn(st) && repeatPatternRegex.containsMatchIn(st)) {
            //nice
            niceCount++
        }
    }
    println("Nice string count $niceCount")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_05/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}