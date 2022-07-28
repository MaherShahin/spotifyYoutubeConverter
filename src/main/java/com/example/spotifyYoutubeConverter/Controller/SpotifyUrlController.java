package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifySearchTrackService;
import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyUrlService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeAuthenticationService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeUserPlaylistService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SpotifyUrlController {

    private String youtubeAuthorizationUrl = "https://accounts.google.com/o/oauth2/auth?client_id=54787073109-" +
            "9alg8012fcofs0fa0ssffp0j8dcmhs5s.apps.googleusercontent.com&" +
            "redirect_uri=http://localhost:8080/Callback&response_type=code&" +
            "scope=https://www.googleapis.com/auth/youtube";
    @Autowired
    SpotifyUrlService spotifyUrlService;

    @Autowired
    SpotifySearchTrackService spotifySearchTrackService;

    @Autowired
    YoutubeUserPlaylistService youtubeUserPlaylistService;

    @RequestMapping(value = {"","/","login"}, method = RequestMethod.GET)
    public String getLoginPage(Model model){
        model.addAttribute("authorizationUrl" , spotifyUrlService.getAuthorizationUrl());
        model.addAttribute("youtubeAuthorizationUrl",youtubeAuthorizationUrl);
        return "/login";
    }

    @GetMapping("/myapi/get-user-code")
    public String getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        spotifyUrlService.getSpotifyUserCode(userCode,response);
        return "spotifyUserPlaylists";
    }

    @GetMapping("/searchSpotify/{trackTitle}")
    public String searchForTrackSpotify(@PathVariable String trackTitle, Model model){
        Track[] results = spotifySearchTrackService.searchForTrack(trackTitle);
        model.addAttribute("tracks", results);
        return "spotifyTracksSearchResults.html";
    }


}
