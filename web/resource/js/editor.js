var input = null;
var type = null;
var converter = new showdown.Converter({ tables: true });
var showAuto = null;
window.onload = function () {
    window.addEventListener('keydown', Input);
    Update();
    GetTypes();
}

//获取type
function GetTypes() {
    $.ajax({
        url: GetType(),
        type: "get",
        dataType: "json"
    }).done(function (output) {
        var typeselect = document.getElementById("typeSelect");
        for (var i = 0; i < output.length; i++) {
            var str = "<option value='" + output[i].type + "'>" + output[i].type + "</option>";
            typeselect.innerHTML += str;
        }
        typeselect.innerHTML +="<option value=‘新增加’>新增加</option>";
        typeSelect();
    }).fail(function (xhr, status) {
        console.log(status);
    });
}

//新增加type
function typeSelect() {
    var selected = document.getElementById("typeSelect").options[document.getElementById("typeSelect").selectedIndex].text;
    if (selected == '新增加') {
        document.getElementById("type").style.display = "block";
        type = "新增加";
    }
    else {
        document.getElementById("type").style.display = "none";
        type=null;
    }
}

//从链接获取文章名
function getvl(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    var result = window.location.search.substr(1).match(reg);
    return result ? decodeURIComponent(result[2]) : null;
}

//修改界面
function Update() {
    var tilte = getvl("article");
    if (tilte != null) {
        document.getElementById("title").value = tilte;
        document.getElementById("del").style.display = "block";
        $.ajax({
            url: GetCloud() + tilte + "&&Url=ArtUrl",
            type: "get",
            dataType: "html"
        }).done(function (output) {
            document.getElementById("article").value = output;;
            ShowDown();
        }).fail(function (xhr, status) {
            console.log(status);
        });
    }
}

//防抖函数
var timeout = null;
function antishake() {
    if (timeout != null) {
        clearTimeout(timeout);
    }
    timeout = setTimeout("ShowDown()", 800);
}

//检测输入
function Input() {
    var newinput = document.getElementById("article").value;
    input = newinput;
    antishake()
}

//预览
function ShowDown() {
    input = document.getElementById("article").value;
    var html = converter.makeHtml(input);
    document.getElementById("showdown").innerHTML = html;
}

//提交
function sub() {
    input = document.getElementById("article").value;
    var title = document.getElementById("title").value;
    if (input == "" || title == "") {
        alert("输入为空！");
    }
    else {
        if (type == "新增加") {
            type = document.getElementById("type").value;
        }
        else {
            type = document.getElementById("typeSelect").options[document.getElementById("typeSelect").selectedIndex].text;
        }
        $.ajax({
            url: GetSub(),
            type: "post",
            data: { "ArtName": title, "ArtText": input, "Type": type },
            dataType: "json"
        }).done(function () {
            window.open(GetArt() + title);
        }).fail(function (xhr, status) {
            console.log(status);
        });
    }
}

//删除
function del() {
    if (confirm("确认删除？")) {
        $.ajax({
            url: GetDel(),
            type: "post",
            data: { "ArtName": title },
            dataType: "json"
        }).done(function () {
            window.open(GetArt() + "index");
        }).fail(function (xhr, status) {
            console.log(status);
        });
    }
}

//黑白主题切换
function DayAndNight() {
    var select = document.getElementById("SetImg");
    var head = document.getElementsByTagName("head")[0];
    var color = new Array();
    var str = head.innerHTML;
    if (select.getAttribute("alt") == "night") {
        //网页背景颜色
        color[0] = "#262622";
        //编写、预览、题目栏栏头字体颜色
        color[1] = "aliceblue";
        //编写、预览、题目栏背景颜色
        color[2] = "#22211f";
        //编写、预览、题目栏字体颜色
        color[3] = "#e4d6bd";
        //编写、预览、题目栏边框颜色
        color[4] = "#494843";
        //提交按钮背景颜色
        color[5] = "#496f70";
        //提交删除按钮、类别选择栏字体颜色
        color[6] = "aliceblue";
        //底部声明颜色
        color[7] = "#bbbbbb";
        //类别选择栏背景颜色
        color[8] = "#2a2a2a";

        //博客图标
        document.getElementById("AblogImg").src = "resource/img/Ablog-night.svg";

        //网页设置图标
        document.getElementById("SetImg").src = "resource/img/set-night.svg";

        select.setAttribute('alt', 'day');

        str = str.substring(0, str.indexOf('<link rel="stylesheet" href="resource/css/github-markdown-day.css">'));
        str = str + '<link rel="stylesheet" href="resource/css/github-markdown-night.css">';
    }
    else if (select.getAttribute("alt") == "day") {
        //网页背景颜色
        color[0] = "#f2f8f2";
        //编写、预览、题目栏栏头字体颜色
        color[1] = "#313131";
        //编写、预览、题目栏背景颜色
        color[2] = "#ecf3ee";
        //编写、预览、题目栏字体颜色
        color[3] = "#3a3b3c";
        //编写、预览、题目栏边框颜色
        color[4] = "#696969";
        //提交按钮背景颜色
        color[5] = "#719596";
        //提交删除按钮、类别选择栏字体颜色
        color[6] = "#16181a";
        //底部声明颜色
        color[7] = "black";
        //类别选择栏背景颜色
        color[8] = "#f2f9f1";

        //博客图标
        document.getElementById("AblogImg").src = "resource/img/Ablog-day.svg";

        //网页设置图标
        document.getElementById("SetImg").src = "resource/img/set-day.svg";

        select.setAttribute('alt', 'night');

        str = str.substring(0, str.indexOf('<link rel="stylesheet" href="resource/css/github-markdown-night.css">'));
        str = str + '<link rel="stylesheet" href="resource/css/github-markdown-day.css">';
    }
    var style = "<style>#article-ti p,#article-sh p,#article-md p{color: " + color[1] + ";}#article,#showdown,#title{color:" + color[3] + ";border-color: " + color[4] + ";}#sub,#del{background-color: " + color[5] + ";color: " + color[6] + ";}.bottom{color:" + color[7] + ";}#typeSelect{color: " + color[6] + ";}</style>";
    document.getElementsByTagName("body")[0].style.backgroundColor = color[0];
    document.getElementById("article").style.backgroundColor = color[2];
    document.getElementById("showdown").style.backgroundColor = color[2];
    document.getElementById("title").style.backgroundColor = color[2];
    document.getElementById("type").style.backgroundColor = color[2];
    document.getElementById("typeSelect").style.backgroundColor = color[8];
    setTimeout(function (str, style, head) {
        head.innerHTML = str + style;
    }, 300, str, style, head);
}