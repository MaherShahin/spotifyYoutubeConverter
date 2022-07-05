package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.SpotifyUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class SpotifyUrlController {

    @Autowired
    SpotifyUrlService spotifyUrlService;

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

}
