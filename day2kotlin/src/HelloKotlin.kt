package demo

import java.io.BufferedReader
import java.io.File
import java.io.InputStream

fun main(args : Array<String>) {
    val lines = readFile("E:\\drive\\intellij\\aoc17\\day2\\input.txt")
    println(getCheckSum(lines))

}

fun readFile(filename : String) : MutableList<List<Int>>{
    val spreadsheet = mutableListOf<List<Int>>()
    val bufferedReader: BufferedReader = File(filename).bufferedReader()
    bufferedReader.useLines{lines->lines.forEach {
        val currline = it.split("\t")
        var currLineInt = currline.map {
            it.toInt()
        }
        spreadsheet.add(currLineInt)
    }}
    return spreadsheet
}

fun getCheckSum(spreadsheet : List<List<Int>>) : Int {
    var sum = 0
    for (numbers in spreadsheet) {
        var lowest = numbers[0]
        var highest = lowest
        for (number in numbers) {
            if (number < lowest) {
                lowest = number
            }

            if (number > highest) {
                highest = number
            }
        }
        sum += highest - lowest
    }
    return sum
}