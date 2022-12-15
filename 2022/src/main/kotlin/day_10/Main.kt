package day_10

import java.io.File

fun part1(input: List<String>) {
    var currentCommand = 0
    var incrementAmount: Int? = null
    var register = 1
    var finalSum = 0
    for (cycle in 1..220) {
        val command = input[currentCommand]
        if (cycle in listOf(20,60,100,140,180,220)) {
            finalSum += register * cycle
        }
        if (command == "noop") {
            // If noop, skip the cycle and increment the command
            currentCommand++
        } else {
            if (incrementAmount == null) {
                incrementAmount = command.split(" ")[1].toInt()
            } else {
                register += incrementAmount
                incrementAmount = null
                currentCommand++
            }
        }
    }
    println("Sum of signal strengths $finalSum")
}

fun part2(input: List<String>) {
    var currentCommand = 0
    var incrementAmount: Int? = null
    var register = 1
    var crtLine: MutableList<Char> = mutableListOf()
    for (cycle in 1..240) {
        val command = input[currentCommand]
        if(crtLine.size in register-1..register+1) {
            crtLine.add('#')
        } else {
            crtLine.add('.')
        }
        if (crtLine.size == 40) {
            println(crtLine.joinToString(""))
            crtLine = mutableListOf()
        }
        if (command == "noop") {
            // If noop, skip the cycle and increment the command
            currentCommand++
        } else {
            if (incrementAmount == null) {
                incrementAmount = command.split(" ")[1].toInt()
            } else {
                register += incrementAmount
                incrementAmount = null
                currentCommand++
            }
        }
    }
}

fun main(){
    val inputFile = File("src/main/kotlin/day_10/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}