package ep1

fun main() {
    val referenceIterator = iterator {
        println("before 1")
        yield(1)
        println("before 2")
        yield(2)
        println("no more")
    }

    while (referenceIterator.hasNext()) {
        println(referenceIterator.next())
    }
}
