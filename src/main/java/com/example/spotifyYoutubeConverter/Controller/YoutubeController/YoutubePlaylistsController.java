package com.example.spotifyYoutubeConverter.Controller.YoutubeController;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyUrlService;
import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyUserProfileService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeUserPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class YoutubePlaylistsController {

    @Autowired
    YoutubeUserPlaylistService youtubeUserPlaylistService;
    @Autowired
    SpotifyUserProfileService spotifyUserProfileService;

    @Autowired
    SpotifyUrlService spotifyUrlService;

    @RequestMapping(value = {"/getYoutubePlaylists","/Callback","/youtubeUserPlaylists"})
    public String getYoutubePlaylist(Model model) throws IOException {
        model.addAttribute("topPlaylists", youtubeUserPlaylistService.getPlaylists());
        model.addAttribute("spotifyStatus", spotifyUserProfileService.getCurrentUserProfile());
        model.addAttribute("spotifyAuthUrl",spotifyUrlService.getAuthorizationUrl());
        return "youtubeUserPlaylists";
    }

}
