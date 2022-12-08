package day_04

import java.io.File

fun part1(input: List<String>) {
    var totalOverlapCount = 0
    for (assignment in input) {
        val a1 = assignment.split(',')[0].split('-')
        val a1List = (a1[0].toInt()..a1[1].toInt()).toList()
        val a2 = assignment.split(',')[1].split('-')
        val a2List = (a2[0].toInt()..a2[1].toInt()).toList()

        val overlap = a1List.intersect(a2List.toSet())

        if (overlap.size == a1List.size || overlap.size == a2List.size) {
            totalOverlapCount++
        }
    }
    print("$totalOverlapCount assignments fully overlap.")
}

fun part2(input: List<String>) {
    var totalOverlapCount = 0
    for (assignment in input) {
        val a1 = assignment.split(',')[0].split('-')
        val a1List = (a1[0].toInt()..a1[1].toInt()).toList()
        val a2 = assignment.split(',')[1].split('-')
        val a2List = (a2[0].toInt()..a2[1].toInt()).toList()

        val overlap = a1List.intersect(a2List.toSet())

        if (overlap.size > 0) {
            totalOverlapCount++
        }
    }
    print("$totalOverlapCount assignments overlap.")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_04/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}