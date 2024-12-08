package falcon
import java.io.File

class Day2 {

    fun List<Int>.isSafe():Boolean {
        val differences = windowed(2).map { it[1] - it[0] }
        return differences.all{it>0 && it<=3} || differences.all { it<0 && it>=-3}
    }

    fun List<Int>.isSafeWithDampener():Boolean {
        if (isSafe())
            return true;

        for(index in indices) {
            val withoutIndex = filterIndexed { i,_ -> i!=index }
            if (withoutIndex.isSafe())
                return true
        }
        return false
    }


    fun run() {
        // Part 1
        val input = File("src/main/resources/InputDay2.txt").readLines().map { it.split(" ").map { it.toInt() } }
        var count = input.count{ it.isSafe()}
        println("Number of safe = $count")

        // Part 2
        val count2 = input.count { it.isSafeWithDampener() }
        println("Part 2: Number of safe = $count2")
    }
}