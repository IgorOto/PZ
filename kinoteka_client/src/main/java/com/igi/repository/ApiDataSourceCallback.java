package com.igi.repository;

public interface ApiDataSourceCallback {

    void onSuccess(String json);

    void onFailure(String json);
}
