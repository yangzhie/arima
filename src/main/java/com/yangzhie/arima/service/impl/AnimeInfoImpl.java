package com.yangzhie.arima.service.impl;

import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.yangzhie.arima.repository.fetch.FetchAnimeInfo;
import com.yangzhie.arima.util.Helper;

public class AnimeInfoImpl {
    public JSONObject getAnimeInfo() {
        // Helper methods
        Helper helper = new Helper();

        // Fetch anime info
        FetchAnimeInfo fetcher = new FetchAnimeInfo();
        String animeInfo = fetcher.fetchAnimeInfo();

        // Return object
        JSONObject result = new JSONObject();

        try {
            // Parse JSON
            JSONParser parser = new JSONParser();
            JSONObject root = (JSONObject) parser.parse(animeInfo);
            JSONObject data = (JSONObject) root.get("data");

            // Get MAL ID
            result.put("id", helper.safe(data, "mal_id"));

            // Get MAL URL
            result.put("url", helper.safe(data, "url"));

            // Show description
            result.put("synopsis", helper.safe(data, "synopsis"));

            // Get show type (movie/tv)
            result.put("type", helper.safe(data, "type"));

            // Number of episodes
            result.put("episodes", helper.safe(data, "episodes"));

            // Status of show (airing, finished airing)
            result.put("status", helper.safe(data, "status"));

            // Duration of each episode
            result.put("duration", helper.safe(data, "duration"));

            // Show rating
            result.put("rating", helper.safe(data, "rating"));

            // Show score (1-10)
            result.put("score", helper.safe(data, "score"));

            // Show overall rank
            result.put("rank", helper.safe(data, "rank"));

            // Show popularity
            result.put("popularity", helper.safe(data, "popularity"));

            // How many have favourited the show
            result.put("favorites", helper.safe(data, "favorites"));

            // What season show started airing
            result.put("season", helper.safe(data, "season"));
            
            // Demographics
            ArrayList<Object> demographicsArray = new ArrayList<Object>();
            JSONArray demographics = (JSONArray) data.get("demographics");
            for (int i = 0; i < demographics.size(); i++) {
                JSONObject d = (JSONObject) demographics.get(i);
                String name = (String) d.get("name");
                demographicsArray.add(name);
            }
            result.put("demographics", demographicsArray);

            // Producers of show
            ArrayList<Object> producersArray= new ArrayList<Object>();
            JSONArray producers = (JSONArray) data.get("producers");
            for (int i = 0; i < producers.size(); i++) {
                JSONObject p = (JSONObject) producers.get(i);
                String name = (String) p.get("name");
                producersArray.add(name);
            }
            result.put("producers", producersArray);

            // Studios of anime
            ArrayList<Object> studiosArray= new ArrayList<Object>();
            JSONArray studios = (JSONArray) data.get("studios");
            for (int i = 0; i < studios.size(); i++) {
                JSONObject s = (JSONObject) studios.get(i);
                String name = (String) s.get("name");
                studiosArray.add(name);
            }
            result.put("studios", studiosArray);

            // Genres of anime
            ArrayList<Object> genresArray= new ArrayList<Object>();
            JSONArray genres = (JSONArray) data.get("genres");
            for (int i = 0; i < genres.size(); i++) {
                JSONObject g = (JSONObject) genres.get(i);
                String name = (String) g.get("name");
                genresArray.add(name);
            }
            result.put("genres", genresArray);

            // Titles 
            ArrayList<Object> titlesArray= new ArrayList<Object>();
            JSONArray titles = (JSONArray) data.get("titles");
            for (int i = 0; i < titles.size(); i++) {
                JSONObject t = (JSONObject) titles.get(i);
                String name = (String) t.get("title");
                titlesArray.add(name);
            }
            result.put("titles", titlesArray);

            // When the anime aired and finished
            JSONObject aired = (JSONObject) data.get("aired");
            String airStart = (String) aired.get("from");
            String airEnd = (String) aired.get("to");
            result.put("airStart", airStart);
            result.put("airEnd", airEnd);

            // Anime images
            JSONObject images = (JSONObject) data.get("images");
            JSONObject jpgImages = (JSONObject) images.get("jpg");
            String image = (String) jpgImages.get("image_url");
            result.put("image", image);

            // Anime YT trailer
            JSONObject trailerObject = (JSONObject) data.get("trailer");
            String trailer = (String) trailerObject.get("embed_url");
            result.put("trailer_url", trailer);

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            result.put("error", "Failed to parse anime info: " + e.getMessage());
            return result;
        }
    }
}