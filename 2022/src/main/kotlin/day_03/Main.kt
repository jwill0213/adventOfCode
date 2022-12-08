package day_03

import java.io.File

val ITEM_VAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun part1(input: List<String>) {
    var result = 0
    for (rucksack in input) {
        val halfSize = rucksack.length / 2
        val c1 = rucksack.subSequence(0, halfSize)
        val c2 = rucksack.subSequence(halfSize, rucksack.length)
        for (item in c1) {
            if (item in c2) {
                result += ITEM_VAL.indexOf(item) + 1
                break
            }
        }
    }
    print("Priority sum of rucksacks: $result")
}

fun part2(input: List<String>) {
    var result = 0
    for (r in 0..input.size - 2 step 3) {
        val elf1 = input[r]
        val elf2 = input[r + 1]
        val elf3 = input[r + 2]
        for (item in elf1) {
            if (item in elf2 && item in elf3) {
                result += ITEM_VAL.indexOf(item) + 1
                break
            }
        }
    }
    print("Sum of common items: $result")
}

fun main() {
    val inputFile = File("src/main/kotlin/day_03/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}