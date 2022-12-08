package day_02

import java.io.File

fun part1(input: List<String>) {
    var totalWrappingPaper = 0
    for (present in input) {
        val (length, width, height) = present.split("x").stream().map { it.toInt() }.toList()
        val surfaceArea = 2*length*width + 2*width*height + 2*height*length
        val extraPaper = minOf(length*width, width*height, height*length)
        totalWrappingPaper += surfaceArea + extraPaper
    }
    println("Total wrapping paper needed: $totalWrappingPaper")
}

fun part2(input: List<String>) {
    var totalRibbonLength = 0
    for (present in input) {
        val (length, width, height) = present.split("x").stream().map { it.toInt() }.toList()
        // Get the two shortest
        val shortestTwo = listOf(length,width,height).sorted().subList(0,2)
        val ribbonLength = 2*shortestTwo[0] + 2*shortestTwo[1]
        val extraLength = length * width * height
        totalRibbonLength += ribbonLength + extraLength
    }
    println("Total ribbon length needed: $totalRibbonLength")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_02/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}