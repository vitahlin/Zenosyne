package com.vitah;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

import java.util.concurrent.atomic.AtomicInteger;

import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE;

/**
 * 测试int类型的并发情况
 *
 * @author vitah
 */
@JCStressTest
@Outcome(id = {"2, 0", "1, 0", "3, 0"}, expect = ACCEPTABLE)
@State
public class AtomicIntegerTest {

    private AtomicInteger x = new AtomicInteger(0);

    @Actor
    public void actor1(II_Result r) {
        r.r1 = x.addAndGet(2);
    }

    @Actor
    public void actor2(II_Result r) {
        r.r1 = x.addAndGet(1);
    }
}
