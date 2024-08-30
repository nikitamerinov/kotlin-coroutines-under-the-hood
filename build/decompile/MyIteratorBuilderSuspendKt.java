/*
 * Decompiled with CFR 0.150.
 */
package ep3;

import ep3.MyIteratorScope;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function2;

@Metadata(mv={2, 0, 0}, k=2, xi=48, d1={"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001aH\u0010\u0002\u001a\b\u0012\u0004\u0012\u0002H\u00040\u0003\"\u0004\b\u0000\u0010\u00042-\u0010\u0005\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u0007\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0006\u00a2\u0006\u0002\b\nH\u0002\u00a2\u0006\u0002\u0010\u000b\u00a8\u0006\f"}, d2={"main", "", "myIterator", "", "T", "block", "Lkotlin/Function2;", "Lep3/MyIteratorScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Ljava/util/Iterator;", "code"})
public final class MyIteratorBuilderSuspendKt {
    public static final void main() {
        Iterator myIterator2 = MyIteratorBuilderSuspendKt.myIterator((Function2)new Function2<MyIteratorScope<Integer>, Continuation<? super Unit>, Object>(null){
            int label;
            private /* synthetic */ Object L$0;

            /*
             * Unable to fully structure code
             * Enabled aggressive block sorting
             * Lifted jumps to return sites
             */
            public final Object invokeSuspend(Object var1_1) {
                var3_2 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                switch (this.label) {
                    case 0: {
                        ResultKt.throwOnFailure(var1_1);
                        $this$myIterator = (MyIteratorScope)this.L$0;
                        System.out.println((Object)"before 1");
                        this.L$0 = $this$myIterator;
                        this.label = 1;
                        v0 = $this$myIterator.yield(Boxing.boxInt(1), this);
                        if (v0 == var3_2) {
                            return var3_2;
                        }
                        ** GOTO lbl17
                    }
                    case 1: {
                        $this$myIterator = (MyIteratorScope)this.L$0;
                        ResultKt.throwOnFailure($result);
                        v0 = $result;
lbl17:
                        // 2 sources

                        System.out.println((Object)"before 2");
                        this.L$0 = null;
                        this.label = 2;
                        v1 = $this$myIterator.yield(Boxing.boxInt(2), this);
                        if (v1 == var3_2) {
                            return var3_2;
                        }
                        ** GOTO lbl27
                    }
                    case 2: {
                        ResultKt.throwOnFailure($result);
                        v1 = $result;
lbl27:
                        // 2 sources

                        System.out.println((Object)"no more");
                        return Unit.INSTANCE;
                    }
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }

            public final Continuation<Unit> create(Object value, Continuation<?> $completion) {
                var var3_3 = new /* invalid duplicate definition of identical inner class */;
                var3_3.L$0 = value;
                return var3_3;
            }

            public final Object invoke(MyIteratorScope<Integer> p1, Continuation<? super Unit> p2) {
                return (this.create(p1, p2)).invokeSuspend(Unit.INSTANCE);
            }
        });
        int bl = ((Number)myIterator2.next()).intValue();
        System.out.println(bl);
        int n = ((Number)myIterator2.next()).intValue();
        System.out.println(n);
        boolean bl2 = myIterator2.hasNext();
        System.out.println(bl2);
    }

    private static final <T> Iterator<T> myIterator(Function2<? super MyIteratorScope<T>, ? super Continuation<? super Unit>, ? extends Object> block) {
        MyIteratorScope iterator = new MyIteratorScope();
        iterator.setContinuation(IntrinsicsKt.createCoroutineUnintercepted(block, iterator, iterator));
        return iterator;
    }

    public static /* synthetic */ void main(String[] args) {
        MyIteratorBuilderSuspendKt.main();
    }
}
