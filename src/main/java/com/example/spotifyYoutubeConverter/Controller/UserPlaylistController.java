package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.UserPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;

@Controller
public class UserPlaylistController {

    @Autowired
    UserPlaylistService userPlaylistService;

    @GetMapping("/userPlaylists")
    public String getUserTopArtist(Model model){
        model.addAttribute("topPlaylists", userPlaylistService.getUserPlaylists());

        return "userPlaylists";
    }
}
