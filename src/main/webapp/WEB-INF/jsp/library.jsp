<%-- 
    Document   : library
    Created on : 2014-09-16, 10:19:17
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
             
            </div>  
            <img class="sliderImg" alt="img" src="<c:url value="/resources/images/image1.jpg"/>" id="slideshow">
            <div id="preload">
                
            </div>
            <div id="mainBody">
                <div id="newsContainer" class="mainBody">
                    <h1 id="gameLibraryHeadline">${usersLibrary} games library</h1>
                    <div id="searchPanel">
                        <form method="POST" action="searchInLibrary?user=${usersLibrary}">
                            <input type="text" name="gameName"/>
                            <input class="formButton" type="submit" value="Search"/>
                        </form>
                    </div>
                    <c:if test="${(usersLibrary == loggedUser) || (loggedRole == 'Admin') || (loggedRole == 'Junior Admin')}">
                        <div class="filtersContainer">
                            <form method="POST" action="addGame">
                                <select type="text" name="availableGames">
                                    <c:forEach items="${availableGames}" varStatus="i">
                                        <option>${availableGames[i.index].name}</option>
                                    </c:forEach>
                                </select>
                                <input class="formButton" type="submit" value="Add game"/>
                            </form>
                            <form method="POST" action="removeGame">
                                <select type="text" name="usersGames">
                                    <c:forEach items="${usersGamesToRemove}" varStatus="i">
                                        <option>${usersGamesToRemove[i.index].name}</option>
                                    </c:forEach>
                                </select>
                                <input class="formButton" type="submit" value="Remove game"/>
                            </form>
                        </div>
                    </c:if>
                    <c:forEach items="${usersGames}" varStatus="i">
                        <div class="game"> 
                            <img src="<c:url value="resources/images/game_miniature_default.jpg"/>" align="left"/>
                            <h2><a href="<c:url value="games?game=${usersGames[i.index].name}"/>">${usersGames[i.index].name}</a></h2>
                            <p>Release date: ${usersGames[i.index].releaseDate}</p>
                            <p>${usersGames[i.index].description}</p><br/><br/>
                        </div>
                        <br/>
                        <br/>
                    </c:forEach>
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