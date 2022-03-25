package com.gemini.automation.ApiTest;

import com.google.gson.JsonObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]) {
        ApiClientConnect_Interface apiClientConnect = new ApiClientConnect_Interface() {
            @Override
            public JsonObject httpsPostRequest(String url, String requestPayload, String contentType) {
                return null;
            }

            @Override
            public JsonObject httpsGetRequest(String url) {
                return null;
            }

            @Override
            public JsonObject httpsPutRequest(String url, String requestPayload, String contentType) {
                return null;
            }

            @Override
            public JsonObject httpsCreateRequest(String method, String url, String requestPayload, Map<String, String> headers) {
                return null;
            }

            @Override
            public JsonObject httpsPatchRequest(String url, String requestPayload, String contentType) {
                return null;
            }

            @Override
            public JsonObject httpsCreateRequest(String method, String url, File requestPayload, Map<String, String> headers) {
                return null;
            }

            @Override
            public JsonObject httpsCreateRequest(String method, String url, Map<String, String> headers) {
                return null;
            }

            @Override
            public JsonObject httpPostRequest(String url, String requestPayload, String contentType) {
                return null;
            }

            @Override
            public JsonObject httpGetRequest(String url) {
                return null;
            }

            @Override
            public JsonObject httpPutRequest(String url, String requestPayload, String contentType) {
                return null;
            }

            @Override
            public JsonObject httpCreateRequest(String method, String url, String requestPayload, Map<String, String> headers) {
                return null;
            }

            @Override
            public JsonObject httpCreateRequest(String method, String url, File requestPayload, Map<String, String> headers) {
                return null;
            }

            @Override
            public JsonObject httpCreateRequest(String method, String url, Map<String, String> headers) {
                return null;
            }
        };
        String token = "Bearer e44ac095d53abb1da69ff4cdc9c0bc24ea741dbc7cbe6f2ba2dfdbec9cb1ecd4";

        // Headers
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", token);
        headers.put("Content-Type", "application/json");

        // Payload
        JsonObject req = new JsonObject();
        req.addProperty("name", "User_updated_2");
        req.addProperty("email", "Updated_User2@gmail.com");
        req.addProperty("gender", "male");
        req.addProperty("status", "inactive");

        // Mock PUT Request
        String url = "https://gorest.co.in/public/v1/users/3533";


        JsonObject response = apiClientConnect.httpCreateRequest("patch", url, req.toString(), headers);
        System.out.println(response.toString());


    }
}
