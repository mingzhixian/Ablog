var converter = new showdown.Converter({tables: true});
var tiltle = "README";
var DirTop = 0;
window.onload = function () {
    document.getElementById("Title").innerHTML = tiltle;
    $.ajax({
        url: "https://cdn.jsdelivr.net/gh/mingzhixian/study@master/" + tiltle + ".md",
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

//检测目录是否到达顶部
function IsTop() {
    var scrollTop = document.body.scrollTop;
    var art = document.getElementById("article");
    var dir = document.getElementById("directory");
    if (art.getBoundingClientRect().top < 20 && DirTop === 0) {
        dir.style.position = "fixed";
        dir.style.top = "50px";
        DirTop = 1;
    }
    if (art.getBoundingClientRect().top > 20 && DirTop === 1) {
        dir.style.position = "relative";
        dir.style.top = "0";
        dir.style.right = "0";
        DirTop = 0;
    }
}

//使用 rAF（requestAnimationFrame）触发滚动事件
var ticking = false; // rAF 触发锁

function onScroll() {
    if (!ticking) {
        requestAnimationFrame(realFun);
        ticking = true;
    }
}

function realFun() {
    IsTop();
    ticking = false;
}

// 滚动事件监听
window.addEventListener('scroll', onScroll, false);
