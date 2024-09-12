package ep4

fun main() {
    val sum = DeepRecursiveFunction<List<Int>, Int> { list ->
        if (list.isEmpty()) {
            return@DeepRecursiveFunction 0
        }

        val sumRest = callRecursive(list.subList(1, list.size))
        return@DeepRecursiveFunction list[0] + sumRest
    }

    println(sum(listOf(1, 2, 3)))
}
