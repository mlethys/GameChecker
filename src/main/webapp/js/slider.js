/* 
    Created on : 2014-07-27, 14:54:13
    Author     : mlethys
*/
var images = ["images/image1.jpg", 
                "images/image2.jpg",
                "images/image3.jpg"];
var imgNum = 0;
var imgLength = images.length - 1;


function changeImage(direction) {
    imgNum = imgNum + direction;
    if (imgNum > imgLength) {
        imgNum = 0;
    }
    if (imgNum < 0) {
        imgNum = imgLength;
    }
    document.getElementById('slideshow').src = images[imgNum];
    return false;
}
window.setInterval(function() {
    changeImage(1);
}, 3000);

window.onload = function() {
    for (var i = 0; i <= imgLength; i++) {
        var img = document.createElement('img');
        img.src = images[i];
        document.getElementById('preload').appendChild(img);
    }
};