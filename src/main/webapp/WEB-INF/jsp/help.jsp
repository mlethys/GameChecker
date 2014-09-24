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
            </div>  
            <img class="sliderImg" alt="img" src="<c:url value="/resources/images/image1.jpg"/>" id="slideshow">
            <div id="preload">
                
            </div>
            <div id="mainBody">
                <div id="newsContainer" class="mainBody">
                <h1>Strona glowna:</h1><br/>
                    <img src="<c:url value="/resources/images/screen1.jpg"/>"/><br/>
                    <p>Opis przycisków:
                    – GameChecker – hiperlink do strony głównej;
                    – Encyklopedia – hiperlink do encyklopedii, wymaga zalogowania;
                    – SQFA – Short Question Fast Answer – hiperlink do systemu pytań i odpowiedzi, wymaga
                    zalogowania;
                    – Login – przenosi do ekranu logowania;
                    – Register – przenosi do ekranu rejestracji;
                    Obrazek jest umieszczony w sliderze i automatycznie zmienia się co kilka sekund.
                    W stopce pojawia się łącze do panelu admina, jeśli jesteśmy zalogowani na użytkownika z
                    odpowiednimi prawami i przejdziemy właśnie na stronę główną.
                    </p><br/>
                <h2>SQFA widok glowny</h2><br/>
                <img src="<c:url value="/resources/images/screen2.jpg"/>"/><br/>
                <p>Wszystko powyżej slidera pozostaje tak samo jak na stronie głównej, ze zmianą przycisku
"Login" na "Profil.
Pod sliderem ukazuje się natomiast powyższe okno. Z którego jesteśmy w stanie dodać
zapytanie SQFA, wyszukać zapytania po nazwie, oraz przeglądać pytania zadane juz wcześniej.
Kliknięcie w tytuł pytania przenosimy sie do jego wątku.</p><br/>
                </div>
                <div id="addsContainer" class="mainBody">
                    <img src="<c:url value="/resources/images/sampleAdd.jpg"/>" alt="add" class="add"/>
                </div>
            </div>
        </div>
        <footer class="footer">
            <p>© 2014 GameChecker Team. All Rights Reserved. </p>
            <c:if test="${memberRole == 'Admin'}">
                <a href="<c:url value="adminPanel"/>">Admin panel</a>
            </c:if>
        </footer>
    </body>
</html>
