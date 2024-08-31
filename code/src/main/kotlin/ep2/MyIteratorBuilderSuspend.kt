package ep2

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.createCoroutineUnintercepted
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume

fun main() {
    val ownIterator = ownIterator {
        println("before 1")
        yield(1)
        println("before 2")
        yield(2)
        println("no more")
    }

    while (ownIterator.hasNext()) {
        println(ownIterator.next())
    }
}

private fun <T> ownIterator(block: suspend OwnIteratorScope<T>.() -> Unit): Iterator<T> {
    val iterator = OwnIteratorScope<T>()
    iterator.continuation = block.createCoroutineUnintercepted(receiver = iterator, completion = iterator)
    return iterator
}

private class OwnIteratorScope<T> : AbstractIterator<T>(), Continuation<Unit> {
    private var isDone: Boolean = false
    private var value: T? = null
    var continuation: Continuation<Unit>? = null

    suspend fun yield(value: T) {
        return suspendCoroutineUninterceptedOrReturn { continuation ->
            this.value = value
            this.continuation = continuation
            COROUTINE_SUSPENDED
        }
    }

    override fun computeNext() {
        continuation!!.resume(Unit)

        if (!isDone) {
            @Suppress("UNCHECKED_CAST")
            setNext(value as T)
        }
    }

    override fun resumeWith(result: Result<Unit>) {
        done()
        isDone = true
    }

    override val context: CoroutineContext = EmptyCoroutineContext
}
