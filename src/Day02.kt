import kotlin.text.split

fun main() {
    fun part1(input: List<String>): Long {

        val regex = "(\\d+)-(\\d+)".toRegex()

        return input.sumOf { range ->
            val (a, b) = regex.find(range)!!.destructured
            val n = a.length - a.length / 2
            val from = "0$a".dropLast(n).toLong()   // add 0 for one-digit number to prevent exception
            val to = b.dropLast(n).toLong()
            (from..to).sumOf {
                val invalidID = (it.toString() + it.toString()).toLong()
                if (invalidID in a.toLong()..b.toLong()) invalidID else 0
            }
        }
    }

    fun part2(input: List<String>): Long {

        val regex = "(\\d+)-(\\d+)".toRegex()

        return input.sumOf { range ->
            val (a, b) = regex.find(range)!!.destructured
            val n = a.length - a.length / 2
            val from = "0$a".dropLast(n).toLong()   // add 0 for one-digit number to prevent exception
            val to = b.dropLast(n).toLong()
            val set = mutableSetOf<Long>()
            (from..to).forEach { half ->
                val halfId = half.toString()
                l@for (digits in 1..half.toString().length) {
                    val part = halfId.take(digits)
                    for (times in 1..12) {
                        try {
                            val invalidID = (part.repeat(times)).toLong()
                            if (invalidID in a.toLong()..b.toLong()) {
                                set += invalidID
                                //break@l
                            }
                        } catch (_: NumberFormatException) {
                            break
                        }
                    }
                }
            }
            set.sum()
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readWholeInput("Day02_test")
    check(part1(testInput.split(",")).println("Check 1 =") == 1227775554L)
    check(part2(testInput.split(",")).println("Check 2 =") == 4174379265L)

    // Read the input from the `src/Day01.txt` file.
    val input = readWholeInput("Day02")
    //part1(input.split(",")).println()  // 24747430309
    part2(input.split(",")).println()  // 30962646823, 25416297736 too low, 26974767433 too low
}
