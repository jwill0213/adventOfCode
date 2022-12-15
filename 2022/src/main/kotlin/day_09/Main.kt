package day_09

import java.io.File
import kotlin.math.abs

fun part1(input: List<String>) {
    var headLoc: Pair<Int,Int> = Pair(0,0)
    var tailLoc: Pair<Int,Int> = Pair(0,0)
    val visitedLoc: MutableSet<Pair<Int, Int>> = mutableSetOf(Pair(0,0))

    for (move in input) {
        val (dir, count) = move.split(" ")

        for (c in 1..count.toInt()){
            headLoc = moveDir(dir, headLoc)
            tailLoc = moveTail(headLoc, tailLoc)
            visitedLoc.add(tailLoc)
        }
    }
    println("Tail visited ${visitedLoc.size}")
}

fun part2(input: List<String>) {
    // Rope. Head is in index 0. Rest of tail is index 1 to 9
    val rope: MutableList<Pair<Int, Int>> = MutableList(10) { Pair(0,0) }
    val visitedLoc: MutableSet<Pair<Int, Int>> = mutableSetOf(Pair(0,0))

    for (move in input) {
        val (dir, count) = move.split(" ")

        for (c in 1..count.toInt()){
            rope[0] = moveDir(dir, rope[0])
            for (t in 1 until rope.size) {
                rope[t] = moveTail(rope[t-1], rope[t])
            }
            visitedLoc.add(rope[9])
        }
    }
    println("Tail visited ${visitedLoc.size}")
}

fun moveTail(headLoc: Pair<Int, Int>, tailLoc: Pair<Int, Int>): Pair<Int, Int> {
    var newTailLoc = tailLoc
    if (distanceFarApart(headLoc,newTailLoc)) {
        // move tail
        if (headLoc.first == newTailLoc.first) {
            // x is same, move y
            newTailLoc = if (headLoc.second - newTailLoc.second > 0) {
                // move up
                moveDir("U", newTailLoc)
            } else {
                // move down
                moveDir("D", newTailLoc)
            }
        } else if (headLoc.second == newTailLoc.second) {
            // y is same, move x
            newTailLoc = if (headLoc.first - newTailLoc.first > 0) {
                // move right
                moveDir("R", newTailLoc)
            } else {
                // move left
                moveDir("L", newTailLoc)
            }
        } else {
            // neither is same, move diagnal
            // move y
            newTailLoc = if (headLoc.second - newTailLoc.second > 0) {
                // move up
                moveDir("U", newTailLoc)
            } else {
                // move down
                moveDir("D", newTailLoc)
            }
            // move x
            newTailLoc = if (headLoc.first - newTailLoc.first > 0) {
                // move right
                moveDir("R", newTailLoc)
            } else {
                // move left
                moveDir("L", newTailLoc)
            }
        }
    }
    return newTailLoc
}

fun distanceFarApart(loc1: Pair<Int,Int>,loc2: Pair<Int, Int>): Boolean {
    return (abs(loc1.first - loc2.first) > 1 || abs(loc1.second - loc2.second) > 1)
}

fun moveDir(dir: String, loc: Pair<Int,Int>): Pair<Int,Int> {
    var (x,y) = loc

    if (dir == "U") {
        y += 1
    } else if (dir == "R") {
        x += 1
    } else if (dir == "D") {
        y -= 1
    }else if (dir == "L") {
        x -= 1
    }

    return Pair(x,y)
}

fun main(){
    val inputFile = File("src/main/kotlin/day_09/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}