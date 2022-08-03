package com.example.spotifyYoutubeConverter.Service.SpotifyService;

import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeCreatePlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeInsertItemInPlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.YoutubeSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;

import java.io.IOException;

@Service
public class SpotifyYoutubeConverterService {

    @Autowired
    SpotifyUserPlaylistService spotifyUserPlaylistService;

    @Autowired
    SpotifyPlaylistTracksService spotifyPlaylistTracksService;

    @Autowired
    SpotifyGetPlaylistService spotifyGetPlaylistService;

    @Autowired
    YoutubeSearchService youtubeSearchService;

    @Autowired
    YoutubeCreatePlaylistService youtubeCreatePlaylistService;

    @Autowired
    YoutubeInsertItemInPlaylistService youtubeInsertItemInPlaylistService;



    public void convertToYoutube(String playlistId) throws IOException {

    //     Input -> Spotify Playlist (Take name and tracks from it)
    // Create a new playlist with the same name
    // For each track in the playlist, search Youtube for the same song and insert its uri into the newly created playlist

        String youtubePlaylistId = youtubeCreatePlaylistService.createPlaylist(
                spotifyGetPlaylistService.getPlaylist(playlistId).getName(),"Created by My App");

//        String youtubePlaylistId = "PLAbLaqtJcMjNerJ-k6j2q6l8ztqWtYU5_";

        for (PlaylistTrack t : spotifyPlaylistTracksService.getPlaylistTracks(playlistId)) {
            String videoId = youtubeSearchService.getVideos(t.getTrack().getName()).get(0).getId().getVideoId();
            System.out.println(youtubeSearchService.getVideos(t.getTrack().getName()).get(0).getId().getVideoId()
                    + " Name : " + youtubeSearchService.getVideos(t.getTrack().getName()).get(0).getSnippet().getTitle());
            youtubeInsertItemInPlaylistService.insertItemInPlaylist(youtubePlaylistId,videoId);
        }

    }

}
