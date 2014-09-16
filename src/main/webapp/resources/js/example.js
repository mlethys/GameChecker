$(document).ready(function() {
  $('.example-2').ratings(5).bind('ratingchanged', function(event, data) {
    $('.example-rating-2').text(data.rating);
  });
});

function addRate() {
    $.ajax({
        url : 'rate.html',
        success : function(data) {
            $('#example-rating-2').html(data);
        }
    });
}