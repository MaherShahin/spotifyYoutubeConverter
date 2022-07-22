package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Model.MyTrack;
import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyCreatePlaylistService;
import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyInsertTracksPlaylistService;
import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifySearchTrackService;
import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyUrlService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeUserPlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeSpotifyConverter.YoutubeSpotifyConverterService;
import com.google.api.services.youtube.model.Playlist;
import com.google.api.services.youtube.model.PlaylistItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.io.IOException;
import java.util.ArrayList;

@Controller
public class YoutubeToSpotifyController {

    @Autowired
    YoutubeSpotifyConverterService youtubeSpotifyConverterService;


    @GetMapping("/convertYoutubeToSpotify/{playlistId}")
    public String convertYoutubeToSpotify(@PathVariable("playlistId") String playlistId, Model model) throws IOException {

        model.addAttribute("tracks",youtubeSpotifyConverterService.convertYoutubePlaylistToYoutube(playlistId));
        return "/youtubeSpotifyComparison";
    }


}
