fun main() {
    fun part1(input: List<String>): Int {

        val (maxRow, maxCol) = listOf(input.lastIndex, input[0].lastIndex)

        fun countScore(r: Int, c: Int): Int {

            fun one(t: Boolean): Int = if (t) 1 else 0

            var res = 0
            if (r > 0 && c > 0) res += one(input[r - 1][c - 1] == '@')
            if (c > 0) res += one(input[r][c - 1] == '@')
            if (r < maxRow && c > 0) res += one(input[r + 1][c - 1] == '@')
            if (r > 0) res += one(input[r - 1][c] == '@')

            if (r < maxRow) res += one(input[r + 1][c] == '@')
            if (r > 0 && c < maxCol) res += one(input[r - 1][c + 1] == '@')
            if (c < maxCol) res += one(input[r][c + 1] == '@')
            if (r < maxRow && c < maxCol) res += one(input[r + 1][c + 1] == '@')

            return res
        }

        var sum = 0
        for (r in 0..maxRow)
            for (c in 0..maxCol)
                if (input[r][c] == '@') {

                    sum += if (countScore(r, c) < 4) 1 else 0
                }

        return sum
    }

    fun part2(input2: List<String>): Int {
        val input = input2.map { it.toMutableList() }

        val (maxRow, maxCol) = listOf(input.lastIndex, input[0].lastIndex)

        fun countScore(r: Int, c: Int): Int {

            fun one(t: Boolean): Int = if (t) 1 else 0

            var res = 0
            if (r > 0 && c > 0) res += one(input[r - 1][c - 1] == '@')
            if (c > 0) res += one(input[r][c - 1] == '@')
            if (r < maxRow && c > 0) res += one(input[r + 1][c - 1] == '@')
            if (r > 0) res += one(input[r - 1][c] == '@')

            if (r < maxRow) res += one(input[r + 1][c] == '@')
            if (r > 0 && c < maxCol) res += one(input[r - 1][c + 1] == '@')
            if (c < maxCol) res += one(input[r][c + 1] == '@')
            if (r < maxRow && c < maxCol) res += one(input[r + 1][c + 1] == '@')

            return res
        }

        var sum = 0
        do {
            var removed = false
            for (r in 0..maxRow)
                for (c in 0..maxCol)
                    if (input[r][c] == '@') {

                        if (countScore(r, c) < 4) {
                            input[r][c] = 'x'
                            removed = true
                            sum++
                        }
                    }
        } while (removed)

        return sum
    }

    // Test if implementation meets criteria from the description, like:
    //check(part1(listOf("test_input")) == 1)

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day04_test")
    //check(part1(testInput).println("Check 1 =") == 13)
    check(part2(testInput).println("Check 2 =") == 43)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day04")
    //part1(input).println()
    part2(input).println()
}
