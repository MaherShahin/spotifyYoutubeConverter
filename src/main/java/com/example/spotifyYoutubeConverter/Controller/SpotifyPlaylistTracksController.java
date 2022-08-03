package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyPlaylistTracksService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeCreatePlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeInsertItemInPlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpotifyPlaylistTracksController {

    @Autowired
    SpotifyPlaylistTracksService spotifyPlaylistTracksService;



    @GetMapping("/getTracksForPlaylist/{playlistId}")
    public String getPlaylistTracks(@PathVariable String playlistId, Model model) throws IOException {

        List<Track> tracks = new ArrayList<>();
        for (PlaylistTrack p : spotifyPlaylistTracksService.getPlaylistTracks(playlistId)) {
            tracks.add((Track) p.getTrack());

        }
        model.addAttribute("tracks", tracks);


        return "spotifyTracksPlaylist";
    }

//
//    @Autowired
//    YoutubeCreatePlaylistService youtubeCreatePlaylistService;
//    @Autowired
//    YoutubeInsertItemInPlaylistService youtubeInsertItemInPlaylistService;
//    @Autowired
//    YoutubeSearchService youtubeSearchService;
//
//    @GetMapping("/getTracksForPlaylist/{playlistId}")
//    public String getPlaylistTracks(@PathVariable String playlistId, Model model) throws IOException {
//
//        List<Track> tracks = new ArrayList<>();
//        for (PlaylistTrack p : spotifyPlaylistTracksService.getPlaylistTracks(playlistId)) {
//            tracks.add((Track) p.getTrack());
//
//        }
//        model.addAttribute("tracks", tracks);
//
//
//        return "spotifyTracksPlaylist";
//    }

}
