package com.example.spotifyYoutubeConverter.Controller;

import com.example.spotifyYoutubeConverter.Service.SpotifyService.PlaylistTracksService;
import com.example.spotifyYoutubeConverter.Service.SpotifyService.UserPlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.CreatePlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.InsertItemInPlaylistService;
import com.example.spotifyYoutubeConverter.Service.YoutubeService.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.PlaylistTrack;
import se.michaelthelin.spotify.model_objects.specification.Track;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PlaylistTracksController {

    @Autowired
    PlaylistTracksService playlistTracksService;

    @Autowired
    CreatePlaylistService createPlaylistService;

    @Autowired
    UserPlaylistService userPlaylistService;

    @Autowired
    InsertItemInPlaylistService insertItemInPlaylistService;

    @Autowired
    SearchService searchService;

    @GetMapping("/getTracksForPlaylist/{playlistId}")
    public String getPlaylistTracks(@PathVariable String playlistId, Model model) throws IOException {


        List<Track> tracks = new ArrayList<>();
        for (PlaylistTrack p : playlistTracksService.getPlaylistTracks(playlistId)) {
            tracks.add((Track) p.getTrack());

        }
        model.addAttribute("tracks", tracks);

//        userPlaylistService.getUserPlaylists().get

//        String youtubePlaylistId = createPlaylistService.createPlaylist("Test","Copied from Spotify");
//        System.out.println(youtubePlaylistId);
//////
//            for (PlaylistTrack p: playlistTracksService.getPlaylistTracks(playlistId)) {
//                Track t = (Track) p.getTrack();
//                t.getArtists()[0].getName();
//                System.out.println((t.getAlbum().getName());
//    //        }
        return "tracksPlaylist";
    }

}
