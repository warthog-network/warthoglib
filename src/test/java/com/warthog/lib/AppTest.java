package com.warthog.lib;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.bouncycastle.util.encoders.Hex;
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

    @Test
    public void TestTxBuilding() {
        /*
        pinHash = "35af90b5c89e176bde16260db20a57dde97392c1d2bf89474ca63f8cd45aa4ad"
        pinHeight = 3643776
        nonceId = 0
        feeE8 = 9992
        toAddr = "0000000000000000000000000000000000000000de47c9b2"
        amountE8 = 100000000
        byte = b"5\xaf\x90\xb5\xc8\x9e\x17k\xde\x16&\r\xb2\nW\xdd\xe9s\x92\xc1\xd2\xbf\x89GL\xa6?\x8c\xd4Z\xa4\xad\x007\x99\x80\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00'\x08\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x00\x05\xf5\xe1\x00"
        enco = "35af90b5c89e176bde16260db20a57dde97392c1d2bf89474ca63f8cd45aa4ad0037998000000000000000000000000000270800000000000000000000000000000000000000000000000005f5e100"
        enco2= "35af90b5c89e176bde16260db20a57dde97392c1d2bf89474ca63f8cd45aa4ad00379980000000000000000000000000000000000000000000000000000000de47c9b2000000000000270800000000000000000000000000000000000000000000000005f5e10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000
        */
        var pyver =
            "35af90b5c89e176bde16260db20a57dde97392c1d2bf89474ca63f8cd45aa4ad0037998000000000000000000000000000270800000000000000000000000000000000000000000000000005f5e100";
        var tx = new Transaction();
        tx.pinHash =
            "35af90b5c89e176bde16260db20a57dde97392c1d2bf89474ca63f8cd45aa4ad";
        tx.pinHeight = 3643776;
        tx.nonceId = 0;
        tx.feeE8 = 9992;
        tx.to = "0000000000000000000000000000000000000000de47c9b2";
        tx.amountE8 = 100000000;

        var finishedTx = Hex.toHexString(tx.toArray());
        assertTrue(pyver.equals(finishedTx));
    }
}
