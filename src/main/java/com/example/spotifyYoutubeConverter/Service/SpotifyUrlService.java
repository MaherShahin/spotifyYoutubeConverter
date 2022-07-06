package com.example.spotifyYoutubeConverter.Service;

import lombok.Getter;
import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@Service
@Getter
public class SpotifyUrlService {

    private static final URI redirectUri = SpotifyHttpManager.makeUri("http://localhost:8081/myapi/get-user-code");
    private String userAccessCode = "";

    @Value("${CLIENT_ID}")
    private static String ClientID;

    @Value("${CLIENT_SECRET}")
    private static String ClientSecret;

    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId("e7355e6552e14faea50a3da3aa8f6f8b")
            .setClientSecret("2340b9c6329b48a788b4350d2ed4bd2b")
            .setRedirectUri(redirectUri)
            .build();

    public SpotifyApi getSpotifyApi() {
        return this.spotifyApi;
    }

    public String getAuthorizationUrl() {
        AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi.authorizationCodeUri()
                .scope("ugc-image-upload,user-read-playback-state,user-modify-playback-state,user-read-currently-playing," +
                        "streaming,app-remote-control,user-read-email,user-read-private"
                +       ",playlist-read-collaborative,playlist-modify-public,playlist-read-private,playlist-modify-private," +
                        "user-library-modify,user-library-read,user-top-read,user-read-playback-position" +
                        ",user-read-recently-played,user-follow-read,user-follow-modify")
                .show_dialog(true)
                .build();

        final URI uri = authorizationCodeUriRequest.execute();
        return uri.toString();

    }
    @GetMapping(value = "get-user-code")
    public String getSpotifyUserCode(@RequestParam("code") String userCode, HttpServletResponse response) throws IOException {
        userAccessCode = userCode;

        AuthorizationCodeRequest authorizationCodeRequest = spotifyApi.authorizationCode(userAccessCode).build();

        try {

            final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

            //Sets access and refresh tokens for further spotifyApi Object usage
            spotifyApi.setAccessToken(authorizationCodeCredentials.getAccessToken());
            spotifyApi.setRefreshToken(authorizationCodeCredentials.getRefreshToken()); //Not working

        } catch (ParseException | SpotifyWebApiException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("/userPlaylists");
        return spotifyApi.getAccessToken();


    }


}
