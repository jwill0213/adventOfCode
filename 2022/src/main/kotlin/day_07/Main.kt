package day_07

import java.io.File

fun part1(input: List<String>) {
    // Map of directory to contents. Contents can be string (directory) or int(files size)
    val systemMap: HashMap<String, MutableList<Any>> = hashMapOf()
    val directoryStack: ArrayDeque<String> = ArrayDeque()

    //build filesystem
    for (line in input) {
        if (line[0] == '$') {
            // is command
            val cmd = line.split(" ")[1]
            if (cmd == "cd") {
                // nav to new directory
                val arg = line.split(" ")[2]
                if (arg == "..") {
                    // go back one dir
                    directoryStack.removeLast()
                } else {
                    // arg is new directory. Add to stack
                    directoryStack.addLast(arg)
                    systemMap[directoryStack.toArray().joinToString("/")] = mutableListOf()
                }
            }

        } else if(line.split(" ")[0] == "dir") {
            // is directory
            val fileStr = directoryStack.toArray().joinToString("/") + "/${line.split(" ")[1]}"
            systemMap[directoryStack.toArray().joinToString("/")]?.add(fileStr) !!
        } else {
            // is file save size
            systemMap[directoryStack.toArray().joinToString("/")]?.add(line.split(" ")[0].toInt()) !!
        }
    }

    var totalDirSizes = 0
    for (e in systemMap) {
        val dirSize: Int = getSizeOfDir(systemMap, e.key)
        if (dirSize <= 100000) {
            totalDirSizes += dirSize
        }
    }

    println("Size of all dirs less than 100000: $totalDirSizes")
}

fun getSizeOfDir(systemMap: HashMap<String, MutableList<Any>>, dir: String): Int {
    var contentsSum = 0
    val dirContents = systemMap[dir] !!
    for (item in dirContents) {
        if (item is Int) {
            contentsSum += item
        } else {
            contentsSum += getSizeOfDir(systemMap, item as String)
        }
    }
    return contentsSum
}

fun part2(input: List<String>) {
    // Map of directory to contents. Contents can be string (directory) or int(files size)
    val systemMap: HashMap<String, MutableList<Any>> = hashMapOf()
    val directoryStack: ArrayDeque<String> = ArrayDeque()

    //build filesystem
    for (line in input) {
        if (line[0] == '$') {
            // is command
            val cmd = line.split(" ")[1]
            if (cmd == "cd") {
                // nav to new directory
                val arg = line.split(" ")[2]
                if (arg == "..") {
                    // go back one dir
                    directoryStack.removeLast()
                } else {
                    // arg is new directory. Add to stack
                    directoryStack.addLast(arg)
                    systemMap[directoryStack.toArray().joinToString("/")] = mutableListOf()
                }
            }

        } else if(line.split(" ")[0] == "dir") {
            // is directory
            val fileStr = directoryStack.toArray().joinToString("/") + "/${line.split(" ")[1]}"
            systemMap[directoryStack.toArray().joinToString("/")]?.add(fileStr) !!
        } else {
            // is file save size
            systemMap[directoryStack.toArray().joinToString("/")]?.add(line.split(" ")[0].toInt()) !!
        }
    }

    val allDirSizes: MutableList<Int> = mutableListOf()
    for (e in systemMap) {
        val dirSize: Int = getSizeOfDir(systemMap, e.key)
        allDirSizes.add(dirSize)
    }

    // Total size based on root
    val neededSpace = 30000000 - (70000000 - getSizeOfDir(systemMap, "/"))

    println("Delete directory of size ${allDirSizes.filter { it >= neededSpace }.minOf { it }}")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_07/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}