package day_03

import java.io.File

fun part1(input: List<String>) {
    // Hash map with x,y of 0,0 being the starting point
    val visitedHouseMap: HashMap<String, Int> = hashMapOf("(0,0)" to 1)
    var (x,y) = listOf(0,0)
    // Input is one line
    for (d in input[0]) {
        if (d == '^') {
            y++
        } else if (d == '>') {
            x++
        } else if (d == 'v') {
            y--
        } else if (d == '<') {
            x--
        } else {
            continue
        }
        // If exists, increment value, else new value will be one
        visitedHouseMap["($x,$y)"] = visitedHouseMap.getOrDefault("($x,$y)", 0) + 1
    }

    println("Houses visited by Santa: ${visitedHouseMap.size}")
}

fun part2(input: List<String>) {
    // Hash map with x,y of 0,0 being the starting point
    val visitedHouseMap: HashMap<String, Int> = hashMapOf("(0,0)" to 1)
    var (sX,sY,rX,rY) = listOf(0,0,0,0)

    //If true santa moves, false Robot Santa moves
    var santaMove = true
    // Input is one line
    for (d in input[0]) {
        var (x,y) = if (santaMove) listOf(sX,sY) else listOf(rX,rY)
        if (d == '^') {
            y++
        } else if (d == '>') {
            x++
        } else if (d == 'v') {
            y--
        } else if (d == '<') {
            x--
        } else {
            continue
        }
        // If exists, increment value, else new value will be one
        visitedHouseMap["($x,$y)"] = visitedHouseMap.getOrDefault("($x,$y)", 0) + 1
        if (santaMove) {
            sX = x
            sY = y
        } else {
            rX = x
            rY = y
        }
        santaMove = !santaMove
    }

    println("Houses visited by Santa: ${visitedHouseMap.size}")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_03/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}