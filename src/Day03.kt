fun main() {
    fun part1(input: List<String>): Int {

        var sum = 0
        input.forEach { line ->
            var max = 0
            for (i in 0..line.lastIndex)
                for (j in i + 1..line.lastIndex){
                    val curr = "${line[i]}${line[j]}".toInt()
                    if (curr > max) max = curr
                }
            sum += max
        }
        return sum
    }

    fun part2(input: List<String>): Long {
        var sum = 0L
        input.forEach { line ->
            var from = 0
            var to = line.lastIndex - 11
            var maxJoltage = ""
            repeat(12) {
                var max = '0'
                var maxIndex = from
                for (i in from..to + it) {
                    if (line[i] > max) {
                        max = line[i]
                        maxIndex = i
                    }
                }
                maxJoltage += max
                from = maxIndex + 1
            }
            sum += maxJoltage.toLong()
        }
        return sum
    }

    // Test if implementation meets criteria from the description, like:
    check(part2(listOf("818181911112111")).println("Check test =") == 888911112111)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day03_test")
    //check(part1(testInput).println("Check 1 =") == 357)
    check(part2(testInput).println("Check 2 =") == 3121910778619)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day03")
    //part1(input).println()  // 17535
    part2(input).println()  // 173577199527257
}
