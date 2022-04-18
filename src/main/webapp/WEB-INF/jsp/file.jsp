<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>BSides BG Demo</title>
</head>
<body>
<%
    if (request.getParameter("file") != null) {
        String file = request.getParameter("file");
%>
<jsp:include page='<%="../uploads/"+file%>'/>
<%
    }
%>
</body>
</html>