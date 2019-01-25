package com.igi.repository.model;

import com.igi.repository.AuthClientProvider;
import com.igi.repository.ApiDataSourceCallback;
import javafx.application.Platform;
import okhttp3.*;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

public class AuthApiDataSource {
    protected final Logger log = Logger.getLogger(getClass().getName());

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client;

    public void login(String username, String password, String authUrl, ApiDataSourceCallback loginCallback) {
        client = AuthClientProvider.createAuthenticatedClient(username, password);
        doGet(authUrl, loginCallback);
    }

    public void get(String url, final ApiDataSourceCallback callback){
        log.info("get URL " + url);
        doGet(url, callback);
    }

    public void put(String url, String json, ApiDataSourceCallback callback){
        log.info("put URL " + url);
        doPut(url, json, callback);
    }

    private void doPut(String anyURL, String jsonBody, ApiDataSourceCallback callback){
        RequestBody requestBody = RequestBody.create(JSON, jsonBody);
        Request request = new Request.Builder().url(anyURL).put(requestBody).build();

        call(request, callback);
    }

    private void doGet(String anyURL, final ApiDataSourceCallback callback) {
        Request request = new Request.Builder().url(anyURL).build();

        call(request,callback);

    }

    private void call(Request request, ApiDataSourceCallback callback) {
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Platform.runLater(() ->callback.onFailure(e.getMessage()));
            }

            @Override
            public void onResponse(Call call, final Response response) {
                if (!response.isSuccessful()) {
                    log.warning("response not successful");
                    Platform.runLater(() ->callback.onFailure(response.message()));
                } else {
                    try {
                        if(response.body() == null) log.info("success body null");
                        Optional<String> resOp = Optional.ofNullable(response.body().string());
                        String responseBody = "empty";
                        if(resOp.isPresent()) responseBody = resOp.get();
                        log.info("success body " + responseBody);
                        String ress = responseBody;

                        Platform.runLater(() ->callback.onSuccess(ress));
                    } catch (IOException e) {
                        log.severe("IOException");
                        Platform.runLater(() ->callback.onFailure(null));
                    }

                }
            }
        });
    }

    public void post(String url, String json, ApiDataSourceCallback callback) {
        log.info("post URL " + url);
        doPost(url, json, callback);
    }

    public void register(String url, String json, ApiDataSourceCallback callback){
        log.info("register URL " + url);
        client = AuthClientProvider.createGuestClient();
        doPost(url, json, callback);
    }

    private void doPost(String url, String json, ApiDataSourceCallback callback) {
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder().url(url).post(requestBody).build();

        call(request, callback);
    }
}
