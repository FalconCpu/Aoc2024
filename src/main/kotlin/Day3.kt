package falcon

import java.io.File

class Day3 {
    fun part1(input:String) {
        val regex= Regex("""mul\((\d+),(\d+)\)""")
        val matches = regex.findAll(input)
        var sum = matches.sumOf { it.groupValues[1].toLong() * it.groupValues[2].toLong() }
        println("Sum = $sum\n")
    }

    fun part2(input:String) {
        val sb = StringBuilder()
        var enabled = true
        for (i in input.indices) {
            if (i<input.length-4 && input.substring(i, i + 4) == "do()")
                enabled = true
            else if (i<input.length-7 && input.substring(i, i + 7) == "don't()")
                enabled = false
            if (enabled)
                sb.append(input[i])
        }
        part1(sb.toString())
    }

    fun run() {
        val input = File("src/main/resources/InputDay3.txt").readText()
        part1(input)
        part2(input)
    }
}