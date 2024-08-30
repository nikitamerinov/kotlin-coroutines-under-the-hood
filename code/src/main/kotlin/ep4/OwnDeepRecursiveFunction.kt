@file:OptIn(ExperimentalCoroutinesApi::class)

package ep4

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.intrinsics.startCoroutineUninterceptedOrReturn
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED
import kotlin.coroutines.resume

fun main() {
    val referenceSum = DeepRecursiveFunction<List<Int>, Int> { list ->
        if (list.isEmpty()) {
            return@DeepRecursiveFunction 0
        }

        val sumRest = callRecursive(list.subList(1, list.size))
        return@DeepRecursiveFunction list[0] + sumRest
    }

    println(referenceSum(listOf(1, 2, 3)))

    println("===")

    val sum = OwnDeepRecursiveFunction<List<Int>, Int> { list ->
        println("list: $list")
        if (list.isEmpty()) {
            return@OwnDeepRecursiveFunction 0
        }

        val sumRest = callRecursive(list.subList(1, list.size))
        return@OwnDeepRecursiveFunction list[0] + sumRest
    }

    println(sum(listOf(1, 2, 3)))
}

class OwnDeepRecursiveFunctionScope<T, R>(val block: suspend OwnDeepRecursiveFunctionScope<T, R>.(T) -> R) {
    private var continuation: Continuation<R>? = null
    private var param: T? = null

    suspend fun callRecursive(param: T): R {
        this.param = param
        return suspendCoroutineUninterceptedOrReturn { continuation ->
            this.continuation = continuation
            COROUTINE_SUSPENDED
        }
    }

    private fun runLoop(param: T): R {
        this.param = param
        var final: R? = null
        val cont = if (continuation == null) Continuation(EmptyCoroutineContext){ r -> final = r.getOrThrow() } else continuation!!
        block.startCoroutineUninterceptedOrReturn(this, this.param!!, cont)
        block.startCoroutineUninterceptedOrReturn(this, this.param!!, this.continuation!!)
        block.startCoroutineUninterceptedOrReturn(this, this.param!!, this.continuation!!)
        val r = block.startCoroutineUninterceptedOrReturn(this, this.param!!, this.continuation!!)
//        println(r)
        this.continuation!!.resume(r as R)
        return final as R
    }

    operator fun invoke(param: T): R {
        return runLoop(param)
    }
}

fun <T, R> OwnDeepRecursiveFunction(block: suspend OwnDeepRecursiveFunctionScope<T, R>.(T) -> R): OwnDeepRecursiveFunctionScope<T, R> {
    val scope = OwnDeepRecursiveFunctionScope<T, R>(block)
    return scope
}

fun <R, P, T> (suspend R.(P) -> T).startCoroutineUninterceptedOrReturn(
    receiver: R, param: P, completion: Continuation<T>
): Any? {
    val f: (suspend R.() -> T) = { this@startCoroutineUninterceptedOrReturn(this, param) }
    return f.startCoroutineUninterceptedOrReturn(receiver, completion)
}
