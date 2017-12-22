import java.io.BufferedReader
import java.io.File

fun main(args : Array<String>) {
    val lines = readFile("d:\\drive\\intellij\\aoc17\\day5\\input.txt")
    println(stepCount(lines))
}

fun readFile(filename : String) : MutableList<Int>{
    val trampolines : MutableList<Int> = mutableListOf()
    val bufferedReader: BufferedReader = File(filename).bufferedReader()
    bufferedReader.useLines{lines->lines.forEach {
        trampolines.add(it.toInt())
    }}
    return trampolines
}

fun stepCount(trampolines : MutableList<Int>) : Int {
    var jumpcount = 0
    var index = 0
    while(index < trampolines.size ) {
        var next = index + trampolines[index]
        trampolines[index] =
        if(trampolines[index] >= 3) {
            trampolines[index]--
        } else {
            trampolines[index]++
        }
        jumpcount++
        index = next;
    }

    return jumpcount
}