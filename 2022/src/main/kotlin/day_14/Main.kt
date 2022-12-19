package day_14

import java.io.File

data class Point(val x: Int, val y: Int) {
    constructor(pointList: List<String>) : this(pointList[0].toInt(), pointList[1].toInt())
}

fun part1(input: List<String>) {
    val blockedPoints: MutableSet<Point> = parseInput(input)
    val lastBlockedY: Int = blockedPoints.maxOfOrNull { it.y }!!
    var curSandPoint = Point(500, 0)
    var fallenSandUnits = 0
    while (true) {
        if (curSandPoint.y >= lastBlockedY) {
            // No possible blockages below.
            break
        }
        if (blockedPoints.contains(Point(curSandPoint.x, curSandPoint.y + 1))) {
            // Point directly below is blocked
            if (blockedPoints.contains(Point(curSandPoint.x - 1, curSandPoint.y + 1))) {
                // left is blocked
                if (blockedPoints.contains(Point(curSandPoint.x + 1, curSandPoint.y + 1))) {
                    // right is blocked. Sand is at rest
                    blockedPoints.add(curSandPoint)
                    // next point
                    curSandPoint = Point(500, 0)
                    fallenSandUnits++
                } else {
                    curSandPoint = Point(curSandPoint.x + 1, curSandPoint.y + 1)
                }
            } else {
                curSandPoint = Point(curSandPoint.x - 1, curSandPoint.y + 1)
            }
        } else {
            curSandPoint = Point(curSandPoint.x, curSandPoint.y + 1)
        }
    }

    print("$fallenSandUnits have fallen before end has been reached.")
}

fun part2(input: List<String>) {
    val blockedPoints: MutableSet<Point> = parseInput(input)
    val floorY: Int = blockedPoints.maxOfOrNull { it.y }!! + 2
    var curSandPoint = Point(500, 0)
    var fallenSandUnits = 0
    while (true) {
        if (blockedPoints.contains(Point(500,0))) {
            // Falling sand is blocked
            break
        }
        if (curSandPoint.y + 1 == floorY) {
            // Hit the floor. At rest
            blockedPoints.add(curSandPoint)
            curSandPoint = Point(500,0)
            fallenSandUnits++
        } else if (blockedPoints.contains(Point(curSandPoint.x, curSandPoint.y + 1))) {
            // Point directly below is blocked
            if (blockedPoints.contains(Point(curSandPoint.x - 1, curSandPoint.y + 1))) {
                // left is blocked
                if (blockedPoints.contains(Point(curSandPoint.x + 1, curSandPoint.y + 1))) {
                    // right is blocked. Sand is at rest
                    blockedPoints.add(curSandPoint)
                    // next point
                    curSandPoint = Point(500, 0)
                    fallenSandUnits++
                } else {
                    curSandPoint = Point(curSandPoint.x + 1, curSandPoint.y + 1)
                }
            } else {
                curSandPoint = Point(curSandPoint.x - 1, curSandPoint.y + 1)
            }
        } else {
            curSandPoint = Point(curSandPoint.x, curSandPoint.y + 1)
        }
    }

    print("$fallenSandUnits have fallen before 500,0 is blocked.")
}

fun parseInput(input: List<String>): MutableSet<Point> {
    val blockedPointsSet: MutableSet<Point> = mutableSetOf()
    for (l in input) {
        val linePoints = l.split(" -> ")
        for (i in 0 until linePoints.lastIndex) {
            val p1 = Point(linePoints[i].split(','))
            val p2 = Point(linePoints[i + 1].split(','))

            if (p1.x != p2.x) {
                for (j in p1.x.coerceAtMost(p2.x)..p1.x.coerceAtLeast(p2.x)) {
                    blockedPointsSet.add(Point(j, p1.y))
                }
            } else if (p1.y != p2.y) {
                for (j in p1.y.coerceAtMost(p2.y)..p1.y.coerceAtLeast(p2.y)) {
                    blockedPointsSet.add(Point(p1.x, j))
                }
            }
        }
    }

    return blockedPointsSet
}

fun main() {
    val inputLines = File("src/main/kotlin/day_14/input.txt").readLines()
    print("\n----- Part 1 -----\n")
    part1(inputLines)
    print("\n----- Part 2 -----\n")
    part2(inputLines)
}