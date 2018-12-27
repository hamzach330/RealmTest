package com.example.hamzamasood.realmtest.businesslogic;

import android.app.Activity;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLHandshakeException;


/**
 * Created by m.umer on 12/9/2016.
 */

public class HttpHandler {
    Activity activity;

    HttpsURLConnection connection_https;
    HttpURLConnection connection_http;
    KeyPinStore_Old keyPinStore_old;
    KeyPinStore keystore;

    private boolean publicKeyPinn = true;    ////  make true for new ssl pinning

    public HttpHandler(Activity activity) {
        this.activity = activity;
    }

    public static String writeTextFileToSDCard(String fileName, String request) throws Exception {
        File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), "UBL_Logs");
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(dir, fileName + ".txt");
        FileOutputStream fOut = new FileOutputStream(file);
        OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
        myOutWriter.append(request);
        myOutWriter.close();
        fOut.flush();
        fOut.close();
        return "ok";
    }

    public String makeServiceCall2(String url, String request) throws Exception {
        String json = "";
        String response = "";

        URL urls = new URL(url);
        if (urls.getProtocol().equalsIgnoreCase("https")) {
            try {

                if (publicKeyPinn) {
                    keystore = new KeyPinStore(activity);
                    connection_https = (HttpsURLConnection) urls.openConnection();
                    connection_https.setSSLSocketFactory(keystore.getContext().getSocketFactory());
                } else {
                    keyPinStore_old = new KeyPinStore_Old(activity);
                    connection_https = (HttpsURLConnection) urls.openConnection();
                    connection_https.setSSLSocketFactory(keyPinStore_old.getContext().getSocketFactory());
                }
                connection_https.setConnectTimeout(60000);
                connection_https.setRequestMethod("POST");
                connection_https.setRequestProperty("Accept", "application/json");
                connection_https.setRequestProperty("Content-type", "application/json");
                connection_https.setDoInput(true);
                connection_https.setDoOutput(true);
                connection_https.connect();
                OutputStream os = connection_https.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(request);
                writer.flush();
                writer.close();
                os.close();
                int responseCode = connection_https.getResponseCode();
                if (responseCode == 200) {
                    InputStream inputStream = connection_https.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(inputStream, "UTF-8"));
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    connection_https.disconnect();
                    json = response;
                    return json;
                } else {
                    String errorJson = " {\"Code\": \"3039\",\"Desc\": \"" + connection_https.getResponseMessage() + "\"} ";
                    return errorJson;
                }

            } catch (Exception e) {

                if (e != null && e instanceof SSLHandshakeException || e instanceof CertificateException || e instanceof IllegalArgumentException) {
                    String errorJson = " {\"Code\": \"3011\",\"Desc\": \"" + e.toString() + "\"} ";
                    return errorJson;
                } else {


                    String errorJson = " {\"Code\": \"3039\",\"Desc\": \"" + connection_https.getResponseMessage() + "\"} ";
                    return errorJson;
                }
            }
        } else {
            try {
                    /*if (url.contains("HealthCheck")) {
                        url = "http://172.16.1.133/UBL_AOT/UblAotCoreApis/Activity/HealthCheck";
                    }*/
                connection_http = (HttpURLConnection) urls
                        .openConnection();

                connection_http.setConnectTimeout(60000);
                connection_http.setRequestMethod("POST");
                connection_http.setRequestProperty("Accept", "application/json");
                connection_http.setRequestProperty("Content-type", "application/json");
                connection_http.setDoInput(true);
                connection_http.setDoOutput(true);
                OutputStream outputStream = connection_http.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(request);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                connection_http.connect();
                int responseCode = connection_http.getResponseCode();
                if (responseCode == 200) {
                    InputStream inputStream = connection_http.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(inputStream, "UTF-8"));
                    String line = "";
                    while ((line = bufferedReader.readLine()) != null) {
                        response += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    connection_http.disconnect();
                    json = response;
                    return json;
                } else {
                    String errorJson = " {\"Code\": \"3039\",\"Desc\": \"" + connection_http.getResponseMessage() + "\"} ";
                    return errorJson;
                }
            } catch (Exception e) {
                String errorJson = " {\"Code\": \"3039\",\"Desc\": \"" + connection_http.getResponseMessage() + "\"} ";
                return errorJson;
            }
        }

    }
}
