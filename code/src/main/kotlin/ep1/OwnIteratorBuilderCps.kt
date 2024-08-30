package ep1

fun main() {
    val ownIterator = ownIterator { onComplete ->
        println("before 1")
        yield(1) {
            println("before 2")
            yield(2) {
                println("no more")
                onComplete()
            }
        }
    }

    while (ownIterator.hasNext()) {
        println(ownIterator.next())
    }
}

private typealias OwnContinuation = () -> Unit

private fun <T> ownIterator(block: OwnIteratorScope<T>.(OwnContinuation) -> Unit): Iterator<T> {
    val iterator = OwnIteratorScope<T>()
    iterator.continuation = { iterator.block(iterator) }
    return iterator
}

private class OwnIteratorScope<T> : AbstractIterator<T>(), OwnContinuation {
    private var isDone = false
    private var value: T? = null
    var continuation: OwnContinuation? = null

    fun yield(value: T, continuation: OwnContinuation) {
        this.value = value
        this.continuation = continuation
    }

    override fun computeNext() {
        continuation!!()

        if (!isDone) {
            @Suppress("UNCHECKED_CAST")
            setNext(value as T)
        }
    }

    override fun invoke() {
        done()
        isDone = true
    }
}
