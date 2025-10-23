package com.warthog.lib;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bouncycastle.util.encoders.Hex;

public class Transaction {

    String pinHash;
    int pinHeight;
    int nonceId;
    long amountE8;
    long feeE8;
    String to;

    // TODO : Maybe add more check ??
    /*
    public Transaction() {
        if (this.pinHash.length() != 64 && this.pinHash != null) {
            throw new HandledException(
                "BAD_PINHASH",
                "pinHash haven't an length of 64"
            );
        }
    }
*/

    public byte[] toArray() {
        byte[] pinHashBytes = Hex.decode(this.pinHash);
        byte[] toAddrBytes = Hex.decode(this.to);
        int totalSize = pinHashBytes.length + 4 + 4 + 3 + 8 + 20 + 8;
        ByteBuffer tx = ByteBuffer.allocate(totalSize);
        tx.order(ByteOrder.BIG_ENDIAN);
        tx.put(pinHashBytes);
        tx.putInt(pinHeight);
        tx.putInt(nonceId);
        tx.put((byte) 0);
        tx.put((byte) 0);
        tx.put((byte) 0);
        tx.putLong(feeE8);
        tx.put(toAddrBytes, 0, 20);
        tx.putLong(amountE8);
        return tx.array();
    }
}
