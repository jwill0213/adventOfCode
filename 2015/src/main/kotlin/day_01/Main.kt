package day_01

import java.io.File

fun part1(input: List<String>) {
    var currentFloor = 0
    //input is only one line so navigate each character
    for (move in input[0]) {
        if (move == '(') {
            currentFloor++
        } else {
            currentFloor--
        }
    }
    println("Final floor $currentFloor")
}

fun part2(input: List<String>) {
    var currentFloor = 0
    var basementCommand = 0
    //input is only one line so navigate each character
    for (move in input[0].indices) {
        if (input[0][move] == '(') {
            currentFloor++
        } else {
            currentFloor--
        }
        if (currentFloor == -1) {
            basementCommand = move + 1
            break
        }
    }
    println("Santa enters basement on command $basementCommand")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_01/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}