package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyYoutubeConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class SpotifyToYoutubeController {
    @Autowired
    SpotifyYoutubeConverterService spotifyYoutubeConverterService;

    @GetMapping("/convertSpotifyToYoutube/{spotifyPlaylistId}")
    public String convertSpotifyYoutube(@PathVariable("spotifyPlaylistId") String spotifyPlaylistId, Model model) throws IOException {
        spotifyYoutubeConverterService.convertToYoutube(spotifyPlaylistId);
        return "redirect:/youtubeUserPlaylists";
    }
}