<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>BSides Demo</title>
</head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    * {
        font-family: sans-serif;
    }

    body {
        background: rgba(0, 0, 0, 0.9);
    }

    .vertical-menu {
        /*width: 75%; !* Set a width if you like *!*/
    }

    .vertical-menu a {
        background-color: #eee; /* Grey background color */
        color: black; /* Black text color */
        display: block; /* Make the links appear below each other */
        padding: 12px; /* Add some padding */
        text-decoration: none; /* Remove underline from links */
        text-align: center;
    }

    .vertical-menu a:hover {
        background-color: #ccc; /* Dark grey background on mouse-over */
    }

    .vertical-menu a.active {
        background-color: #16a085; /* Add a green color to the "active/current" link */
        color: white;
    }

    .container {
        height: 200px;
        position: relative;
    }

    .center {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 200px;
        margin-top: 15%;
    }

    .footer {
        position: fixed;
        left: 0;
        bottom: 0;
        width: 100%;
        background-color: #16a085;
        color: white;
        text-align: center;
    }

    .fa {
        padding: 5px;
        font-size: 30px;
        width: 30px;
        text-align: center;
        text-decoration: none;
        /*margin: 5px 2px;*/
        border-radius: 50%;
    }

    .fa:hover {
        opacity: 0.7;
    }

    .fa-twitter {
        /*background: #55ACEE;*/
        color: white;
    }

    .fa-linkedin {
        /*background: #007bb5;*/
        color: white;
    }

    .fa-rss {
        color: white;
    }
</style>
<body>
<div class="container">
    <div class="center">
        <div class="vertical-menu">
            <img src="https://dannyshblog.b-cdn.net/logo.png">
            <a href="#" class="active">BSides SDLC Demo</a>
            <a href="level-1">Insecure ( Level 1 )</a>
            <a href="level-2">Secure ( Level 2 )</a>
            <a href="level-3">Secure* ( Level 3 )</a>
        </div>
    </div>
</div>
<div class="footer">
    <a href="#" class="fa fa-twitter"></a>
    @dnn13_
    <a href="#" class="fa fa-linkedin"></a>
    🧙 Dan Ny (dnny@tutanota.com)
    <%--    <a href="#" class="fa fa-rss"></a>--%>
    <%--    https://dnny.sh--%>
</div>
</body>
</html>
