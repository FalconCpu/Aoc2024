package falcon

class Day8 (val input:List<String>) {
    val antennae = readInput()
    val xRange = 0..<input[0].length
    val yRange = 0..<input.size

    fun readInput() : Map<Char,List<Vec2D>> {
        val ret = mutableMapOf<Char, MutableList<Vec2D>>()
        for (y in input.indices)
            for (x in input[y].indices) {
                val c = input[y][x]
                if (c != '.')
                    ret.getOrPut(c) { mutableListOf() }.add(Vec2D(x, y))
            }
        return ret
    }

    fun part1() {
        val antinodes = mutableSetOf<Vec2D>()
        for(antenna in antennae.values) {
            for ((first,second) in allPairSequence(antenna)){
                val d = second - first // Get the vector P1->P0
                val antinode1 = second + d
                val antinode2 = first - d
                if (antinode1.inRange(xRange, yRange))
                    antinodes.add(antinode1)
                if (antinode2.inRange(xRange, yRange))
                    antinodes.add(antinode2)
            }
        }
        println("Part1: Antinodes = ${antinodes.size}")
    }

    fun part2() {
        val antinodes = mutableSetOf<Vec2D>()
        for(antenna in antennae.values) {
            for ((first,second) in allPairSequence(antenna)){
                val d = second - first // Get the vector P1->P0
                for(l in -60..60) {
                    val antinode = first + d * l
                    if (antinode.inRange(xRange, yRange))
                        antinodes.add(antinode)
                }
            }
        }
        print("Part2: Antinodes = ${antinodes.size}")
    }

    fun run() {
        part1()
        part2()
    }


}