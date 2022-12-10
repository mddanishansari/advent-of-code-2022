fun main() {
    val lowerCaseRange = 'a'..'z'
    fun Char.priority(): Int {
        if (this in lowerCaseRange)
            return code - 96
        return code - 38
    }

    fun part1(input: List<String>): Int {
        var priorities = 0
        input.forEach {
            val (firstHalf, secondHalf) = it.chunked(it.count() / 2)
            val common = firstHalf.toSet().intersect(secondHalf.toSet()).first()
            priorities += common.priority()
        }
        return priorities
    }

    fun part2(input: List<String>): Int {
        var priorities = 0
        input.chunked(3).forEach {
            val first = it[0]
            val second = it[1]
            val third = it[2]
            val common = first.toSet().intersect(second.toSet()).intersect(third.toSet()).first()
            priorities += common.priority()
        }
        return priorities
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
