package com.yangzhie.arima.controller;

import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yangzhie.arima.service.impl.AnimeInfoImpl;

// Map API route
@RestController
@RequestMapping("/api")
public class MainController {
    private final AnimeInfoImpl animeInfoService;

    public MainController(AnimeInfoImpl animeInfoService) {
        this.animeInfoService = animeInfoService;
    }

    @GetMapping("/get-anime-info/{animeId}")
    public JSONObject fetchAnime(@PathVariable String animeId) {
        return animeInfoService.getAnimeInfo(animeId);    
    }
}