package com.gemini.automation.ApiTest;
import java.io.*;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Instant;
import java.util.*;

import javax.net.ssl.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

// import generic.QuanticGlobalVar;
// import generic.prism.UGTFapi;

public class ApiClientConnect {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    static class MyAuthenticator extends Authenticator {
        static final String kuser = "svc_qc"; // your account name
        static final String kpass = "XklRvE8juIkh"; // your account password

        public PasswordAuthentication getPasswordAuthentication() {
            // System.out.println("Using Custom Authentication");
            String decryptedPwd = getDecryptedPwd(kpass);
            return (new PasswordAuthentication(kuser, decryptedPwd.toCharArray()));
        }
    }

    public static String getDecryptedPwd(String encryptedPwd) {
        String decryptedPwd = "";
        for (int i = encryptedPwd.length() - 1; i >= 0; i--) {
            decryptedPwd += (char) ((int) encryptedPwd.charAt(i) - 1);
        }
        return decryptedPwd;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////
    private static void writeDataToOutputStream(final OutputStream outputStream, final String jsonStringPayload) {
        try (OutputStream os = outputStream) {
            os.write(jsonStringPayload.getBytes());
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static JsonObject PostRequest(String url, String requestPayload, String contentType) {
        Authenticator.setDefault(new MyAuthenticator());
        HttpURLConnection httpsCon;
        long startTime = Instant.now().toEpochMilli();

        try {
            URL requestUrl = new URL(url);
            String requestProtocol = requestUrl.getProtocol();
            httpsCon = requestProtocol.equals("https") ? createSSLDisabledHttpsUrlConnection(requestUrl) : (HttpURLConnection) requestUrl.openConnection();

            httpsCon.setConnectTimeout(100000);
            httpsCon.setDoInput(true);
            httpsCon.setDoOutput(true);
            httpsCon.setRequestMethod("POST");
            httpsCon.setReadTimeout(100000);

            // httpsCon.setFixedLengthStreamingMode(requestPayLoad.getBytes().Length);
            httpsCon.setRequestProperty("Content-Type", "application/json");
            if (contentType != "json") {
                httpsCon.setRequestProperty("Content-Type", "multipart/form-data");
            }
            httpsCon.setRequestProperty("accept", "application/json, text/plain, */*");
            httpsCon.setRequestProperty("Connection", "keep-alive");
            httpsCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36");

            writeDataToOutputStream(httpsCon.getOutputStream(), requestPayload);

            httpsCon.connect();

            int statusCode = httpsCon.getResponseCode();
            String responseMessage = httpsCon.getResponseMessage();
            String errorMessage = getDataFromBufferedReader(httpsCon.getErrorStream());
            String responseBody = errorMessage == null ? getDataFromBufferedReader(httpsCon.getInputStream()) : null;
            long executionTime = Instant.now().toEpochMilli() - startTime;
            JsonObject responseJSON = new JsonObject();

            responseJSON.addProperty("status", statusCode);
            responseJSON.addProperty("responseMessage", responseMessage);
            responseJSON.add("responseError", errorMessage != null ? JsonParser.parseString(errorMessage) : null);
            responseJSON.add("responseBody", responseBody != null ? JsonParser.parseString(responseBody) : null);
            responseJSON.addProperty("execTime", executionTime);

            return responseJSON;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getDataFromBufferedReader(final InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        String output;
        if (inputStream != null) {
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            try {
                while ((output = br.readLine()) != null) {
                    builder.append(output);
                }
            } catch (IOException e) {
                e.printStackTrace();
                builder = new StringBuilder(e.getMessage());
            }
            return builder.toString();
        } else {
            return null;
        }

    }

    /////////////////////////////////////////////// HTTPS OPERATION //////////////////////////////
    private static TrustManager[] getTrustManager() {
        TrustManager[] trustManager = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                        // TODO Auto-generated method stub

                    }

                }

        };
        return trustManager;
    }

    private static HostnameVerifier getHostVerifier() {
        HostnameVerifier hostnameVerifier = new HostnameVerifier() {
            @Override
            public boolean verify(String s, SSLSession sslSession) {
                return true;
            }
        };
        return hostnameVerifier;
    }

    public static HttpURLConnection createSSLDisabledHttpsUrlConnection(final URL requestUrl) {
        HttpsURLConnection httpsURLConnection;
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, getTrustManager(), new SecureRandom());
            httpsURLConnection = (HttpsURLConnection) requestUrl.openConnection();
            httpsURLConnection.setSSLSocketFactory(sslContext.getSocketFactory());
            httpsURLConnection.setHostnameVerifier(getHostVerifier());
            httpsURLConnection.setDoOutput(true);
        } catch (Exception e) {
            httpsURLConnection = null;
        }
        return httpsURLConnection;
    }


    public static JsonObject GetRequest(String url) {
        Authenticator.setDefault(new MyAuthenticator());
        long startTime = Instant.now().toEpochMilli();
        HttpURLConnection httpsCon;
        try {
            URL requestUrl = new URL(url);
            String requestProtocol = requestUrl.getProtocol();

            httpsCon = requestProtocol.equals("https") ? createSSLDisabledHttpsUrlConnection(requestUrl) :
                    (HttpURLConnection) requestUrl.openConnection();

            httpsCon.setRequestMethod("GET");
            httpsCon.connect();

            int statusCode = httpsCon.getResponseCode();
            String responseMessage = httpsCon.getResponseMessage();
            String errorMessage = getDataFromBufferedReader(httpsCon.getErrorStream());
            String responseBody = errorMessage == null ? getDataFromBufferedReader(httpsCon.getInputStream()) : null;
            long executionTime = Instant.now().toEpochMilli() - startTime;
            JsonObject responseJson = new JsonObject();

//            nebula.addrow("status",statusCode)
            responseJson.addProperty("status", statusCode);
            responseJson.addProperty("responseMessage", responseMessage);
            responseJson.add("responseError", errorMessage != null ? JsonParser.parseString(errorMessage) : null);
            responseJson.add("responseBody", responseBody != null ? JsonParser.parseString(responseBody) : null);
            responseJson.addProperty("execTime", executionTime);

            return responseJson;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static JsonObject PutRequest(String url, String requestPayload, String contentType) {
        Authenticator.setDefault(new MyAuthenticator());
        HttpURLConnection httpsCon;
        long startTime = Instant.now().toEpochMilli();

        try {
            URL requestUrl = new URL(url);
            String requestProtocol = requestUrl.getProtocol();
            httpsCon = requestProtocol.equals("https") ? createSSLDisabledHttpsUrlConnection(requestUrl) : (HttpsURLConnection) requestUrl.openConnection();

            httpsCon.setConnectTimeout(100000);
            httpsCon.setDoInput(true);
            httpsCon.setDoOutput(true);
            httpsCon.setRequestMethod("PUT");
            httpsCon.setReadTimeout(100000);

            // httpsCon.setFixedLengthStreamingMode(requestPayLoad.getBytes().Length);
            httpsCon.setRequestProperty("Content-Type", "application/json");
            if (contentType != "json") {
                httpsCon.setRequestProperty("Content-Type", "multipart/form-data");
            }
            httpsCon.setRequestProperty("accept", "application/json, text/plain, */*");
            httpsCon.setRequestProperty("Connection", "keep-alive");
            httpsCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36");

            writeDataToOutputStream(httpsCon.getOutputStream(), requestPayload);

            httpsCon.connect();

            int statusCode = httpsCon.getResponseCode();
            String responseMessage = httpsCon.getResponseMessage();
            String errorMessage = getDataFromBufferedReader(httpsCon.getErrorStream());
            String responseBody = errorMessage == null ? getDataFromBufferedReader(httpsCon.getInputStream()) : null;
            long executionTime = Instant.now().toEpochMilli() - startTime;
            JsonObject responseJSON = new JsonObject();

            responseJSON.addProperty("status", statusCode);
            responseJSON.addProperty("responseMessage", responseMessage);
            responseJSON.add("responseError", errorMessage != null ? JsonParser.parseString(errorMessage) : null);
            responseJSON.add("responseBody", responseBody != null ? JsonParser.parseString(responseBody) : null);
            responseJSON.addProperty("execTime", executionTime);

            return responseJSON;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject CreateRequest(String method, String url, String requestPayload, Map<String, String> headers) {
        Authenticator.setDefault(new MyAuthenticator());
        HttpURLConnection httpsCon;

        long startTime = Instant.now().toEpochMilli();
        method = method.toUpperCase();
        try {
            URL requestUrl = new URL(url);
            String requestProtocol = requestUrl.getProtocol();

            httpsCon = requestProtocol.equals("https") ? createSSLDisabledHttpsUrlConnection(requestUrl) :
                    (HttpsURLConnection) requestUrl.openConnection();


            httpsCon.setRequestProperty("accept", "application/json, text/plain, */*");
            httpsCon.setRequestProperty("Connection", "keep-alive");
            httpsCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36");

            for (Map.Entry<String, String> set : headers.entrySet()) {
                httpsCon.setRequestProperty(set.getKey(), set.getValue());
            }

            // Setting Content-Type if not present in Headers
            if (!headers.containsKey("Content-Type")) {
                httpsCon.setRequestProperty("Content-Type", "application/json");
            }

            // Setting Other headers if not present in Headers
            if (!headers.containsKey("accept")) {
                httpsCon.setRequestProperty("accept", "application/json, text/plain, */*");
            }

            method = method.toUpperCase();
            switch (method) {
                case "POST": {
                    try {

                        httpsCon.setRequestMethod("POST");
                        httpsCon.setReadTimeout(100000);
                        break;

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                }
                case "PUT": {
                    try {
                        httpsCon.setRequestMethod("PUT");
                        httpsCon.setReadTimeout(100000);
                        break;

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                }
                case "PATCH": {
                    try {

                        httpsCon.setRequestMethod("POST");
                        httpsCon.setRequestProperty("X-HTTP-Method-Override", "PATCH");
                        httpsCon.setReadTimeout(100000);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                }
                default: {
                    System.out.println("Only POST, PUT or PATCH Request is allowed");
                }
            }
            writeDataToOutputStream(httpsCon.getOutputStream(), requestPayload);
            httpsCon.connect();
            int statusCode = httpsCon.getResponseCode();
            String responseMessage = httpsCon.getResponseMessage();
            String errorMessage = getDataFromBufferedReader(httpsCon.getErrorStream());
            String responseBody = errorMessage == null ? getDataFromBufferedReader(httpsCon.getInputStream()) : null;
            long executionTime = Instant.now().toEpochMilli() - startTime;
            JsonObject responseJSON = new JsonObject();

            responseJSON.addProperty("status", statusCode);
            responseJSON.addProperty("responseMessage", responseMessage);
            responseJSON.add("responseError", errorMessage != null ? JsonParser.parseString(errorMessage) : null);
            responseJSON.add("responseBody", responseBody != null ? JsonParser.parseString(responseBody) : null);
            responseJSON.addProperty("execTime", executionTime + " ms");
            return responseJSON;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JsonObject PatchRequest(String url, String requestPayload, String contentType) {
        Authenticator.setDefault(new MyAuthenticator());
        HttpURLConnection httpsCon;
        long startTime = Instant.now().toEpochMilli();

        try {
            URL requestUrl = new URL(url);
            String requestProtocol = requestUrl.getProtocol();
            httpsCon = requestProtocol.equals("https") ? createSSLDisabledHttpsUrlConnection(requestUrl) : (HttpsURLConnection) requestUrl.openConnection();

            httpsCon.setConnectTimeout(100000);
            httpsCon.setDoInput(true);
            httpsCon.setDoOutput(true);
            httpsCon.setRequestMethod("POST");
            httpsCon.setRequestProperty("X-HTTP-Method-Override", "PATCH");
            httpsCon.setReadTimeout(100000);

            // httpsCon.setFixedLengthStreamingMode(requestPayLoad.getBytes().Length);
            httpsCon.setRequestProperty("Content-Type", "application/json");
            if (contentType != "json") {
                httpsCon.setRequestProperty("Content-Type", "multipart/form-data");
            }
            httpsCon.setRequestProperty("accept", "application/json, text/plain, */*");
            httpsCon.setRequestProperty("Connection", "keep-alive");
            httpsCon.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.110 Safari/537.36");

            writeDataToOutputStream(httpsCon.getOutputStream(), requestPayload);

            httpsCon.connect();

            int statusCode = httpsCon.getResponseCode();
            String responseMessage = httpsCon.getResponseMessage();
            String errorMessage = getDataFromBufferedReader(httpsCon.getErrorStream());
            String responseBody = errorMessage == null ? getDataFromBufferedReader(httpsCon.getInputStream()) : null;
            long executionTime = Instant.now().toEpochMilli() - startTime;
            JsonObject responseJSON = new JsonObject();

            responseJSON.addProperty("status", statusCode);
            responseJSON.addProperty("responseMessage", responseMessage);
            responseJSON.add("responseError", errorMessage != null ? JsonParser.parseString(errorMessage) : null);
            responseJSON.add("responseBody", responseBody != null ? JsonParser.parseString(responseBody) : null);
            responseJSON.addProperty("execTime", executionTime);

            return responseJSON;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static JsonObject CreateRequest(String method, String url, File requestPayload, Map<String, String> headers) {
        StringBuilder payload = new StringBuilder();
        try {
            FileReader fr = new FileReader(requestPayload);
            int i;
            // Holds true till there is nothing to read
            while ((i = fr.read()) != -1) {
                payload.append((char) i);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        JsonObject responseJson = CreateRequest(method, url, payload.toString(), headers);
        return responseJson;
    }


    public static JsonObject CreateRequest(String method, String url, Map<String, String> headers) {
        Authenticator.setDefault(new MyAuthenticator());
        HttpURLConnection httpsCon;
        long startTime = Instant.now().toEpochMilli();
        method = method.toUpperCase();
        try {
            URL requestUrl = new URL(url);
            String requestProtocol = requestUrl.getProtocol();

            httpsCon = requestProtocol.equals("https") ? createSSLDisabledHttpsUrlConnection(requestUrl) :
                    (HttpsURLConnection) requestUrl.openConnection();


            for (Map.Entry<String, String> set : headers.entrySet()) {
                httpsCon.setRequestProperty(set.getKey(), set.getValue());
            }

            // Setting Content-Type if not present in Headers
            if (!headers.containsKey("Content-Type")) {
                httpsCon.setRequestProperty("Content-Type", "application/json");
            }

            // Setting Other headers if not present in Headers
            if (!headers.containsKey("accept")) {
                httpsCon.setRequestProperty("accept", "application/json, text/plain, */*");
            }

            switch (method) {
                case "GET": {
                    try {
                        httpsCon.setRequestMethod("GET");
                        httpsCon.connect();
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }

                }

                case "DELETE": {
                    try {
                        httpsCon.setRequestMethod("DELETE");
                        httpsCon.connect();
                        break;

                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                }
                default: {
                    System.out.println("Enter valid parameters");

                }
            }
            int statusCode = httpsCon.getResponseCode();
            String responseMessage = httpsCon.getResponseMessage();
            String errorMessage = getDataFromBufferedReader(httpsCon.getErrorStream());
            String responseBody = errorMessage == null ? getDataFromBufferedReader(httpsCon.getInputStream()) : null;
            long executionTime = Instant.now().toEpochMilli() - startTime;
            JsonObject responseJson = new JsonObject();

            responseJson.addProperty("status", statusCode);
            responseJson.addProperty("responseMessage", responseMessage);
            responseJson.add("responseError", errorMessage != null ? JsonParser.parseString(errorMessage) : null);
            responseJson.add("responseBody", responseBody != null ? JsonParser.parseString(responseBody) : null);
            responseJson.addProperty("execTime", executionTime);

            return responseJson;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}