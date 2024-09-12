package ep4

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.startCoroutineUninterceptedOrReturn
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume

fun main() {
    val sum = MyDeepRecursiveFunction<List<Int>, Int> { list ->
        if (list.isEmpty()) {
            return@MyDeepRecursiveFunction 0
        }

        val sumRest = callRecursive(list.subList(1, list.size))
        return@MyDeepRecursiveFunction list[0] + sumRest
    }

    println(sum(listOf(1, 2, 3)))
}


class MyDeepRecursiveFunctionScope<T, R>(
    private val block: suspend MyDeepRecursiveFunctionScope<T, R>.(T) -> R,
    private var param: T
): Continuation<R> {
    private var continuation: Continuation<R> = this
    private var finalResult: R? = null

    suspend fun callRecursive(param: T): R {
        this.param = param
        return suspendCoroutineUninterceptedOrReturn { continuation ->
            this.continuation = continuation
            COROUTINE_SUSPENDED
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun runCallLoop(): R {
        while (true) {
            val callResult = block.startCoroutineUninterceptedOrReturn(this, param, continuation)
            if (callResult !== COROUTINE_SUSPENDED) {
                continuation.resume(callResult as R)
                return finalResult as R
            }
        }
    }

    override fun resumeWith(result: Result<R>) {
        finalResult = result.getOrThrow()
    }

    override val context: CoroutineContext = EmptyCoroutineContext
}

class MyDeepRecursiveFunction<T, R>(private val block: suspend MyDeepRecursiveFunctionScope<T, R>.(T) -> R) {
    operator fun invoke(param: T): R {
        return MyDeepRecursiveFunctionScope(block, param).runCallLoop()
    }
}

fun <R, P, T> (suspend R.(P) -> T).startCoroutineUninterceptedOrReturn(
    receiver: R, param: P, completion: Continuation<T>
): Any? {
    val block: (suspend R.() -> T) = { this@startCoroutineUninterceptedOrReturn(this, param) }
    return block.startCoroutineUninterceptedOrReturn(receiver, completion)
}
