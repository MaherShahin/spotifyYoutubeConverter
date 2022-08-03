package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifySearchTrackService;
import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyUrlService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeUserPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @RequestMapping(value = {"","/","login"}, method = RequestMethod.GET)
    public String getLoginPage(@RequestParam(required = false, name="message") String errorMessage, Model model){
        if (errorMessage != null){
            model.addAttribute("errorMessage",errorMessage);
        }
        model.addAttribute("authorizationUrl" , spotifyUrlService.getAuthorizationUrl());
        return "/login";
    }

    @GetMapping("/loginYoutube")
    public String getYoutubeLogin(){
        return "/loginYoutube";
    }

    @GetMapping("/myapi/get-user-code")
    public ModelAndView getSpotifyUserCode(@RequestParam( required = false, name = "code") String userCode,
                                           @RequestParam(required = false, name = "error") String error ,
                                           HttpServletResponse response, ModelMap model) throws IOException {

        if (userCode == null){
            model.addAttribute("message", "Something wrong has happened, please try to login with your Spotify account again. Error: " + error);
            return new ModelAndView("redirect:/login",model);
        } else {
            spotifyUrlService.getSpotifyUserCode(userCode, response);
        }
        return new ModelAndView("redirect:/loginYoutube");
    }

    @GetMapping("/searchSpotify/{trackTitle}")
    public String searchForTrackSpotify(@PathVariable String trackTitle, Model model){
        Track[] results = spotifySearchTrackService.searchForTrack(trackTitle);
        model.addAttribute("tracks", results);
        return "spotifyTracksSearchResults.html";
    }


}
