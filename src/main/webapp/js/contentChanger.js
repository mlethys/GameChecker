$(document).ready(function(){
    $("#loginButton").click(function() {
        $("#newsContainer").load("login.html #loginContainer");
    });
});
