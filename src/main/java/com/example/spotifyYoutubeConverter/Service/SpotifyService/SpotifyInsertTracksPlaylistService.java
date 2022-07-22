package com.example.spotifyYoutubeConverter.Service.SpotifyService;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;

import java.io.IOException;

@Service
public class SpotifyInsertTracksPlaylistService {

    @Autowired
    SpotifyUrlService spotifyUrlService;
    @Autowired
    UserProfileService userProfileService;

    public SnapshotResult insertItemsInPlaylist(String playlistId, String uriToBeAdded){

        String userID = userProfileService.getCurrentUserProfile().getId();

        final AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyUrlService.getSpotifyApi()
                .addItemsToPlaylist(playlistId, new String[]{uriToBeAdded}).build();
        try {
            final SnapshotResult playlist = addItemsToPlaylistRequest.execute();
            System.out.println("Item added Successfully!");
            return playlist;
        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new RuntimeException(e);
        }

    }
}
