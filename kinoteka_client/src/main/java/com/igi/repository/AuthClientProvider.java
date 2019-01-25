package com.igi.repository;

import okhttp3.*;

import java.io.IOException;

public class AuthClientProvider {
    public static OkHttpClient createAuthenticatedClient(final String username,
                                                          final String password) {

        return new OkHttpClient.Builder().authenticator(new Authenticator() {
            public Request authenticate(Route route, Response response) throws IOException {
                String credential = Credentials.basic(username, password);
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        }).build();
    }

    public static OkHttpClient createGuestClient(){
        return new OkHttpClient();
    }
}
