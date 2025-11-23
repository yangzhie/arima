package com.yangzhie.arima.repository.fetch;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Repository;

@Repository
public class FetchAnimeInfo {
    public String fetchAnimeInfo(String animeId) {
        // Create HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Create URL string
        String url = "https://api.jikan.moe/v4/anime/" + animeId;

        // Create GET request
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
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
