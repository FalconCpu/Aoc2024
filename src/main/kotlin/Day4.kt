package falcon

import java.io.File

class Day4(val input: List<String>) {

    fun get(y:Int, x:Int) : Char {
        if (y<0 || y>=input.size || x<0 || x>=input[y].length)
            return ' ';
        return input[y][x]
    }

    fun match(y:Int, x: Int, dy:Int, dx:Int) : Boolean {
        return ((get(y,x)=='X') && get(y+dy,x+dx)=='M' && get(y+2*dy,x+2*dx)=='A' && get(y+3*dy,x+3*dx)=='S')
    }

    fun match2(y:Int, x: Int) : Boolean {
        if (get(y,x)!='A')
            return false
        if (get(y-1,x-1)=='M' && get(y+1,x-1)=='M' && get(y-1,x+1)=='S' && get(y+1,x+1)=='S') return true
        if (get(y-1,x-1)=='S' && get(y+1,x-1)=='S' && get(y-1,x+1)=='M' && get(y+1,x+1)=='M') return true
        if (get(y-1,x-1)=='M' && get(y-1,x+1)=='M' && get(y+1,x+1)=='S' && get(y+1,x-1)=='S') return true
        if (get(y-1,x-1)=='S' && get(y-1,x+1)=='S' && get(y+1,x+1)=='M' && get(y+1,x-1)=='M') return true
        return false
    }

    fun part1(input:List<String>) {
        var count = 0
        for(y in input.indices) {
            for(x in input[y].indices) {
                if (match(y,x,1,0))   count++   // North
                if (match(y,x,1,1))   count++   // Northeast
                if (match(y,x,0,1))   count++   // East
                if (match(y,x,-1,1))  count++   // Southeast
                if (match(y,x,-1,0))  count++   // South
                if (match(y,x,-1,-1)) count++   // Southwast
                if (match(y,x,0,-1))  count++   // West
                if (match(y,x,1,-1))  count++   // Northwest
            }
        }
        println("Part 1: $count")
    }

    fun part2(input:List<String>) {
        var count = 0
        for(y in input.indices) {
            for(x in input[y].indices) {
                if (match2(y,x))  count++
            }
        }
        println("Part 2: $count")
    }


    fun run() {
        val input = "src/main/resources/InputDay4.txt"
        val lines = File(input).readLines()
        part1(lines)
        part2(lines)
    }
}