package com.example.spotifyYoutubeConverter.Service.SpotifyService;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.EnvironmentCapable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.SpotifyHttpManager;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;

@Service
public class SpotifyUrlService  {

    @Value("${spotify.redirect_uri}")
    private String redirectUriString;

    @Value("${spotify.CLIENT_ID}")
    private String ClientID;

    @Value("${spotify.CLIENT_SECRET}")
    private String ClientSecret;

    private String userAccessCode = "";
    private SpotifyApi spotifyApi;

    private URI redirectUri;

    @PostConstruct
    public void postConstruct(){

        redirectUri = SpotifyHttpManager.makeUri(redirectUriString);

        spotifyApi = new SpotifyApi.Builder()
                .setClientId(ClientID)
                .setClientSecret(ClientSecret)
                .setRedirectUri(redirectUri)
                .build();


    }

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



        return spotifyApi.getAccessToken();

    }


}
