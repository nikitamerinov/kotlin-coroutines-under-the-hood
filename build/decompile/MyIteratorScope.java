/*
 * Decompiled with CFR 0.150.
 */
package ep2;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv={2, 0, 0}, k=1, xi=48, d1={"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\f\u0012\u0004\u0012\u00020\u00050\u0004j\u0002`\u0003B\u0007\u00a2\u0006\u0004\b\u0006\u0010\u0007J%\u0010\u0012\u001a\u00020\u00052\u0006\u0010\n\u001a\u00028\u00002\u0010\u0010\f\u001a\f\u0012\u0004\u0012\u00020\u00050\u0004j\u0002`\u0003\u00a2\u0006\u0002\u0010\u0013J\b\u0010\u0014\u001a\u00020\u0005H\u0014J\t\u0010\u0015\u001a\u00020\u0005H\u0096\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u0004\u0018\u00018\u0000X\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000bR*\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004j\u0004\u0018\u0001`\u0003X\u0086\u000e\u00a2\u0006\u0010\n\u0002\u0010\u0011\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010\u00a8\u0006\u0016"}, d2={"Lep2/MyIteratorScope;", "T", "Lkotlin/collections/AbstractIterator;", "Lep2/MyContinuation;", "Lkotlin/Function0;", "", "<init>", "()V", "isDone", "", "value", "Ljava/lang/Object;", "continuation", "getContinuation", "()Lkotlin/jvm/functions/Function0;", "setContinuation", "(Lkotlin/jvm/functions/Function0;)V", "Lkotlin/jvm/functions/Function0;", "yield", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)V", "computeNext", "invoke", "code"})
final class MyIteratorScope<T>
extends AbstractIterator<T>
implements Function0<Unit> {
    private boolean isDone;
    @Nullable
    private T value;
    @Nullable
    private Function0<Unit> continuation;

    @Nullable
    public final Function0<Unit> getContinuation() {
        return this.continuation;
    }

    public final void setContinuation(@Nullable Function0<Unit> function0) {
        this.continuation = function0;
    }

    public final void yield(T value, @NotNull Function0<Unit> continuation) {
        Intrinsics.checkNotNullParameter(continuation, "continuation");
        this.value = value;
        this.continuation = continuation;
    }

    @Override
    protected void computeNext() {
        Function0<Unit> function0 = this.continuation;
        Intrinsics.checkNotNull(function0);
        function0.invoke();
        if (!this.isDone) {
            this.setNext(this.value);
        }
    }

    @Override
    public void invoke() {
        this.done();
        this.isDone = true;
    }
}
