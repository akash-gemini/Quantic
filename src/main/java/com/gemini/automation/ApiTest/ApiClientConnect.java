package com.gemini.automation.ApiTest;

import com.google.gson.JsonObject;

import java.io.File;
import java.util.Map;

public interface ApiClientConnect {
    public JsonObject postRequest(String url, String requestPayload, String contentType);

    public JsonObject getRequest(String url);

    public JsonObject putRequest(String url, String requestPayload, String contentType);

    public JsonObject createRequest(String method, String url, String requestPayload, Map<String, String> headers);

    public JsonObject patchRequest(String url, String requestPayload, String contentType);

    public JsonObject createRequest(String method, String url, File requestPayload, Map<String, String> headers);

    public JsonObject createRequest(String method, String url, Map<String,String> headers);

}