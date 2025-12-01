fun main() {
    fun part1(input: List<String>): Int {

        var points = 50

        fun pointsAtZeroAtTheEnd(dir: String, n: Int) : Boolean {
            points = when(dir) {
                "L" -> (points - n) % 100
                "R" -> (points + n) % 100
                else -> error("Wrong dir $dir")
            }
            return points == 0
        }

        return input.count { line ->
            pointsAtZeroAtTheEnd(line.take(1), line.drop(1).toInt())
        }
    }

    fun part2(input: List<String>): Int {

        var points = 50

        fun zeroCrosses(dir: String, n: Int) : Int {
            val oldPoints = points
            val turns = n / 100
            val rest = n % 100

            points = when(dir) {
                "L" -> (points - rest).mod(100) // This is always >= 0 (flooring div)
                "R" -> (points + rest) % 100            // The same as previous only for positives
                else -> error("Wrong dir $dir")
            }

            return turns +
                    if (points == 0 && rest != 0) 1 else 0 +
                            if (dir == "R" && rest + oldPoints > 100) 1 else 0 +
                                    if (dir == "L" && rest > oldPoints && oldPoints != 0) 1 else 0

        }

        return input.sumOf { line ->
            zeroCrosses(line.take(1), line.drop(1).toInt())
        }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    //check(part1(testInput).println("Check 1 =") == 3)
    check(part2(testInput).println("Check 2 =") == 6)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()  // 1036
    part2(input).println()  // 6228
}
