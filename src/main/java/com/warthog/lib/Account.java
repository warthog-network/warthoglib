package com.warthog.lib;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECKeyGenerationParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.util.encoders.Hex;

public class Account {

    private String privateKey;
    public String pubKey;
    public String address;

    public static Account genKeyPair() {
        var curve = ECNamedCurveTable.getParameterSpec("secp256k1");
        var domainParams = new ECDomainParameters(
            curve.getCurve(),
            curve.getG(),
            curve.getN(),
            curve.getH(),
            curve.getSeed()
        );

        var secureRandom = new SecureRandom();
        var keyParams = new ECKeyGenerationParameters(
            domainParams,
            secureRandom
        );

        var generator = new ECKeyPairGenerator();
        generator.init(keyParams);

        var keyPair = generator.generateKeyPair();

        var pubKey = (ECPublicKeyParameters) keyPair.getPublic();
        var priKey = (ECPrivateKeyParameters) keyPair.getPrivate();

        Account account = new Account();
        account.privateKey = Hex.toHexString(priKey.getD().toByteArray());
        account.pubKey = Hex.toHexString(pubKey.getQ().getEncoded(true));
        account.address = account.getAddress();
        return account;
    }

    public String getPk() {
        return this.privateKey;
    }

    private byte[] shaBytes(byte[] arg) {
        try {
            return MessageDigest.getInstance("SHA-256").digest(arg);
        } catch (Exception e) {
            return null;
        }
    }

    private byte[] Ripe(byte[] arg) {
        RIPEMD160Digest digest = new RIPEMD160Digest();
        digest.update(arg, 0, arg.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }

    public String getAddress() {
        byte[] pubKeyBytes = Hex.decode(this.pubKey);
        byte[] sha = shaBytes(pubKeyBytes);
        byte[] addr_raw = Ripe(sha);
        byte[] addr_hash = shaBytes(addr_raw);
        byte[] checksum = new byte[4];
        System.arraycopy(addr_hash, 0, checksum, 0, 4);
        byte[] addr = new byte[addr_raw.length + 4];
        System.arraycopy(addr_raw, 0, addr, 0, addr_raw.length);
        System.arraycopy(checksum, 0, addr, addr_raw.length, 4);
        return Hex.toHexString(addr);
    }

    public static Account fromPk(String privateKey) {
        var curve = ECNamedCurveTable.getParameterSpec("secp256k1");
        var domainParams = new ECDomainParameters(
            curve.getCurve(),
            curve.getG(),
            curve.getN(),
            curve.getH(),
            curve.getSeed()
        );
        byte[] pkB = Hex.decode(privateKey);
        BigInteger d = new BigInteger(1, pkB);

        ECPrivateKeyParameters priKeyParams = new ECPrivateKeyParameters(
            d,
            domainParams
        );

        ECPoint pubpoints = curve.getG().multiply(d).normalize();
        ECPublicKeyParameters pubkeyParams = new ECPublicKeyParameters(
            pubpoints,
            domainParams
        );

        Account account = new Account();
        account.privateKey = privateKey;
        account.pubKey = Hex.toHexString(pubkeyParams.getQ().getEncoded(true));
        account.address = account.getAddress();

        return account;
    }
}
