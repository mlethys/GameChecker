<%-- 
    Document   : encyclopedia
    Created on : 2014-09-10, 20:04:04
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
        <script src="<c:url value="/resources/js/jquery.ratings.js" />"></script>
        <script src="<c:url value="/resources/js/example.js" />"></script>
        <script src="<c:url value="/resources/js/filter.js" />"></script>
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
                     <div class="filtersContainer">
                        <form id="registerForm" method="POST" action="filter">
                            <p>Game name</p>
                            <input type="text" name="gameName"/>
                            <p>Popularity</p>
                            <input type="radio" name="popularity[]" value="low"/>Low
                            <input type="radio" name="popularity[]" value="medium"/>Medium
                            <input type="radio" name="popularity[]" value="high"/>High
                            <p>Rate</p>
                            <select type="text" name="rateFrom">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                            <label for="rate" >-</label>
                            <select type="text" name="rateTo">
                                <option>1</option>
                                <option>2</option>
                                <option>3</option>
                                <option>4</option>
                                <option>5</option>
                            </select>
                            <p>Release day</p>
                            <label for="from">From</label>
                            <input type="text" maxlength="4" name="from" class="datesInput" onkeypress="return isNumber(event)"/>
                            <label for="to">To</label>
                            <input type="text" maxlength="4" name="to" class="datesInput" onkeypress="return isNumber(event)"/>
                            <p>Type</p>
                            <select type="text" name="types">
                                <c:forEach items="${gameTypes}" varStatus="i">
                                    <option>${gameTypes[i.index].name}</option>
                                </c:forEach>
                            </select>
                            <p>Others</p>
                            <input type="checkbox" name="others" value="singleplayer"/>Singleplayer<br/>
                            <input type="checkbox" name="others2" value="multiplayer"/>Multiplayer<br/>
                            <input type="checkbox" name="others3" value="free2play"/>Free2Play<br/>
                            <input class="formButton" type="submit" value="Filter!"/>
                        </form>
                    </div>
                    <h1 id="gameLibraryHeadline">Games</h1>
                    <c:forEach items="${games}" varStatus="i">
                        <div class="game"> 
                            <img src="<c:url value="resources/images/game_miniature_default.jpg"/>" align="left"/>
                            <h2><a href="<c:url value="games?game=${games[i.index].name}"/>">${games[i.index].name}</a></h2>
                            <div class="example-2"></div>
                            <p>${games[i.index].description}</p><br/><br/>
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