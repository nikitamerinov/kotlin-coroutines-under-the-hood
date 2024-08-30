package future.a

import kotlinx.coroutines.*
import kotlinx.coroutines.future.await
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import kotlin.coroutines.*
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlin.coroutines.intrinsics.COROUTINE_SUSPENDED

@OptIn(ExperimentalCoroutinesApi::class, DelicateCoroutinesApi::class)
suspend fun main() {
    suspendCoroutine { continuation ->
        continuation.resume(Unit)
        continuation.resume(Unit)
        continuation.resume(Unit)
    }
    println(1)
}


suspend fun test() {
    println(1)
    test2()
    println(2)
}

suspend fun test2() {
    println(21)
    delay(1)
    println(22)
}
