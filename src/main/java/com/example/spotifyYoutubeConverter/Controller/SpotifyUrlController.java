package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifySearchTrackService;
import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyUrlService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SpotifyUrlController {

    @Autowired
    SpotifyUrlService spotifyUrlService;

    @Autowired
    SpotifySearchTrackService spotifySearchTrackService;

    @GetMapping("/login")
    public String getLoginPage(Model model){
        model.addAttribute("authorizationUrl" , spotifyUrlService.getAuthorizationUrl());
        return "/login";
    }

    @GetMapping("/myapi/get-user-code")
    public String getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        spotifyUrlService.getSpotifyUserCode(userCode,response);
        return "index.html";
    }

    @GetMapping("/searchSpotify/{trackTitle}")
    public String searchForTrackSpotify(@PathVariable String trackTitle, Model model){
        Track[] results = spotifySearchTrackService.searchForTrack(trackTitle);
        model.addAttribute("tracks", results);
        return "spotifyTracksSearchResults.html";
    }


}
