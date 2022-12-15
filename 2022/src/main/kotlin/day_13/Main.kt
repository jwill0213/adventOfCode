package day_13

import java.io.File

fun part1(packetPairList: List<Pair<List<Any>, List<Any>>>) {
    var curPair = 1
    var pairTotals = 0
    for (pair in packetPairList) {
        val left = pair.first
        val right = pair.second

        val packetCompare = PacketComparator()
        if (packetCompare.compare(left,right) == -1) {
            pairTotals += curPair
        }
        curPair++
    }

    println("Sum of all valid packet pairs is $pairTotals")
}

fun part2(packetPairList: List<Pair<List<Any>, List<Any>>>) {
    val finalPacketList = packetPairList.flatMap { listOf(it.first,it.second) }.toMutableList()
    finalPacketList.add(listOf(listOf(2)))
    finalPacketList.add(listOf(listOf(6)))

    val packetCompare = PacketComparator()
    finalPacketList.sortWith(packetCompare)

    val divider1 = finalPacketList.indexOf(listOf(listOf(2))) + 1
    val divider2 = finalPacketList.indexOf(listOf(listOf(6))) + 1

    println("Decoder Key is ${divider1 * divider2}")
}

class PacketComparator: Comparator<List<Any>>{
    override fun compare(o1: List<Any>?, o2: List<Any>?): Int {
        if(o1 == null || o2 == null)
            return 0
        val comparison = compareLists(o1,o2)
        if (comparison != null && comparison) {
            return -1
        }
        return 1
    }
}

fun compareLists(left: List<Any>, right: List<Any>): Boolean? {
    if (left.isEmpty() && right.isEmpty()) {
        // continue since left is same as right
        return null
    }
    if (left.isEmpty()) {
        // true since left is smaller than right
        return true
    }
    if (right.isEmpty()) {
        // false since right is out before left
        return false
    }
    // both lists have items, compare
    for (i in left.indices) {
        if (i > right.lastIndex) {
            return false
        }
        if (left[i] is Int && right[i] is Int) {
            val lVal = left[i] as Int
            val rVal = right[i] as Int
            // compare. If left < right true, same continue, left > right false
            if (lVal < rVal) {
                return true
            } else if (lVal > rVal) {
                return false
            }
        } else {
            // At least one is a list. Convert them to lists and recurse
            @Suppress("UNCHECKED_CAST")
            val l1: List<Any> = if (left[i] is Int) listOf(left[i]) else left[i] as List<Any>

            @Suppress("UNCHECKED_CAST")
            val l2: List<Any> = if (right[i] is Int) listOf(right[i]) else right[i] as List<Any>

            val lstCompare = compareLists(l1,l2)

            if (lstCompare != null) {
                return lstCompare
            }
        }
    }
    if (left.lastIndex < right.lastIndex) {
        return true
    }

    return null
}

fun parseInput(input: List<String>): List<Pair<List<Any>, List<Any>>> {
    val packetPairList: MutableList<Pair<List<Any>, List<Any>>> = mutableListOf()

    for (i in input.indices step 3) {
        packetPairList.add(Pair(parsePacket(input[i]), parsePacket(input[i + 1])))
    }

    return packetPairList
}

fun parsePacket(packetStr: String): List<Any> {
    var tmpPacketStr = packetStr
    var curList = 0
    val listMap: HashMap<Int, String> = hashMapOf()

    // get startAndEnd of most inner list
    var listStart = packetStr.lastIndexOf('[')
    var listEnd = listStart + packetStr.substring(listStart).indexOf(']')
    while (true) {
        listMap[curList] = tmpPacketStr.substring(listStart, listEnd + 1)
        tmpPacketStr = tmpPacketStr.replaceRange(listStart, listEnd + 1, "{L$curList}")

        if (listStart == 0) {
            break
        } else {
            curList++
        }
        // get next list
        listStart = tmpPacketStr.lastIndexOf('[')
        listEnd = listStart + tmpPacketStr.substring(listStart).indexOf(']')
    }

    // Convert lists to actual list and int objects. Currently Strings
    val convertedListMap: HashMap<Int, List<Any>> = hashMapOf()
    for (i in 0..curList) {
        val tmpList = listMap[i]!!.substring(1 until listMap[i]!!.lastIndex).split(",")
        val newList: MutableList<Any> = mutableListOf()
        for (item in tmpList) {
            if (item.isEmpty()) {
                //do nothing
                continue
            } else if (item.toIntOrNull() != null) {
                newList.add(item.toInt())
            } else {
                // Get the number from {L#}
                val number = item.removePrefix("{L").removeSuffix("}")
                val listNum = number.toInt()
                newList.add(convertedListMap[listNum]!!)
            }
        }
        convertedListMap[i] = newList
    }

    return convertedListMap[curList]!!
}

fun main() {
    val parsedInput = parseInput(File("src/main/kotlin/day_13/input.txt").readLines())
    print("\n----- Part 1 -----\n")
    part1(parsedInput)
    print("\n----- Part 2 -----\n")
    part2(parsedInput)
}