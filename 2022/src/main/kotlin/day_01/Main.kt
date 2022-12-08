package day_01

import java.io.File

fun part1(input: List<String>) {
    var mostCalories = 0
    var totalForCurrentElf = 0

    for (meal in input) {
        if (meal.isEmpty()) {
            if (mostCalories < totalForCurrentElf) {
                mostCalories = totalForCurrentElf
            }
            totalForCurrentElf = 0
        } else {
            totalForCurrentElf += meal.toInt()
        }
    }

    print("Most calories carried by an elf: $mostCalories")
}

fun part2(input: List<String>) {
    val top3 = mutableListOf(0,0,0)
    var totalForCurrentElf = 0

    for (meal in input) {
        if (meal.isEmpty()) {
            top3.add(totalForCurrentElf)
            top3.sortDescending()
            top3.removeLast()
            totalForCurrentElf = 0
        } else {
            totalForCurrentElf += meal.toInt()
        }
    }
    val ans = top3.sum()
    print("Sum of top 3 elves: $ans")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_01/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}