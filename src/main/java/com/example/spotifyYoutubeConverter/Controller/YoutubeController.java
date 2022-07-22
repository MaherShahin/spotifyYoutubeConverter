package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.YoutubeService.CreatePlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeUserPlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.InsertItemInPlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;

@Controller
public class YoutubeController {

    @Autowired
    YoutubeUserPlaylistService youtubeUserPlaylistService;
    @Autowired
    CreatePlaylistService createPlaylistService;

    @Autowired
    InsertItemInPlaylistService insertItemInPlaylistService;
    @Autowired
    SearchService searchService;

    @GetMapping("/getYoutubePlaylists")
    public String getYoutubePlaylist(Model model) throws IOException {
        model.addAttribute("topPlaylists", youtubeUserPlaylistService.getPlaylists());
        return "youtubeUserPlaylists";
    }

    @GetMapping("/createPlaylist/{title}/{description}")
    public String createPlaylist(@PathVariable("title") String title, @PathVariable("description") String description) throws IOException {
        String playlistId = createPlaylistService.createPlaylist(title,description);
        System.out.println("From Controller: " + playlistId);
        insertItemInPlaylistService.insertItemInPlaylist(playlistId,"OWv5wvZ3w7I");
        return "index.html";
    }

    @GetMapping("/search/{query}")
    public String searchItem(@PathVariable("query") String query){
        searchService.getVideos(query);
        return "index.html";
    }


}
