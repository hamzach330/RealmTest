package com.example.hamzamasood.realmtest.businesslogic;

import android.util.Base64;

import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;

import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;


public final class PubKeyManager implements X509TrustManager {

    private String publicKey;

    public PubKeyManager(String publicKey) {
        this.publicKey = publicKey;
    }

    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain == null) {
            throw new IllegalArgumentException(
                    "checkServerTrusted: X509Certificate array is null");
        }
        if (!(chain.length > 0)) {
            throw new IllegalArgumentException(
                    "checkServerTrusted: X509Certificate is empty");
        }
        TrustManagerFactory tmf;
        try {
            tmf = TrustManagerFactory.getInstance("X509");
            tmf.init((KeyStore) null);

            /*for (TrustManager trustManager : tmf.getTrustManagers()) {
                ((X509TrustManager) trustManager).checkServerTrusted(
                        chain, authType);
            }*/

        } catch (Exception e) {
            //throw new CertificateException(e.toString());
        }

        boolean expected = false;
        for (X509Certificate key : chain) {
            RSAPublicKey pubkey = (RSAPublicKey) key.getPublicKey();
            expected = publicKey.equalsIgnoreCase(Base64.encodeToString(pubkey.getEncoded(), Base64.NO_WRAP));
            if (expected)
                break;

        }
        if (!expected) {
            throw new CertificateException("Not trusted");
        }
    }

    public void checkClientTrusted(X509Certificate[] xcs, String string) {
    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

}