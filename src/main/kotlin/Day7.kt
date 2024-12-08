package falcon

import kotlin.system.measureTimeMillis

class Day7(input:List<String>) {
    val equations = input.map { it.replace(":","").split(' ').map{it.toLong()} }

    fun testEquation(target:Long, arguments:List<Long>) : Boolean {
        if (arguments.size == 1)
            return arguments[0] == target
        if (arguments[0]>target)
            return false

        val addList = listOf(arguments[0] + arguments[1]) + arguments.drop(2)
        val multList = listOf(arguments[0] * arguments[1]) + arguments.drop(2)
        return testEquation(target, addList) || testEquation(target, multList)
    }

    fun part1() {
        var total = 0L
        for(equ in equations) {
            val target = equ[0]
            val arguments = equ.drop(1)
            val pass = testEquation(target,arguments)
            if (pass)
                total+=target
            //println("$equ $pass")
        }
        println("Part1 Total: $total")
    }


    fun testEquationPart2(target:Long, arguments:List<Long>) : Boolean {
        if (arguments.size == 1)
            return arguments[0] == target
        if (arguments[0]>target)
            return false

        // combine the first two arguments with eiter + or * |
        val remainder = arguments.drop(2)
        val addList = listOf(arguments[0] + arguments[1]) + remainder
        val multList = listOf(arguments[0] * arguments[1]) + remainder
        val concatList = listOf((arguments[0].toString() + arguments[1].toString()).toLong()) + remainder
        return testEquationPart2(target, addList) ||
               testEquationPart2(target, multList) ||
               testEquationPart2(target, concatList)
    }


    fun part2() : Long {
        var total = 0L
        for(equ in equations) {
            val target = equ[0]
            val arguments = equ.drop(1)
            val pass = testEquationPart2(target,arguments)
            if (pass)
                total+=target
            //println("$equ $pass")
        }
        return total
    }


    fun run() {
        part1()
        println()

        val timeInMillis = measureTimeMillis {
            val result = part2()
            println("Result: $result")
        }

        println("Part2 Time taken: $timeInMillis ms")
    }


}