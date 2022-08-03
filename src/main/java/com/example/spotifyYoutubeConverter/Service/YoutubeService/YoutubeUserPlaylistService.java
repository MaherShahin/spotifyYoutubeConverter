package com.example.spotifyYoutubeConverter.Service.YoutubeService;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class YoutubeUserPlaylistService {
    private static YouTube youtube;

    public List<Playlist> getPlaylists() throws IOException {
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        Credential credential = YoutubeAuthenticationService.authorize(scopes, "playlistupdates");

        // This object is used to make YouTube Data API requests.
        youtube = new YouTube.Builder(YoutubeAuthenticationService.HTTP_TRANSPORT, YoutubeAuthenticationService.JSON_FACTORY, credential)
                .setApplicationName("youtube-cmdline-playlistupdates-sample")
                .build();

        YouTube.Playlists.List request = youtube.playlists().list("snippet,contentDetails");
        PlaylistListResponse response = request.setMaxResults(25L)
                .setMine(true)
                .execute();

        return response.getItems();
    }

    public Playlist getPlaylistLocalization(String playlistId, String language) throws IOException {
        // Call the YouTube Data API's playlists.list method to retrieve playlists.
        PlaylistListResponse playlistListResponse = youtube.playlists().
                list("snippet").setId(playlistId).set("hl", language).execute();

        // Since the API request specified a unique playlist ID, the API
        // response should return exactly one playlist. If the response does
        // not contain a playlist, then the specified playlist ID was not found.
        List<Playlist> playlistList = playlistListResponse.getItems();
        if (playlistList.isEmpty()) {
            System.out.println("Can't find a playlist with ID: " + playlistId);
            return null;
        }
        Playlist playlist = playlistList.get(0);

        // Print information from the API response.
        System.out.println("\n================== Playlist ==================\n");
        System.out.println("  - ID: " + playlist.getId());
        System.out.println("  - Title(" + language + "): " +
                playlist.getLocalizations().get(language).getTitle());
        System.out.println("  - Description(" + language + "): " +
                playlist.getLocalizations().get(language).getDescription());
        System.out.println("\n-------------------------------------------------------------\n");

        return playlist;
    }

    public PlaylistItemListResponse getItemsInPlaylist(String playlistID) throws IOException {
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        Credential credential = YoutubeAuthenticationService.authorize(scopes, "playlistupdates");

        youtube = new YouTube.Builder(YoutubeAuthenticationService.HTTP_TRANSPORT, YoutubeAuthenticationService.JSON_FACTORY, credential)
                .setApplicationName("youtube-cmdline-playlistupdates-sample")
                .build();

        YouTube.PlaylistItems.List request = youtube.playlistItems().list("snippet,contentDetails");
        PlaylistItemListResponse response = request.setMaxResults(150L)
                .setPlaylistId(playlistID)
                .execute();
        return response;
    }

    public PlaylistItemListResponse getItemsInPlaylistOffset(String playlistID, String nextPageToken) throws IOException {
        List<String> scopes = Lists.newArrayList("https://www.googleapis.com/auth/youtube");

        Credential credential = YoutubeAuthenticationService.authorize(scopes, "playlistupdates");

        youtube = new YouTube.Builder(YoutubeAuthenticationService.HTTP_TRANSPORT, YoutubeAuthenticationService.JSON_FACTORY, credential)
                .setApplicationName("youtube-cmdline-playlistupdates-sample")
                .build();

        YouTube.PlaylistItems.List request = youtube.playlistItems().list("snippet,contentDetails").setPageToken(nextPageToken);
        PlaylistItemListResponse response = request.setMaxResults(150L)
                .setPlaylistId(playlistID).setPageToken(nextPageToken)
                .execute();

        return response;

    }

    public String getYoutubeTrackName(PlaylistItem item){
            PlaylistItemSnippet snippet = (PlaylistItemSnippet) item.get("snippet");
            return snippet.getTitle();
    }

    public String getYoutubeVideoId(PlaylistItem item){
        PlaylistItemContentDetails contentDetails = item.getContentDetails();
        return contentDetails.getVideoId();
    }

}
