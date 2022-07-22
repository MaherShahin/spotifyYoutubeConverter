package com.example.spotifyYoutubeConverter.Service.SpotifyService;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.requests.data.playlists.GetPlaylistsItemsRequest;

import java.io.IOException;

@Service
public class PlaylistTracksService {

    @Autowired
    SpotifyUrlService spotifyUrlService;

    @GetMapping("tracks-in-playlists")
    public PlaylistTrack[] getPlaylistTracks(String playlistId){

        final GetPlaylistsItemsRequest getPlaylistsItemsRequest =
                spotifyUrlService.getSpotifyApi().getPlaylistsItems
                        (playlistId).build();

        try {

            final PlaylistTrack[] playlistTracks= getPlaylistsItemsRequest.execute().getItems();

            return playlistTracks;
        }
        catch (IOException | ParseException | SpotifyWebApiException e) {

            System.out.println("Something went wrong! " + e.getMessage());
        }
        return new PlaylistTrack[0];
    }
}
