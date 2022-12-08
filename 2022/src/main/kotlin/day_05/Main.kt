package day_05

import java.io.File
import java.util.ArrayDeque

fun part1(input: List<String>) {
    // Read in starting stacks
    var moves: List<String> = listOf()
    // Map of stack to current state
    val stackIndexMap: HashMap<Int, ArrayDeque<Char>> = hashMapOf()

    for (l in 0..input.size) {
        if(input[l].isEmpty()) {
            // The first empty line is the end of state and start of moves. Split input there
            val initialState = input.subList(0, l)
            moves = input.subList(l + 1, input.size)
            for (c in initialState[l-1]) {
                if (c.isDigit()) {
                    val stackIndex = initialState[l-1].indexOf(c)
                    val newStackState: ArrayDeque<Char> = ArrayDeque()
                    for (line in initialState.subList(0, initialState.size-1)) {
                        if (line[stackIndex].toString().isNotBlank()) {
                            newStackState.addFirst(line[stackIndex])
                        }
                    }
                    stackIndexMap[c.digitToInt()] = newStackState
                }
            }
            break
        }
    }

    for (m in moves) {
        val cratesToMove = m.split(' ')[1].toInt()
        val startingStack = m.split(' ')[3].toInt()
        val endStack = m.split(' ')[5].toInt()

        for (n in 1..cratesToMove) {
            val crate: Char? = stackIndexMap[startingStack]?.removeLast()
            stackIndexMap[endStack]?.addLast(crate)
        }
    }

    var result: String = ""
    for (e in stackIndexMap) {
        result += e.value.last
    }
    print("Top crates in each stack: $result")
}

fun part2(input: List<String>) {
    // Read in starting stacks
    var moves: List<String> = listOf()
    // Map of stack to current state
    val stackIndexMap: HashMap<Int, ArrayDeque<Char>> = hashMapOf()

    for (l in 0..input.size) {
        if(input[l].isEmpty()) {
            // The first empty line is the end of state and start of moves. Split input there
            val initialState = input.subList(0, l)
            moves = input.subList(l + 1, input.size)
            for (c in initialState[l-1]) {
                if (c.isDigit()) {
                    val stackIndex = initialState[l-1].indexOf(c)
                    val newStackState: ArrayDeque<Char> = ArrayDeque()
                    for (line in initialState.subList(0, initialState.size-1)) {
                        if (line[stackIndex].toString().isNotBlank()) {
                            newStackState.addFirst(line[stackIndex])
                        }
                    }
                    stackIndexMap[c.digitToInt()] = newStackState
                }
            }
            break
        }
    }

    for (m in moves) {
        val cratesToMove = m.split(' ')[1].toInt()
        val startingStack = m.split(' ')[3].toInt()
        val endStack = m.split(' ')[5].toInt()

        var cratesOnCrane: List<Char> = mutableListOf()
        for (n in 1..cratesToMove) {
            cratesOnCrane += stackIndexMap[startingStack]?.removeLast() !!
        }

        for (c in cratesOnCrane.reversed()) {
            stackIndexMap[endStack]?.addLast(c)
        }
    }

    var result: String = ""
    for (e in stackIndexMap) {
        result += e.value.last
    }
    print("Top crates in each stack: $result")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_05/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}