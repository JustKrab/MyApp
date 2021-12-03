
$(document).ready(function(){
    $("#filter").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#reviews tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});


if ( window.history.replaceState ){
    window.history.replaceState( null, null, window.location.href );
}
function myFunction() {
    var element = document.getElementById("toggle");
    element.classList.toggle("bootstrap");
}

$("#est").click(function(){
    $('#estimate').hide();
});

$(document).ready(function () {
    $("#locales").change(function () {
        var selectedOption = $("#locales").val();
        if (selectedOption !== "") {
            window.location.replace('?lang=' + selectedOption);
        }
    })
})