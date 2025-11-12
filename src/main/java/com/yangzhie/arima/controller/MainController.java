package com.yangzhie.arima.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yangzhie.arima.service.impl.AnimeInfoImpl;

// Map API route
@RestController
@RequestMapping("/api")
public class MainController {
    @GetMapping("/get-anime-info")
    public JSONObject fetchAnime() {
        AnimeInfoImpl getAnimeInfo = new AnimeInfoImpl();
        JSONObject info = getAnimeInfo.getAnimeInfo();
        return info;
    }
}