package com.warthog.lib;

import java.nio.ByteBuffer;
import org.bouncycastle.util.encoders.Hex;

public class Transaction {

    String pinHash;
    int pinHeight;
    int nonceId;
    int amountE8;
    int feeE8;
    String to;

    // TODO : Maybe add more check ??
    public Transaction() {
        if (this.pinHash.length() != 64 && this.pinHash == null) {
            throw new HandledException(
                "BAD_PINHASH",
                "pinHash haven't an length of 64"
            );
        }
    }

    public static byte[] convertIntToByteArray(int value, int size) {
        return ByteBuffer.allocate(size).putInt(value).array();
    }

    public byte[] toHex() {
        var toc = ByteBuffer.wrap(Hex.decode(this.to)).slice(0, 20);
        ByteBuffer tx = ByteBuffer.wrap(Hex.decode(this.pinHash));
        tx.put(convertIntToByteArray(pinHeight, 4));
        tx.put(convertIntToByteArray(nonceId, 4));
        tx.put(new byte[] { 0, 0, 0, 0 });
        tx.put(convertIntToByteArray(feeE8, 8));
        tx.put(toc);
        tx.put(convertIntToByteArray(amountE8, 8));
        return tx.array();
    }
}
