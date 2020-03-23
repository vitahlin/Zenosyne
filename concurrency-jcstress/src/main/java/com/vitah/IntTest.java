package com.vitah;

import org.openjdk.jcstress.annotations.Actor;
import org.openjdk.jcstress.annotations.JCStressTest;
import org.openjdk.jcstress.annotations.Outcome;
import org.openjdk.jcstress.annotations.State;
import org.openjdk.jcstress.infra.results.II_Result;

import static org.openjdk.jcstress.annotations.Expect.ACCEPTABLE;

/**
 * 测试int类型的并发情况
 *
 * @author vitah
 */
@JCStressTest
@Outcome(id = {"1, 1", "1, 2", "2, 1", "2, 2"}, expect = ACCEPTABLE, desc = "One update lost.")
@State
public class IntTest {

    private int x = 0;

    @Actor
    public void actor1(II_Result r) {
        x++;
        r.r1 = x;
    }

    @Actor
    public void actor2(II_Result r) {
        x++;
        r.r2 = x;
    }
}
