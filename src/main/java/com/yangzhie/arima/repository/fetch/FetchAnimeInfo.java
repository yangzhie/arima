package com.yangzhie.arima.repository.fetch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FetchAnimeInfo {
    public String fetchAnimeInfo() {
        // Create HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create GET request
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create("https://api.jikan.moe/v4/anime/5114"))
            .GET()
            .build();
    
        try {
            // Send request + get response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    
            // Get response body
            String convertedResponse = response.body();
    
            // Return response
            return convertedResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
