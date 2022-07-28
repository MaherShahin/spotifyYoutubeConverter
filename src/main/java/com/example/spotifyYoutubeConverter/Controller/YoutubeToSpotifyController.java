package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.YoutubeSpotifyConverter.YoutubeSpotifyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class YoutubeToSpotifyController {

    @Autowired
    YoutubeSpotifyConverterService youtubeSpotifyConverterService;

    @GetMapping("/convertYoutubeToSpotify/{playlistId}")
    public String convertYoutubeToSpotify(@PathVariable("playlistId") String playlistId, Model model) throws IOException {

        model.addAttribute("tracks",youtubeSpotifyConverterService.convertYoutubePlaylistToSpotify(playlistId));
        //redirect to SpotifyPlaylists
        return "redirect:/spotifyUserPlaylists";
    }


}
