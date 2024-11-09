package org.example.healthcaremanagementapplication.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MapboxService {

    private final OkHttpClient client = new OkHttpClient();

    @Value("${mapbox.api-key}")
    private String mapboxApiKey;

    public String getRoute(String coordinates) {
        String url = "https://api.mapbox.com/directions/v5/mapbox/driving/" + coordinates +
                "?access_token=" + mapboxApiKey;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {
                throw new RuntimeException("Error from Mapbox API: " + response.code());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch route from Mapbox API", e);
        }
    }
}
