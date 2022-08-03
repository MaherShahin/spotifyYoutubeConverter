# spotifyYoutubeConverter

Spring Web App utilizing Spotify API & Google Youtube Data API

A webapp where users can login with their spotify and youtube accounts and manage/convert playlists between the different platforms

## Done so far:
- Spotify API Authentication
- Youtube API Authentication
- Convert Youtube playlist to Spotify Playlist - Single Conversion
- Convert Spotify Playlist to Youtube Playlist - Single Conversion
- View and Manage Spotify Playlists and Tracks within them

## Services and Controllers for:
### Spotify:
- Authentication/URL 
- Create playlist
- Get playlist
- Insert playlist
- Get user playlists
- Search for tracks 
- Insert tracks into user playlist
- Convert a single playlist into a youtube playlist

### Youtube:
- Youtube Authentication
- Create playlist
- Get playlist
- Get user playlists
- Search for tracks
- Insert items in user playlist
- Convert a single playlist into a spotify playlist

### Directory Structure

    ├── com.example.spotifyYoutubeConverter                    
    │   ├── Controller          
    |   |     └── SpotifyController #All Controllers for Spotify - including URL and SpotifyToYoutube convert
    |   |     └── YoutubeController #All Controllers for Youtube - including YoutubeToSpotify convert
    │   ├── Model         #Contains only MyTrack which is a simple custom model that holds the name of the track from Spotify & Youtube
    │   └── Service         
    |   ├──   └── SpotifyService # Services for Spotify -> URL-Authentication-GetUser-Get/CreatePlaylist-InsertTracks-Search..etc
    |   ├──   └── YoutubeService # Services for Youtube -> Authentication-Search-CreatePlaylist-GetPlaylists-InsertTracks
    |   SpotifyYoutubeConverterApplication #Main Access Point -> Run this 
    └── ...

## To do:
- Add the possibility for user to convert multiple playlists at once
- Decouple the API authorizations from the current localhost and deploy the project

## How to install:

To build it, you will need to download and unpack the latest (or recent) version of Maven (https://maven.apache.org/download.cgi) and put the mvn command on your path. Then, you will need to install a Java 1.8 (or higher) JDK (not JRE!), and make sure you can run java from the command line. Now you can run 'mvn clean install' and Maven will compile your project.

Run SpotifyYoutubeConverterApplication and access the app through localhost:8080

