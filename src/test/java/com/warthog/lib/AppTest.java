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
        var acc = Account.fromPk(
            "966a71a98bb5d13e9116c0dffa3f1a7877e45c6f563897b96cfd5c59bf0803e0"
        );
        var addr = "3661579d61abde5837a8686dc4d65348a2fc61b1fe5f4093";
        assertTrue(addr.equals(acc.address));
    }
}
