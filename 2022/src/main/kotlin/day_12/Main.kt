package day_12

import java.io.File

data class Point(val x: Int, val y: Int) {
    fun findAccessiblePoints(): List<Point> {
        return listOf(Point(x - 1, y), Point(x + 1, y), Point(x, y - 1), Point(x, y + 1))
    }
}

fun part1(input: List<String>) {
    val (start,end,heightMap) = parseInput(input)

    println("Shortest path to end is: ${findPathLength(start,end,heightMap)}")
}

fun part2(input: List<String>) {
    val (start,end,heightMap) = parseInput(input)
    var shortest = findPathLength(start, end, heightMap)

    for (p in heightMap.filterValues { it == 'a'.code }) {
        shortest = shortest.coerceAtMost(findPathLength(p.key, end, heightMap))
    }

    println("Shortest path to end from 'a' is: $shortest")
}

fun parseInput(input: List<String>): Triple<Point,Point,HashMap<Point, Int>> {
    val heightMap: HashMap<Point, Int> = hashMapOf()
    var start: Point? = null
    var end: Point? = null
    for (y in input.indices) {
        for (x in input[y].indices) {
            when (input[y][x]) {
                'S' -> {
                    start = Point(x,y)
                    heightMap[start] = 'a'.code
                }
                'E' -> {
                    end = Point(x,y)
                    heightMap[end] = 'z'.code
                }
                else -> {
                    heightMap[Point(x,y)] = input[y][x].code
                }
            }
        }
    }

    return Triple(start!!, end!!, heightMap)
}

fun findPathLength(start: Point, end: Point, heightMap: HashMap<Point, Int>): Int {
    // Find the path
    val evalQueue: ArrayDeque<Point> = ArrayDeque(listOf(start))
    val visitedCost: HashMap<Point, Int> = hashMapOf(start to 0)
    while (evalQueue.isNotEmpty()) {
        val currentPoint = evalQueue.removeFirst()
        val currentHeight = heightMap[currentPoint]!!

        for (p in currentPoint.findAccessiblePoints()) {
            val pHeight: Int? = heightMap[p]
            if (pHeight == null || pHeight > currentHeight + 1) {
                continue
            } else if (visitedCost.getOrDefault(p, Int.MAX_VALUE) > visitedCost[currentPoint]!! + 1) {
                evalQueue.addLast(p)
                visitedCost[p] = visitedCost[currentPoint]!! + 1
            }
        }
    }

    return visitedCost.getOrDefault(end, Int.MAX_VALUE)
}

fun main() {
    val inputFile = File("src/main/kotlin/day_12/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}