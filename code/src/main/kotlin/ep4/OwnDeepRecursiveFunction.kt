package ep4

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.intrinsics.startCoroutineUninterceptedOrReturn
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.resume

fun main() {
    val sum = OwnDeepRecursiveFunction<List<Int>, Int> { list ->
        if (list.isEmpty()) {
            return@OwnDeepRecursiveFunction 0
        }

        val sumRest = callRecursive(list.subList(1, list.size))
        return@OwnDeepRecursiveFunction list[0] + sumRest
    }

    println(sum(listOf(1, 2, 3)))
}


private class OwnDeepRecursiveFunctionScope<T, R>(
    private val block: suspend OwnDeepRecursiveFunctionScope<T, R>.(T) -> R,
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

    override val context: CoroutineContext = EmptyCoroutineContext

    override fun resumeWith(result: Result<R>) {
        finalResult = result.getOrThrow()
    }
}

private class OwnDeepRecursiveFunction<T, R>(private val block: suspend OwnDeepRecursiveFunctionScope<T, R>.(T) -> R) {
    operator fun invoke(param: T): R {
        return OwnDeepRecursiveFunctionScope(block, param).runCallLoop()
    }
}

private fun <R, P, T> (suspend R.(P) -> T).startCoroutineUninterceptedOrReturn(
    receiver: R, param: P, completion: Continuation<T>
): Any? {
    val f: (suspend R.() -> T) = { this@startCoroutineUninterceptedOrReturn(this, param) }
    return f.startCoroutineUninterceptedOrReturn(receiver, completion)
}
