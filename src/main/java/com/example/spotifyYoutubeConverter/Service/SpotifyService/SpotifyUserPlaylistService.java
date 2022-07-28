package com.example.spotifyYoutubeConverter.Service.SpotifyService;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.PlaylistSimplified;
import se.michaelthelin.spotify.requests.data.playlists.GetListOfCurrentUsersPlaylistsRequest;

import java.io.IOException;

@Service
public class SpotifyUserPlaylistService {
    @Autowired
    SpotifyUrlService spotifyUrlService;


    @GetMapping("userPlaylists")
    public PlaylistSimplified[] getUserPlaylists() {

        final GetListOfCurrentUsersPlaylistsRequest getListOfUsersPlaylistsRequest =
                spotifyUrlService.getSpotifyApi().getListOfCurrentUsersPlaylists()
                        .build();

        try{

            final PlaylistSimplified[] userPlaylists = getListOfUsersPlaylistsRequest.execute().getItems();
            return userPlaylists;

        }
        catch (IOException | ParseException | SpotifyWebApiException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
        return new PlaylistSimplified[0];

    }



}
