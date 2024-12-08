package falcon




val cardinalDirections = listOf(Vec2D(0,-1), Vec2D(1,0), Vec2D(0,1), Vec2D(-1,0)) // NESW

class Day6(val input:List<String>) {

    val initialPosition = findInitialPosition()

    // Get the character at a given position, or '&' if out of bounds
    fun get(pos:Vec2D) : Char {
        if (pos.x < 0 || pos.y < 0 || pos.x >= input[0].length || pos.y >= input.size)
            return '&'
        return input[pos.y][pos.x]
    }

    fun findInitialPosition(): Vec2D {
        for(y in input.indices) {
            val x = input[y].indexOf('^')
            if (x!=-1)
                return Vec2D(x,y)
        }
        error("Cannot find start point")
    }

    fun part1() : Set<Vec2D> {
        var pos = initialPosition
        val path = mutableSetOf<Vec2D>()
        var direction = 0
        path += pos
        while(true) {
            // look at the next square
            val nextPos = pos + cardinalDirections[direction]
            val nextChar = get(nextPos)
            println("At $pos facing $direction. Next square = $nextPos $nextChar")
            when(nextChar) {
                '^', '.' -> {  // move forward
                    pos = nextPos
                    path += pos
                }

                '#' -> { // turn right
                    direction = (direction + 1) % 4
                }

                '&' -> { // exited the area
                    return path
                }
            }
        }
    }

    fun testPath(extraObs:Vec2D) : Int{
        var pos = initialPosition
        var facing = 0
        var timeout = 0
        while(true) {
            // look at the next square
            val nextPos = pos.plus(cardinalDirections[facing])
            if (nextPos == extraObs) {
                // Treat as a wall
                facing = (facing + 1) % 4
            } else {
            val nextChar = get(nextPos)
                when(nextChar) {
                    '^', '.' -> {
                        // move forward
                        pos = nextPos
                        timeout++
                        if (timeout == 10000)
                            return 0
                    }

                    '#' -> {
                        // turn right
                        facing = (facing + 1) % 4
                    }

                    '&' -> {
                        // exited the area
                        return timeout
                    }
                }
            }
        }
    }

    fun part2(path1:Set<Vec2D>) : Int {
        var count = 0
        for (p in path1) {
            if (p==initialPosition)
                continue
            val test = testPath(p)
            println("Obstacle position $p resulted in path length $test")
            if (test==0)
                count++
        }

        return count
    }



    fun run() {
        val path = part1()
        println("Part 1: $path.size")
        println("Part 2: ${part2(path)}")
    }


}