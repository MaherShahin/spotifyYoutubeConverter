package com.example.spotifyYoutubeConverter.Controller.SpotifyController;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.SpotifyUserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpotifyUserProfileController {
    @Autowired
    SpotifyUserProfileService spotifyUserProfileService;

    @GetMapping("/getCurrentUser")
    public String getUserTopArtist(Model model){
        model.addAttribute("currentUser", spotifyUserProfileService.getCurrentUserProfile());
        return "currentUser";
    }

}
