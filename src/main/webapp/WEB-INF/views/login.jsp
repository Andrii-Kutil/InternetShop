<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id="is_logged" scope="request" type="java.lang.Integer"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
Welcome to login page!
<c:if test = "${is_logged == 0}">
<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="container">
        <h2>Login</h2>
        <p>Please fill in this form to sign into account.</p>
        <hr>

        <label for="username"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" id="username" name="username" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" id="psw" name="psw" required>
        <hr>

        <button type="submit">Login</button>
        <h4 style="color: red"><b><i>${errorMsg}</i></b></h4>
    </div>

    <div class="container signin">
        <p>Don't have an account? <a href="${pageContext.request.contextPath}/registration">Sign up</a></p>
    </div>
</form>
</c:if>
<c:if test = "${is_logged == 1}">
    <h3><a href="${pageContext.request.contextPath}/logout">Logout</a></h3>
</c:if>
<h3><a href="${pageContext.request.contextPath}/index">Return to Main Page</a></h3>
</body>
</html>
