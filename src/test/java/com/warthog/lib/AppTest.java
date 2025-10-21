package com.warthog.lib;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    @Test
    public void TestAccount() {
        var acc = Account.genKeyPair();
        var acc2 = Account.fromPk(acc.getPk());
        System.err.println(acc.getPk());
        System.err.println(acc2.getPk());

        assertTrue(acc.getPk() == acc2.getPk());
    }
}
