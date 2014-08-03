$(document).ready(function(){
    $("#loginButton").click(function() {
        $("#newsContainer").load("login.html #loginContainer");
    });
    $("#registerButton").click(function() {
        $("#newsContainer").load("register.html #registerContainer");
    });
});
