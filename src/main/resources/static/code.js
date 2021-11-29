// $("#file-1").fileinput({
//     theme: 'fa',
//     uploadUrl: '#',
//     allowedFileExtensions: ['jpg', 'png'],
//     overwriteInitial: false,
//     maxFileSize:2000,
//     slugCallback: function (filename) {
//         return filename.replace('(', '_').replace(']', '_');
//     }
// });

$(document).ready(function(){
    $("#filter").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#reviews tr").filter(function() {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });
});

// $('.switch').click(()=>{
//     document.getElementById('toggle').classList.add('bootstrap')})

function myFunction() {
    var element = document.getElementById("toggle");
    element.classList.toggle("bootstrap");
}

$("#est").click(function(){
    $('#estimate').hide();
});