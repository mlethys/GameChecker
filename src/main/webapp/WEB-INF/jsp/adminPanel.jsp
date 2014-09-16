<%-- 
    Document   : adminPanel
    Created on : 2014-09-16, 19:27:06
    Author     : mlethys
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Game Checker Service</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:url value="/resources/css/main.css" />" rel="stylesheet">
        <link href="<c:url value="/resources/css/jquery.ratings.css" />" rel="stylesheet">
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.3/jquery.min.js"></script>
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
            </div>  
            <img class="sliderImg" alt="img" src="<c:url value="/resources/images/image1.jpg"/>" id="slideshow">
            <div id="preload">
                
            </div>
            <div id="mainBody">
                <div id="newsContainer" class="mainBody">
                    <h1>Administration Panel</h1>
                    <form method="POST" action="deleteUser">
                        <select type="text" name="users">
                            <c:forEach items="${members}" varStatus="i">
                                <option>${members[i.index].name}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Remove member"/>
                    </form>
                    <form method="POST" action="changeUserName">
                        <select type="text" name="users">
                            <c:forEach items="${members}" varStatus="i">
                                <option>${members[i.index].name}</option>
                            </c:forEach>
                        </select>
                        <input type="text" name="newUsername"/>
                        <input type="submit" value="Change username"/>
                    </form>
                    <form method="POST" action="deleteAvatar">
                        <select type="text" name="users">
                            <c:forEach items="${members}" varStatus="i">
                                <option>${members[i.index].name}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Delete avatar"/>
                    </form>
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
