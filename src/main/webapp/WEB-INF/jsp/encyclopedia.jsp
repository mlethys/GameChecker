<%-- 
    Document   : encyclopedia
    Created on : 2014-09-10, 20:04:04
    Author     : mlethys
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Game Checker Service</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/jquery.raty.css" />" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
        <script src="<c:url value="/resources/js/jquery.raty.js" />"></script>
        <script src="<c:url value="/resources/js/slider.js" />"></script>
    </head>
    <body bgcolor="e5e5e5">
        <div id="container">
            <div id="loginBar">
                <div id="registerButton" class="loginRegButton">
                    <a href="<c:url value="logout"/>">Logout</a>
                </div>
                <div id="loginButton" class="loginRegButton">
                    <a href="<c:url value="profile"/>">Profile</a>
                </div>
            </div>
            <div id="menuBar">
                <div id="home">
                    <a href="<c:url value="/"/>"><img src="<c:url value="/resources/images/logo1.png"/>" alt="logo" 
                                              onmouseover="this.src='<c:url value="/resources/images/logo2.png"/>'"
                                              onmouseout="this.src='<c:url value="/resources/images/logo1.png"/>'"/></a>
                </div>
                <div id="gameEnc" class="menuButton" onclick="location.href='encyclopedia.html';">
                    <p class="menuText">Encyclopedia</p>
                </div>
                <div id="sqfa" class="menuButton" onclick="location.href='sqfa.html';">
                    <p class="menuText">SQFA</p>
                </div>
                <div id="about" class="menuButton">
                    <p class="menuText">About</p>
                </div>
                <div id="library" class="menuButton">
                    <p class="menuText">My library</p>
                </div>
            </div>  
            <img class="sliderImg" alt="img" src="<c:url value="/resources/images/image1.jpg"/>" id="slideshow">
            <div id="preload">
                
            </div>
            <div id="mainBody">
                <div id="newsContainer" class="mainBody">
                    <h1 class="headline">Games</h1>
                    <div class=""game><p style="float: left;"><img src="<c:url value="resources/images/game_miniature_default.jpg"/>" height="150px" width="150px" border="1px"/></p>
                        <h2>Game name</h2>
                        <p>Game description</p>
                    </div>
                </div>
                <div id="addsContainer" class="mainBody">
                    <img src="<c:url value="/resources/images/sampleAdd.jpg"/>" alt="add" class="add"/>
                </div>
            </div>
        </div>
        <footer class="footer">
            <p>© 2014 GameChecker Team. All Rights Reserved.</p>
        </footer>
    </body>
</html>