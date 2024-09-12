package ep3

import ep2.myIterator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.debug.DebugProbes

@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    DebugProbes.install()
    DebugProbes.ignoreCoroutinesWithEmptyContext = false

    val iterator = myIterator {
        println("before yield " + DebugProbes.dumpCoroutinesInfo())
        yield(1)
        println("no more " + DebugProbes.dumpCoroutinesInfo())
    }

    println("before iterator start " + DebugProbes.dumpCoroutinesInfo())
    while (iterator.hasNext()) {
        println("before calling next " + DebugProbes.dumpCoroutinesInfo())
        iterator.next()
        println("after calling next " + DebugProbes.dumpCoroutinesInfo())
    }
    println("after iterator end " + DebugProbes.dumpCoroutinesInfo())
}
