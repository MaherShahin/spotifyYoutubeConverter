
function LandingPage() {
    const getSpotifyUserLogin = () => {
        fetch("http://localhost:8080/myapi/login")
        .then((response) => response.text())
        .then(response => {
            window.location.replace(response);
        })

    }
}
