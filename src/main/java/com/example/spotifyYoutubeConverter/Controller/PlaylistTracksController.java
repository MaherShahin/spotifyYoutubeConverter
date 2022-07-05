package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.PlaylistTracksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PlaylistTracksController {

    @Autowired
    PlaylistTracksService playlistTracksService;

    @GetMapping("/getTracksForPlaylist/{playlistId}")
    public String getPlaylistTracks(@PathVariable String playlistId, Model model){
        model.addAttribute("tracks" , playlistTracksService.getPlaylistTracks(playlistId));
        return "tracksPlaylist";
    }
}
