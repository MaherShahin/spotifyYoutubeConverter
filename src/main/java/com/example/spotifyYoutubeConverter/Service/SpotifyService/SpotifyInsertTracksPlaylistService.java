package com.example.spotifyYoutubeConverter.Service.SpotifyService;

import com.google.gson.*;
import org.apache.hc.core5.http.ParseException;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SnapshotResult;
import se.michaelthelin.spotify.requests.data.playlists.AddItemsToPlaylistRequest;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SpotifyInsertTracksPlaylistService {

    @Autowired
    SpotifyUrlService spotifyUrlService;
    @Autowired
    SpotifyUserProfileService spotifyUserProfileService;

    public void insertItemsInPlaylist(String playlistId, String[] urisToBeAdded) {

        String userID = spotifyUserProfileService.getCurrentUserProfile().getId();

        //Some Manipulation of the String[] is needed in order to pass it to the Spotify Wrapper First
        //Packaging the input data nicely
        List<String> urisList = new ArrayList<>(Arrays.asList(urisToBeAdded));

        //TODO Fix no tracks specified bugs -> should breakout if no items are left in the urisList

        boolean under100 = false;
        SnapshotResult playlist = null;

        do  {
            ArrayList<String> uriArrayList = null;

            //Breaking down the requests into chunks of 100 each

            if(urisList.size()==0){
                break;
            }

            if (urisList.size() < 100){
                under100 = true;
                uriArrayList = new ArrayList<>(urisList);
            }
            else {
                uriArrayList = new ArrayList<>(urisList.subList(0, 100));
                urisList = urisList.subList(99,urisList.size()-1);
            }

            //Setting up our JsonArray to pass to the wrapper
            Gson gson = new Gson();
            String data = gson.toJson(uriArrayList);
            JsonArray urisJson = new JsonParser().parse(data).getAsJsonArray();

            AddItemsToPlaylistRequest addItemsToPlaylistRequest = spotifyUrlService.getSpotifyApi()
                    .addItemsToPlaylist(playlistId, urisJson).build();
            try {
                playlist = addItemsToPlaylistRequest.execute();
                System.out.println("Item added Successfully!");

            } catch (IOException | ParseException | SpotifyWebApiException e) {
                throw new RuntimeException(e);
            }

        } while(!under100);


    }
}
