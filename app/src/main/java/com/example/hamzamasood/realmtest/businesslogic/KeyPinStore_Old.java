package com.example.hamzamasood.realmtest.businesslogic;

import android.content.Context;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by Ricardo Iramar dos Santos on 14/08/2015.
 */
public class KeyPinStore_Old {

    private static KeyPinStore_Old instance = null;
    private SSLContext sslContext = SSLContext.getInstance("TLSv1");

    public static synchronized KeyPinStore_Old getInstance(Context context) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        if (instance == null) {
            instance = new KeyPinStore_Old(context);
        }
        return instance;
    }

    public KeyPinStore_Old(Context context) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
        sslContext = SSLContext.getInstance("TLSv1");
        // https://developer.android.com/training/articles/security-ssl.html
        // Load CAs from an InputStream
        // (could be from a resource or ByteArrayInputStream or ...)
        CertificateFactory cf = CertificateFactory.getInstance("X.509");
        // randomCA.crt should be in the Assets directory (tip from here http://littlesvr.ca/grumble/2014/07/21/android-programming-connect-to-an-https-server-with-self-signed-certificate/)
        InputStream caInput = new BufferedInputStream(context.getAssets().open("ubl_prd_20170309.cer"));
        //InputStream caInput = new BufferedInputStream(context.getAssets().open("cert.cer"));

        Certificate ca;
        try {
            ca = cf.generateCertificate(caInput);
            System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
        } finally {
            caInput.close();
        }

        // Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(null, null);
        keyStore.setCertificateEntry("ca", ca);

        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
        tmf.init(keyStore);

        // Create an SSLContext that uses our TrustManager
        // SSLContext context = SSLContext.getInstance("TLS");

        sslContext.init(null, tmf.getTrustManagers(), null);

    }

    public SSLContext getContext() {
        return sslContext;
    }
}
