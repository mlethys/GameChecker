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
                    <h1 id="gameLibraryHeadline">Short questions fast answers</h1>
                    <div class="questionHeader">
                        <img src="<c:url value="${author.avatarURL}"/>" width="50px" height="50px" align="left"/>
                        <a class="link" href="<c:url value="usrProfile?user=${author.name}"/>">${author.name}</a>
                        <p class="authorsPoints">&#160;Points: ${author.points}</p><br/>
                        <p class="authorsPoints">${question.additionDate}</p>
                    </div>
                    <div class="question">
                        <h2>${question.title}</h2>
                        <p>${question.content}</p>
                        <c:if test="${(loggedUser.name == author.name) || (loggedUser.role.name == 'Admin') || (loggedUser.role.name == 'Moderator') || (loggedUser.role.name == 'Junior Admin')}">
                            <a href="<c:url value="deleteQuestion?id=${question.id}"/>">Delete</a>
                        </c:if>
                        <div id="line"></div>
                        <c:forEach items="${questionComments}" varStatus="i">
                            <div class="questionComment">
                                <p>${questionComments[i.index].content} ~<a class="authorLink" href="<c:url value="usrProfile?user=${questionComments[i.index].member.name}"/>">${questionComments[i.index].member.name}</a></p>
                                <p class="date">${questionComments[i.index].additionDate}</p>
                            </div>
                            <c:if test="${(loggedUser.name == questionComments[i.index].member.name) || (loggedUser.role.name == 'Admin') || (loggedUser.role.name == 'Moderator') || (loggedUser.role.name == 'Junior Admin')}">
                                <a href="<c:url value="deleteComment?id=${questionComments[i.index].id}"/>">Delete</a>
                            </c:if>
                            <div id="line"></div>
                        </c:forEach>
                        <form method="POST" action="addComment?question=${question.title}">
                            <label for="comment">Comment</label><br/>
                            <textarea cols="40" rows="5" name="comment" maxlength="300"></textarea>
                            <input class="formButton" type="submit" value="Send"/>
                        </form>
                    </div>
                    <c:forEach items="${answers}" varStatus="i">
                        <div class="answer">
                            <form class="plusForm" method="POST" action="rateAnswer?id=${answers[i.index].id}&question=${question.title}">
                                <input type="submit" class="formButton" value="+"/>
                            </form>
                            <h3>Answer</h3>
                            <p>${answers[i.index].content} ~<a class="authorLink" href="<c:url value="usrProfile?user=${answers[i.index].member.name}"/>">${answers[i.index].member.name}</a></p>
                            <p class="date">${answers[i.index].additionDate}</p>
                            <c:if test="${(loggedUser.name == answers[i.index].member.name) || (loggedUser.role.name == 'Admin') || (loggedUser.role.name == 'Moderator') || (loggedUser.role.name == 'Junior Admin')}">
                                <a href="<c:url value="deleteAnswer?id=${answers[i.index].id}"/>">Delete</a>
                            </c:if>
                        </div>    
                        <div id="line"></div>
                    </c:forEach>
                    <form method="POST" action="addAnswer?question=${question.title}">
                        <label for="answer">Answer</label><br/>
                        <textarea cols="40" rows="5" name="answer" maxlength="5000"></textarea>
                        <input class="formButton" type="submit" value="Send"/>
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


