package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserProfileController {
    @Autowired
    UserProfileService userProfileService;

    @GetMapping("/getCurrentUser")
    public String getUserTopArtist(Model model){
        model.addAttribute("currentUser", userProfileService.getCurrentUserProfile());
        return "currentUser";
    }

}
