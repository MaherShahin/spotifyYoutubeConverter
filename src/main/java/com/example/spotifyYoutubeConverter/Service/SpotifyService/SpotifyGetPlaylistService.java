package com.example.spotifyYoutubeConverter.Service.SpotifyService;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistRequest;

import java.io.IOException;

@Service
public class SpotifyGetPlaylistService {

    @Autowired
    SpotifyUrlService spotifyUrlService;

    public Playlist getPlaylist(String playlistId){
        final GetPlaylistRequest getPlaylistRequest = spotifyUrlService.getSpotifyApi().getPlaylist(playlistId).build();

        try{

            final Playlist playlist = getPlaylistRequest.execute();
            return playlist;

        }
        catch (IOException | ParseException | SpotifyWebApiException e) {
            System.out.println("Something went wrong! " + e.getMessage());
        }
        return null;
    }
}
