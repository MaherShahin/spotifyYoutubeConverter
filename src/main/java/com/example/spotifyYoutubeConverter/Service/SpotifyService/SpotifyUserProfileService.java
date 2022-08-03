package com.example.spotifyYoutubeConverter.Service.SpotifyService;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.User;
import se.michaelthelin.spotify.requests.data.users_profile.GetCurrentUsersProfileRequest;

import java.io.IOException;

@Service
public class SpotifyUserProfileService {

    @Autowired
    SpotifyUrlService spotifyUrlService;

    @GetMapping("userProfile")
    public User getCurrentUserProfile() {

        final GetCurrentUsersProfileRequest getCurrentUsersProfileRequest =
                spotifyUrlService.getSpotifyApi().getCurrentUsersProfile().build();

        try{

            final User currentUser = getCurrentUsersProfileRequest.execute();

            return currentUser;

        }
        catch (IOException | ParseException | SpotifyWebApiException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
        return null;

    }
}
