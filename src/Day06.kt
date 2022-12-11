fun main() {
    fun String.containsDuplicate(): Boolean {
        val duplicates = mutableListOf<Char>()
        forEach {
            if (duplicates.contains(it)) {
                return true
            }
            duplicates.add(it)
        }
        return false
    }

    fun String.solution(distinctCharacters:Int): Int {
        var marker = distinctCharacters
        for (s in windowed(distinctCharacters, 1)) {
            if (s.containsDuplicate()) {
                marker++
            } else {
                break
            }
        }
        return marker
    }

    fun part1(input: List<String>): Int {
        return input.first().solution(4)
    }

    fun part2(input: List<String>): Int {
        return input.first().solution(14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}
