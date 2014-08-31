$(document).ready(function(){
    $("#loginButton").click(function() {
        $("#newsContainer").load("login.html #loginContainer");
    });
    $("#registerButton").click(function() {
        $("#newsContainer").load("register.jsp #registerContainer");
    });
    $("#about").click(function() {
       $("#newsContainer").load("about.html #aboutContainer");
    });
});
