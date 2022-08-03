package com.example.spotifyYoutubeConverter.Service.SpotifyService;

import com.google.gson.JsonArray;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;

import java.io.IOException;

@Service
public class SpotifySearchTrackService {

    @Autowired
    SpotifyUrlService spotifyUrlService;


    public Track[] searchForTrack(String title){
        final SearchItemRequest searchItemRequest = spotifyUrlService.getSpotifyApi().searchItem(title,"track").build();

        try {
            final SearchResult searchResult =  searchItemRequest.execute();

            Track[] tracks = searchResult.getTracks().getItems();

            return tracks;

        } catch (IOException | ParseException | SpotifyWebApiException e) {
            throw new RuntimeException(e);
        }

    }
}
