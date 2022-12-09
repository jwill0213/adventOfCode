package day_04

import java.io.File
import java.security.MessageDigest

fun part1(input: List<String>) {
    var answer = 1
    val key = input[0]

    while(!"$key$answer".toMD5().startsWith("00000")) {
        answer++
    }
    println("First int that MD5 hash starts with 00000: $answer")
}

fun part2(input: List<String>) {
    var answer = 1
    val key = input[0]

    while(!"$key$answer".toMD5().startsWith("000000")) {
        answer++
    }
    println("First int that MD5 hash starts with 00000: $answer")
}

fun String.toMD5(): String {
    val bytes = MessageDigest.getInstance("MD5").digest(this.toByteArray())
    return bytes.toHex()
}

fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}

fun main(){
    val inputFile = File("src/main/kotlin/day_04/input.txt")
    print("\n----- Part 1 -----\n")
    part1(inputFile.readLines())
    print("\n----- Part 2 -----\n")
    part2(inputFile.readLines())
}