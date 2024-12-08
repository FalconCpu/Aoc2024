package falcon
import java.io.File
import kotlin.math.abs

fun day1() {
    val file = "src/main/resources/InputDay1.txt"
    val lines = File(file).readLines()
    val regex = Regex("\\s+")
    val list1 = mutableListOf<Long>()
    val list2 = mutableListOf<Long>()
    for(line in lines) {
        val words = line.split(regex)
        list1 += words[0].toLong()
        list2 += words[1].toLong()
    }

    list1.sort()
    list2.sort()

    var diff = 0L
    for (i in list1.indices)
        diff += abs(list1[i].toLong() - list2[i].toLong())
    println("Total difference = $diff")


    // Part 2
    var similarity = 0L
    for (i in list1.indices) {
        val count = list2.count { it == list1[i] }
        similarity += list1[i] * count
    }
    println("Total similarity = $similarity")

}
