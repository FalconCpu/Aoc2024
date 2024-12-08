package falcon
import java.io.File


fun main() {
    val input = "src/main/resources/InputDay8.txt"
    val lines = File(input).readLines()

    Day8(lines).run()
}