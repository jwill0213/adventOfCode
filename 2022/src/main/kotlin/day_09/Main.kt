package day_09

import java.io.File
import kotlin.math.abs

fun part1(input: List<String>) {
    var headLoc: Pair<Int,Int> = Pair(0,0)
    var tailLoc: Pair<Int,Int> = Pair(0,0)
    var visitedLoc: HashMap<String, Int> = hashMapOf("(0,0)" to 1)

    for (move in input) {
        val (dir, count) = move.split(" ")

        for (c in 1..count.toInt()){
            headLoc = moveDir(dir, headLoc)
            tailLoc = moveTail(headLoc, tailLoc)
            visitedLoc["(${tailLoc.first},${tailLoc.second})"] = visitedLoc.getOrDefault("(${tailLoc.first},${tailLoc.second})", 0) + 1
        }
    }
    println("Tail visited ${visitedLoc.size}")
}

fun part2(input: List<String>) {
    // Rope. Head is in index 0. Rest of tail is index 1 to 9
    var rope: MutableList<Pair<Int, Int>> = mutableListOf(Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0),Pair(0,0))
    var visitedLoc: HashMap<String, Int> = hashMapOf("(0,0)" to 1)

    for (move in input) {
        val (dir, count) = move.split(" ")

        for (c in 1..count.toInt()){
            rope[0] = moveDir(dir, rope[0])
            for (t in 1..9) {
                rope[t] = moveTail(rope[t-1], rope[t])
            }
            var tailLoc = rope[9]
            visitedLoc["(${tailLoc.first},${tailLoc.second})"] = visitedLoc.getOrDefault("(${tailLoc.first},${tailLoc.second})", 0) + 1
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
            if (headLoc.second - newTailLoc.second > 0) {
                // move up
                newTailLoc = moveDir("U", newTailLoc)
            } else {
                // move down
                newTailLoc = moveDir("D", newTailLoc)
            }
        } else if (headLoc.second == newTailLoc.second) {
            // y is same, move x
            if (headLoc.first - newTailLoc.first > 0) {
                // move right
                newTailLoc = moveDir("R", newTailLoc)
            } else {
                // move left
                newTailLoc = moveDir("L", newTailLoc)
            }
        } else {
            // neither is same, move diagnal
            // move y
            if (headLoc.second - newTailLoc.second > 0) {
                // move up
                newTailLoc = moveDir("U", newTailLoc)
            } else {
                // move down
                newTailLoc = moveDir("D", newTailLoc)
            }
            // move x
            if (headLoc.first - newTailLoc.first > 0) {
                // move right
                newTailLoc = moveDir("R", newTailLoc)
            } else {
                // move left
                newTailLoc = moveDir("L", newTailLoc)
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