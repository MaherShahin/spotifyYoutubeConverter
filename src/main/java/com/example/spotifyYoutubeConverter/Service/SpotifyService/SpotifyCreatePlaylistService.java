package com.example.spotifyYoutubeConverter.Service.SpotifyService;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.CreatePlaylistRequest;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;

import java.io.IOException;

@Service
public class SpotifyCreatePlaylistService {

    @Autowired
    SpotifyUrlService spotifyUrlService;
    @Autowired
    UserProfileService userProfileService;

    public Playlist createPlaylistService(String title, String description){

        String userID = userProfileService.getCurrentUserProfile().getId();

        final CreatePlaylistRequest createPlaylistRequest = spotifyUrlService.getSpotifyApi().createPlaylist(userID,"title").build();

        try {
            final se.michaelthelin.spotify.model_objects.specification.Playlist createdPlaylist = createPlaylistRequest.execute();
            return createdPlaylist;
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new RuntimeException(e);
        }

    }

    // Insert Track

}
