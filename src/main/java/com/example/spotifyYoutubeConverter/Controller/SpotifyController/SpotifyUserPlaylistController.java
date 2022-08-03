package com.example.spotifyYoutubeConverter.Controller.SpotifyController;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyUserPlaylistService;
import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpotifyUserPlaylistController {

    @Autowired
    SpotifyUserPlaylistService spotifyUserPlaylistService;

    @Autowired
    SpotifyUserProfileService spotifyUserProfileService;

    @GetMapping("/spotifyUserPlaylists")
    public String getUserTopArtist(Model model){
        if (spotifyUserProfileService.getCurrentUserProfile() == null ){
            System.out.println("value of currentUserProfile is null i.e. no user logged in!");
            return "error";
        }
        model.addAttribute("topPlaylists", spotifyUserPlaylistService.getUserPlaylists());

        return "spotifyUserPlaylists";
    }
}
