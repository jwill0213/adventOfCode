package day_06

import java.io.File

fun part1(input: List<String>) {
    val lightMap: HashMap<String, Boolean> = hashMapOf()
    for (instruction in input) {
        val instructionList = instruction.split(" ")
        var startRange: List<String>
        var endRange: List<String>
        if (instructionList[0] == "toggle") {
            //toggle lights
            startRange = instructionList[1].split(",")
            endRange = instructionList[3].split(",")
            for (x in startRange[0].toInt()..endRange[0].toInt()) {
                for (y in startRange[1].toInt()..endRange[1].toInt()) {
                    lightMap["($x,$y)"] = ! lightMap.getOrDefault("($x,$y)", false)
                }
            }
        } else {
            startRange = instructionList[2].split(",")
            endRange = instructionList[4].split(",")
            for (x in startRange[0].toInt()..endRange[0].toInt()) {
                for (y in startRange[1].toInt()..endRange[1].toInt()) {
                    lightMap["($x,$y)"] = instructionList[1] == "on"
                }
            }
        }
    }
    println("Christmas lights on: ${lightMap.filter { it.value }.size}")
}

fun part2(input: List<String>) {
    val lightMap: HashMap<String, Int> = hashMapOf()
    for (instruction in input) {
        val instructionList = instruction.split(" ")
        var startRange: List<String>
        var endRange: List<String>
        if (instructionList[0] == "toggle") {
            //toggle lights
            startRange = instructionList[1].split(",")
            endRange = instructionList[3].split(",")
            for (x in startRange[0].toInt()..endRange[0].toInt()) {
                for (y in startRange[1].toInt()..endRange[1].toInt()) {
                    lightMap["($x,$y)"] = lightMap.getOrDefault("($x,$y)", 0) + 2
                }
            }
        } else {
            startRange = instructionList[2].split(",")
            endRange = instructionList[4].split(",")
            for (x in startRange[0].toInt()..endRange[0].toInt()) {
                for (y in startRange[1].toInt()..endRange[1].toInt()) {
                    val currentBrightness = lightMap.getOrDefault("($x,$y)", 0)
                    if (instructionList[1] == "off" && currentBrightness > 0) {
                        lightMap["($x,$y)"] = currentBrightness - 1
                    } else if (instructionList[1] == "on") {
                        lightMap["($x,$y)"] = currentBrightness + 1
                    }
                }
            }
        }
    }
    println("Christmas lights total brightness: ${lightMap.map{it.value}.toList().sum()}")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_06/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}