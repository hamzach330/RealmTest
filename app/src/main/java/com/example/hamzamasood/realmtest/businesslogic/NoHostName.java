package com.example.hamzamasood.realmtest.businesslogic;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class NoHostName implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
//        Log.i("RestUtilImpl", "Approving certificate for " + hostname);
        return true;
    }

}