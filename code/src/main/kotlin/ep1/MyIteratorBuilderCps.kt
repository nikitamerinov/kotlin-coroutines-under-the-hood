package ep1

fun main() {
    val iterator = myIterator { onComplete ->
        println("before 1")
        yield(1) {
            println("before 2")
            yield(2) {
                println("no more")
                onComplete()
            }
        }
    }

    while (iterator.hasNext()) {
        println(iterator.next())
    }
}

typealias MyContinuation = () -> Unit

fun <T> myIterator(block: MyIteratorBuilderScope<T>.(MyContinuation) -> Unit): Iterator<T> {
    val iterator = MyIteratorBuilderScope<T>()
    iterator.continuation = { iterator.block(iterator) }
    return iterator
}

class MyIteratorBuilderScope<T> : AbstractIterator<T>(), MyContinuation {
    private var isDone = false
    private var value: T? = null
    var continuation: MyContinuation? = null

    fun yield(value: T, continuation: MyContinuation) {
        this.value = value
        this.continuation = continuation
    }

    override fun computeNext() {
        continuation!!()

        if (isDone) {
            done()
        } else {
            @Suppress("UNCHECKED_CAST")
            setNext(value as T)
        }
    }

    override fun invoke() {
        isDone = true
    }
}
