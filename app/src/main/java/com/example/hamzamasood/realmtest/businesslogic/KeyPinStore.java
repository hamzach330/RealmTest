package com.example.hamzamasood.realmtest.businesslogic;

import android.content.Context;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/**
 * Created by Ricardo Iramar dos Santos on 14/08/2015.
 */
public class KeyPinStore {
    public static boolean isPKR = false;
    private static TrustManager[] trustManagers = null;
    public String PUBLIC_KEY = "";
    private SSLContext sslContext = null;

    public KeyPinStore(Context context) throws Exception {
        sslContext = SSLContext.getInstance("TLSv1");
        //PUBLIC_KEY = Objects.sslPinning.SSL_KEY;
        if (false) {
            TrustManager[] tmf = pinnedTrustManager();
            sslContext.init(null, tmf, null);
        } else {
            if (trustManagers == null) {
                trustManagers = new TrustManager[]{new _FakeX509TrustManager()};
            }
            HttpsURLConnection.setDefaultHostnameVerifier(new NoHostName());
            sslContext.init(null, trustManagers, null);
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }

    }

    private TrustManager[] pinnedTrustManager() throws Exception {
        TrustManager tm[] = {new PubKeyManager(PUBLIC_KEY)};
        return tm;
    }

    public SSLContext getContext() {
        return sslContext;
    }

    private static class _FakeX509TrustManager implements javax.net.ssl.X509TrustManager {
        private static final X509Certificate[] _AcceptedIssuers = new X509Certificate[]{};

        public void checkClientTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] arg0, String arg1)
                throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return (_AcceptedIssuers);
        }

    }

}
