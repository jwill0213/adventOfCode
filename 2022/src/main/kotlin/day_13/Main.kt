package day_13

import java.io.File

fun part1(input: List<String>) {
    val packetPairList: List<Pair<String, String>> = parseInput(input)
}

fun part2(input: List<String>) {
    print(input)
}

fun parseInput(input: List<String>): List<Pair<String, String>> {
    val packetPairList: MutableList<Pair<String, String>> = mutableListOf()
    for (i in input.indices step 3) {
        packetPairList.add(Pair(input[i],input[i+1]))
    }
    return packetPairList
}

fun main(){
    val inputFile = File("src/main/kotlin/day_13/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}