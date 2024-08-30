package ep3

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.debug.DebugProbes

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    DebugProbes.install()
    DebugProbes.ignoreCoroutinesWithEmptyContext = false

    val iterator = iterator {
        println("2: " + DebugProbes.dumpCoroutinesInfo())
        yield(1)
    }
    println("1: " + DebugProbes.dumpCoroutinesInfo())
    iterator.next()
    println("3: " + DebugProbes.dumpCoroutinesInfo())
    iterator.hasNext()
    println("4: " + DebugProbes.dumpCoroutinesInfo())
}
