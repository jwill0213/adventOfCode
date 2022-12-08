package day_06

import java.io.File

fun part1(input: List<String>) {
    for (c in 4..input[0].length) {
        if (input[0].subSequence(c-4, c).all(hashSetOf<Char>()::add)) {
            println("Characters processed before buffer: $c")
            break
        }
    }
}

fun part2(input: List<String>) {
    for (c in 14..input[0].length) {
        if (input[0].subSequence(c-14, c).all(hashSetOf<Char>()::add)) {
            println("Characters processed before message marker: $c")
            break
        }
    }
}

fun main(){
    val inputFile = File("src/main/kotlin/day_06/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}