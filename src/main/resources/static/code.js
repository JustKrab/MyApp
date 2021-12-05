
$(document).ready(function(){

    $("#filter").live("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#reviews tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });

});

$(document).ready(function(){
    let loc = localStorage.getItem('switch');
    $("a.first").fancybox();
    $("a.two").fancybox();
    $("a.video").fancybox({"frameWidth":520,"frameHeight":400});
    $("a.content").fancybox({"frameWidth":600,"frameHeight":300});

    if (loc === "white") {
        document.getElementById("toggle").classList.toggle("bootstrap");
        document.getElementById("switcher").classList.toggle("btn-dark");
    } else {
        document.getElementById("toggle").classList.toggle("bootstrap-dark")
        document.getElementById("switcher").classList.toggle("btn-light");
    }
});



if ( window.history.replaceState ){
    window.history.replaceState( null, null, window.location.href );
}


function myFunction() {
    document.getElementById("toggle").classList.toggle("bootstrap-dark");
        if (localStorage.getItem("switch")==='dark'||localStorage.getItem("switch")===undefined) {
            localStorage.setItem('switch', 'white')
            console.log("w")
        } else {
            localStorage.setItem('switch', 'dark');
            console.log("d")
        }
    window.location.reload();
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