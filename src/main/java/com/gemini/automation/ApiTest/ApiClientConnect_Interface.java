package com.gemini.automation.ApiTest;

import com.google.gson.JsonObject;

import java.io.File;
import java.util.Map;

public interface ApiClientConnect_Interface {

    // Requests for HTTPS Protocol
    public JsonObject httpsPostRequest(String url, String requestPayload, String contentType);

    public JsonObject httpsGetRequest(String url);

    public JsonObject httpsPutRequest(String url, String requestPayload, String contentType);

    public JsonObject httpsCreateRequest(String method, String url, String requestPayload, Map<String, String> headers);

    public JsonObject httpsPatchRequest(String url, String requestPayload, String contentType);

    public JsonObject httpsCreateRequest(String method, String url, File requestPayload, Map<String, String> headers);

    public JsonObject httpsCreateRequest(String method, String url, Map<String,String> headers);


    // Request for HTTP Protocol
    public JsonObject httpPostRequest(String url, String requestPayload, String contentType);

    public JsonObject httpGetRequest(String url);

    public JsonObject httpPutRequest(String url, String requestPayload, String contentType);

    public JsonObject httpCreateRequest(String method, String url, String requestPayload, Map<String, String> headers);

    public JsonObject httpCreateRequest(String method, String url, File requestPayload, Map<String, String> headers);

    public JsonObject httpCreateRequest(String method, String url, Map<String,String> headers);






}