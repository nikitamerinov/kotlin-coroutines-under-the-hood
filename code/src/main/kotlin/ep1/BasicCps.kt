package ep1

fun main() {
    normal()
    println("===")
    cps {}
}

fun normal() {
    val x1 = addOne(0)
    println(x1)
    val x2 = addTwo(x1)
    println(x2)
}

fun addOne(n: Int): Int {
    return n + 1
}

fun addTwo(n: Int): Int {
    return n + 2
}

fun cps(onComplete: () -> Unit) {
    addOneCps(0) { x1 ->
        println(x1)
        addTwoCps(x1) { x2 ->
            println(x2)
            onComplete()
        }
    }
}

fun addOneCps(n: Int, onComplete: (Int) -> Unit) {
    onComplete(n + 1)
}

fun addTwoCps(n: Int, onComplete: (Int) -> Unit) {
    onComplete(n + 2)
}
