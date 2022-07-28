package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyUserPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpotifyUserPlaylistController {

    @Autowired
    SpotifyUserPlaylistService spotifyUserPlaylistService;

    @GetMapping("/spotifyUserPlaylists")
    public String getUserTopArtist(Model model){
        model.addAttribute("topPlaylists", spotifyUserPlaylistService.getUserPlaylists());

        return "spotifyUserPlaylists";
    }
}
