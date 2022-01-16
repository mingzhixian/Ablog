var converter = new showdown.Converter({tables: true});
window.onload = function () {
    $.ajax({
        url: "https://cdn.jsdelivr.net/gh/mingzhixian/study@master/README.md",
        type: "get",
        dataType: "html"
    }).done(function (output) {
        ShowDown(output);
    }).fail(function (xhr, status) {
        console.log(status);
    });
}

function ShowDown(output) {
    var html = converter.makeHtml(output);
    document.getElementById("article").innerHTML = html;
}