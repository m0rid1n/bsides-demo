<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.8/dist/sweetalert2.all.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@8/dist/sweetalert2.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        * {
            font-family: sans-serif;
        }

        body {
            background: rgba(0, 0, 0, 0.9);
        }

        #main-section {
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -100px;
            margin-left: -250px;
            width: 500px;
            height: 200px;
            border: 4px dashed #fff;
        }

        #main-section p {
            width: 100%;
            height: 100%;
            text-align: center;
            line-height: 170px;
            color: #ffffff;
            font-family: Arial;
        }

        #main-section input {
            position: absolute;
            margin: 0;
            padding: 0;
            width: 100%;
            height: 100%;
            outline: none;
            opacity: 0;
        }

        #main-section button {
            margin: 0;
            color: #fff;
            background: #16a085;
            border: none;
            width: 508px;
            height: 35px;
            margin-top: -20px;
            margin-left: -4px;
            border-radius: 4px;
            border-bottom: 4px solid #117A60;
            transition: all .2s ease;
            outline: none;
        }

        #main-section button:hover {
            background: #149174;
            color: #0C5645;
        }

        #main-section button:active {
            border: 0;
        }

        img {
            max-width: 100%;
            max-height: 100%;
            display: block;
            margin: 0 auto;
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
            color: white;
        }

        .fa-linkedin {
            color: white;
        }

        .fa-rss {
            color: white;
        }

        a {
            color: #16a085;
            font-weight: 600;
            text-decoration: none;
            cursor: pointer;
        }
    </style>
    <script>
      function post() {
        const Toast = Swal.mixin({
          toast: true,
          position: 'top-end',
          showConfirmButton: false,
          timer: 15000,
          timerProgressBar: true,
          didOpen: (toast) => {
            toast.addEventListener('mouseenter', Swal.stopTimer)
            toast.addEventListener('mouseleave', Swal.resumeTimer)
          }
        })

        var http = new XMLHttpRequest();
        var url = 'http://localhost:1337/file2';
        var formData = new FormData();
        formData.append("file", document.getElementById("fileField").files[0]);
        http.open("POST", url, true);
        http.onreadystatechange = function () {
          if (this.readyState === 4) {
            if (this.status === 200) {
              Toast.fire({
                icon: 'success',
                title: 'Success',
                // footer: '<a href=\"http://localhost:1337/file/' + this.responseText + '>Download</a>'
                footer: '<a href="http://localhost:1337/file/' + this.responseText + "/\"" + '>Download</a>'
              })
            } else {
              Swal.fire({
                icon: 'error',
                title: 'Invalid file!',
                text: this.responseText,
              })
            }
          }
        };
        http.send(formData);
      }

      function displayAttributes() {
        let file = document.getElementById('fileField');
        let fileName = file.files.item(0).name;
        let fileSize = file.files.item(0).size;
        let fileType = file.files.item(0).type;
        let messageBox = document.getElementById('messageArea');
        messageBox.innerText = fileName + ", " + (fileSize / 1000).toFixed(2) + " kB, " + fileType;
      }

    </script>
    <title>BSides Demo</title>
</head>
<body>

<%--<img src="https://dannyshblog.b-cdn.net/logo.png">--%>
<div id="main-section">
    <input type="file" id="fileField" name="file" onchange="displayAttributes()"/>
    <p id="messageArea">Drag your files here or click in this area.</p>
    <button onclick="post()">Upload</button>
</div>
<div class="footer">
    <a href="#" class="fa fa-twitter"></a>
    @dnn13_
    <a href="#" class="fa fa-linkedin"></a>
    🧙 Dan Ny
<%--    <a href="#" class="fa fa-rss"></a>--%>
<%--    https://dnny.sh--%>
</div>
</body>
</html>
