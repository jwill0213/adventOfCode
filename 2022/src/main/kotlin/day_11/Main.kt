package day_11

import java.io.File
import java.math.BigInteger

data class Monkey(val items: ArrayDeque<BigInteger>, val operation: String, val divisibleByTest: Int, val ifTrue: Int, val ifFalse: Int) {
    var inspections: Long = 0
}

fun part1(input: List<String>) {
    val monkeyList: MutableList<Monkey> = mutableListOf()
    var monkeyStartParse = 0
    // Each monkey has 6 lines, followed by blank line
    while (monkeyStartParse < input.size) {
        // get start list
        val startItems = input[monkeyStartParse + 1].split(": ")[1].split(", ").map{it.toBigInteger()}.toMutableList()
        val operation = input[monkeyStartParse + 2].split(" = ")[1]
        val divisibleByTest = input[monkeyStartParse+3].substringAfterLast(" ").toInt()
        val ifTrueThrow = input[monkeyStartParse+4].substringAfterLast(" ").toInt()
        val ifFalseThrow = input[monkeyStartParse+5].substringAfterLast(" ").toInt()
        monkeyList.add(Monkey(ArrayDeque(startItems), operation, divisibleByTest, ifTrueThrow, ifFalseThrow))
        monkeyStartParse += 7
    }

    for (round in 1..20) {
        for (m in monkeyList) {
            while (m.items.size > 0) {
                m.inspections++
                var itemWorry: BigInteger = m.items.removeFirst()
                // increase worry based on operation
                itemWorry = performOperation(itemWorry, m.operation.split(" "))
                // Bored monkey, divide by 3
                itemWorry = itemWorry.divide(BigInteger.valueOf(3))
                // Check divisible test
                if (itemWorry.mod(m.divisibleByTest.toBigInteger()) == BigInteger.ZERO) {
                    // Is divisible by, throw to
                    monkeyList[m.ifTrue].items.addLast(itemWorry)
                } else {
                    monkeyList[m.ifFalse].items.addLast(itemWorry)
                }
            }
        }
    }
    val (m1, m2) = monkeyList.sortedBy { it.inspections }.map {it.inspections}.takeLast(2)
    println("Most active monkeys had $m1 and $m2 inspections. Answer: ${m1 * m2}")
}

fun performOperation(old: BigInteger, op: List<String>): BigInteger {
    val operand = op[1]
    val num1 = if (op[0] == "old") old else op[0].toBigInteger()
    val num2 = if (op[2] == "old") old else op[2].toBigInteger()
    return if (operand == "*") {
        num1.multiply(num2)
    } else {
        num1.add(num2)
    }
}

fun part2(input: List<String>) {
    val monkeyList: MutableList<Monkey> = mutableListOf()
    var monkeyStartParse = 0
    // Each monkey has 6 lines, followed by blank line
    while (monkeyStartParse < input.size) {
        // get start list
        val startItems = input[monkeyStartParse + 1].split(": ")[1].split(", ").map{it.toBigInteger()}.toMutableList()
        val operation = input[monkeyStartParse + 2].split(" = ")[1]
        val divisibleByTest = input[monkeyStartParse+3].substringAfterLast(" ").toInt()
        val ifTrueThrow = input[monkeyStartParse+4].substringAfterLast(" ").toInt()
        val ifFalseThrow = input[monkeyStartParse+5].substringAfterLast(" ").toInt()
        monkeyList.add(Monkey(ArrayDeque(startItems), operation, divisibleByTest, ifTrueThrow, ifFalseThrow))
        monkeyStartParse += 7
    }

    val bigMod = monkeyList.map(Monkey::divisibleByTest).reduce(Int::times)
    for (round in 1..10000) {
        for (m in monkeyList) {
            while (m.items.size > 0) {
                m.inspections++
                var itemWorry: BigInteger = m.items.removeFirst()
                // increase worry based on operation
                itemWorry = performOperation(itemWorry, m.operation.split(" "))
                // Check divisible test
                itemWorry = itemWorry.mod(bigMod.toBigInteger())
                if (itemWorry.mod(m.divisibleByTest.toBigInteger()) == BigInteger.ZERO) {
                    // Is divisible by, throw to
                    monkeyList[m.ifTrue].items.addLast(itemWorry)
                } else {
                    monkeyList[m.ifFalse].items.addLast(itemWorry)
                }
            }
        }
    }
    val (m1, m2) = monkeyList.sortedBy { it.inspections }.map {it.inspections}.takeLast(2)
    println("Most active monkeys had $m1 and $m2 inspections. Answer: ${m1 * m2}")
}

fun main(){
    val inputFile = File("src/main/kotlin/day_11/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}