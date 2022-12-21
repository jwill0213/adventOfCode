package day_15

import java.io.File
import kotlin.math.abs

data class Point(var x: Int, var y: Int)

data class SensorSet(val sensor: Point, val beacon: Point) {
    fun getDistToBeacon(): Int {
        return abs(beacon.x - sensor.x) + abs(beacon.y - sensor.y)
    }

    fun getDist(other: Point): Int {
        return abs(other.x - sensor.x) + abs(other.y - sensor.y)
    }

    fun getWidth(yVal: Int): Int {
        return getDistToBeacon() - abs(sensor.y - yVal)
    }
}

fun part1(input: List<SensorSet>) {
    val minPossibleX = input.minOf { sensorSet -> sensorSet.sensor.x - sensorSet.getDistToBeacon() }
    val maxPossibleX = input.maxOf { sensorSet -> sensorSet.sensor.x + sensorSet.getDistToBeacon() }
    val yVal = 2000000
    val noBeaconSet: MutableSet<Point> = mutableSetOf()

    for (xVal in minPossibleX..maxPossibleX) {
        val curPoint = Point(xVal, yVal)
        for (s in input) {
            if (s.getDistToBeacon() >= s.getDist(curPoint) && s.beacon != curPoint) {
                // Spot is empty
                noBeaconSet.add(curPoint)
            }
        }
    }


    println("${noBeaconSet.size} points can't contain a beacon.")
}

fun part2(input: List<SensorSet>) {
    var solution: Point? = null
    val maxIndex = 4000000

    val blockedArray = Array<MutableList<IntRange>>(maxIndex + 1) { mutableListOf() }
    for (s in input) {
        for (y in 0..maxIndex) {
            val width = s.getWidth(y)
            if (width > 0) {
                blockedArray[y].add((s.sensor.x - width).coerceAtLeast(0)..(s.sensor.x + width).coerceAtMost(maxIndex))
            }
        }
    }

    for (yIndex in blockedArray.indices) {
        val sortedRanges = blockedArray[yIndex].sortedBy { it.first } // sort by smallest number
        var biggestX = 0 // biggest x starts at 0

        for (r in sortedRanges) {
            if (r.first > biggestX) {
                solution = Point(r.first - 1, yIndex)
            }
            if (r.last > biggestX) {
                biggestX = r.last
            }
        }
    }

    solution!!
    println(
        "Beacon can be placed at $solution with tuning frequency of ${
            solution.x.toBigInteger().multiply(4000000.toBigInteger()).plus(solution.y.toBigInteger())
        }"
    )

}

fun parseInput(input: List<String>): List<SensorSet> {
    val sensorBeaconList = mutableListOf<SensorSet>()

    for (l in input) {
        // numbers are always in order sensor x,y beacon x,y
        val pointVals: List<Int> = Regex("-?\\d+").findAll(l).map { it.value.toInt() }.toList()

        sensorBeaconList.add(SensorSet(Point(pointVals[0], pointVals[1]), Point(pointVals[2], pointVals[3])))
    }

    return sensorBeaconList
}

fun main() {
    val parsedInput = parseInput(File("src/main/kotlin/day_15/input.txt").readLines())
    print("\n----- Part 1 -----\n")
    part1(parsedInput)
    print("\n----- Part 2 -----\n")
    part2(parsedInput)
}