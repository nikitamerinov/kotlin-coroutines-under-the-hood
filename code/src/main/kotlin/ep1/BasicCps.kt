package ep1

fun main() {
    normal()
    cps {}
}

private fun normal() {
    val x1 = addOne(0)
    println(x1)
    val x2 = addTwo(x1)
    println(x2)
}

private fun addOne(n: Int): Int {
    return n + 1
}

private fun addTwo(n: Int): Int {
    return n + 2
}

private fun cps(onComplete: () -> Unit) {
    addOneCps(0) { x1 ->
        println(x1)
        addTwoCps(x1) { x2 ->
            println(x2)
            onComplete()
        }
    }
}

private fun addOneCps(n: Int, onComplete: (Int) -> Unit) {
    onComplete(n + 1)
}

private fun addTwoCps(n: Int, onComplete: (Int) -> Unit) {
    onComplete(n + 2)
}
