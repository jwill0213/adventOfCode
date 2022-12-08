package day_02

import java.io.File

val POSSIBLE_MOVES = listOf(Triple("AX", "Rock", 1), Triple("BY", "Paper", 2), Triple("CZ", "Scissors", 3))
val RESULT_MAP = mapOf("Paper" to "Rock", "Scissors" to "Paper", "Rock" to "Scissors")

fun part1(input: List<String>) {
    var totalScore = 0
    for (game in input) {
        val moves = game.split(" ")
        val opponentMove = POSSIBLE_MOVES.stream().filter { m -> m.first.contains(moves[0]) }.findFirst().get()
        val myMove = POSSIBLE_MOVES.stream().filter { m -> m.first.contains(moves[1]) }.findFirst().get()
        totalScore += findRoundScore(myMove, opponentMove)
    }

    print("My total score: $totalScore")
}

fun part2(input: List<String>) {
    var totalScore = 0
    for (game in input) {
        var roundScore = 0
        val moves = game.split(" ")
        val opponentMove = POSSIBLE_MOVES.stream().filter { m -> m.first.contains(moves[0]) }.findFirst().get()
        val desiredResult = moves[1]
        val myMove = if (desiredResult == "Y") {
            opponentMove
        } else if (desiredResult == "X") {
            POSSIBLE_MOVES.stream().filter { m -> m.second == RESULT_MAP[opponentMove.second] }.findFirst().get()
        } else {
            POSSIBLE_MOVES.stream().filter { m -> m.second == RESULT_MAP.entries.find { it.value == opponentMove.second }?.key }.findFirst().get()
        }
        totalScore += findRoundScore(myMove, opponentMove)
    }

    print("My total score: $totalScore")
}

fun findRoundScore (myMove: Triple<String, String, Int> , opponentMove: Triple<String, String, Int>): Int {
    return if (myMove == opponentMove) {
        // Tie game
        myMove.third + 3
    } else if (RESULT_MAP[myMove.second] == opponentMove.second) {
        // I won
        myMove.third + 6
    } else {
        // I lost
        myMove.third
    }
}

fun main() {
    val inputFile = File("src/main/kotlin/day_02/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}