var youtubeBtn = document.getElementById('youtubeBtn');

youtubeBtn.onclick = function() {
    window.location.replace('https://accounts.google.com/o/oauth2/auth?client_id=54787073109-9alg8012fcofs0fa0ssffp0j8dcmhs5s.apps.googleusercontent.com&redirect_uri=http://localhost:8080/Callback&response_type=code&scope=https://www.googleapis.com/auth/youtube');
}