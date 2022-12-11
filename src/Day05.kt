import java.util.*

fun main() {
    data class Move(val amount: Int, val from: Int, val to: Int)
    data class Input(val stacks: Map<Int, Stack<Char>>, val moves: List<Move>)


    fun MutableMap<Int, Stack<Char>>.insertCrate(stackNumber: Int, crate: Char) {
        if (crate == ' ') return

        if (this[stackNumber] != null) {
            this[stackNumber]?.push(crate)
            return
        }

        this[stackNumber] = Stack<Char>().apply { push(crate) }
    }


    fun List<String>.parseInput(): Input {
        val moves = mutableListOf<Move>()
        val stacks = mutableMapOf<Int, Stack<Char>>()
        val tempStack = mutableListOf<List<Char>>()
        forEach {
            // Read "move X from Y to Z"
            if (it.startsWith("move")) {
                val split = it.split(" ")
                moves.add(Move(amount = split[1].toInt(), from = split[3].toInt(), to = split[5].toInt()))
            } else if (it.contains('[')) {
                tempStack.add(it.toCharArray()
                    // Ignore space, [ and ], reads only crate IDs i.e. A, B, C etc
                    .filterIndexed { index, _ -> (index - 1) % 4 == 0 })
            }
        }

        tempStack.reversed().forEach { chars ->
            chars.forEachIndexed { crateIndex, crate ->
                stacks.insertCrate(crateIndex + 1, crate)
            }
        }

        return Input(stacks = stacks, moves = moves)
    }

    fun Map<Int, Stack<Char>>.answer(): String {
        return buildString {
            entries.forEach {
                append(it.value.peek())
            }
        }
    }

    fun part1(inputLines: List<String>): String {
        val input = inputLines.parseInput()
        input.moves.forEach { move ->
            (1..move.amount).forEach { _ ->
                input.stacks[move.from]?.pop()?.let { movingCrate ->
                    input.stacks[move.to]?.push(movingCrate)
                }
            }
        }
        return input.stacks.answer()
    }

    fun part2(inputLines: List<String>): String {
        val input = inputLines.parseInput()
        input.moves.forEach { move ->
            val removedCrates = mutableListOf<Char>()
            (1..move.amount).forEach { _ ->
                input.stacks[move.from]?.pop()?.let { movingCrate ->
                    removedCrates.add(0, movingCrate)
                }
            }
            removedCrates.forEach {
                input.stacks[move.to]?.push(it)
            }
        }
        return input.stacks.answer()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}
