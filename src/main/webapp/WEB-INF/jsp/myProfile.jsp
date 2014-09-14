<%-- 
    Document   : myProfile
    Created on : 2014-09-14, 11:39:33
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
        <script src="<c:url value="/resources/js/slider.js" />"></script>
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
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
                    <a href="<c:url value="loggedIndex"/>"><img src="<c:url value="/resources/images/logo1.png"/>" alt="logo" 
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
                    <div id="editProfilPanel">
                        <form id="editProfileForm" method="POST" action="editProfile">
                            <label for="newUsername">New username</label><br/>
                            <input type="text" name="newUsername"/><br/>
                            <label for="newUsername">New Email</label><br/>
                            <input type="text" name="newEmail"/><br/>
                            <input class="formButton" type="submit" value="Change"/>
                        </form>
                    </div>
                    <div class="profile"> 
                        <h1>${myLogin}</h1>
                        <img src="<c:url value="${usersAvatar}"/>" align="left"/>
                        <p>&#160;&#160;Email: ${myEmail}</p>
                        <p>&#160;&#160;Register date: ${myRegisterDate}</p>
                        <p>&#160;&#160;Age: ${myAge}</p>
                        <p>&#160;&#160;Points: ${myPoints}</p>
                        <br/>
                        <br/>
                        <h2>Hardware:</h2>
                        <p>&#160;&#160;CPU: TODO</p>
                        <p>&#160;&#160;GPU: TODO</p>
                        <p>&#160;&#160;Memory: TODO</p>
                        <br/>
                        <br/>
                        <h3>${myLogin} games library:</h3>
                        <a href="<c:url value="library?user=${myLogin}"/>">&#160;&#160;Library</a>
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

