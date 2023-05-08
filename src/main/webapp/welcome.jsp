<html>
<head>
<title>Student Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
    <%
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //this page wont be put in cache
        response.setHeader("Expires", "0"); //for proxy servers
        if(session.getAttribute("uname")==null){
            response.sendRedirect("login.jsp");
        }
    %>
    <sql:setDataSource var="db" driver="com.mysql.cj.jdbc.Driver" url="jdbc:mysql://localhost:3306/student_db"
            user="root" password="fearless@2023"/>
    <sql:query var="rs" dataSource="${db}">select * from students</sql:query>
    <h1>Welcome ${uname}</h1></br>
    <h1>Welcome to Student Management System</h1>
    </br>
    <div class="row">
        <div class="container">
            <h3 class="text-center">List of Users</h3>
            <hr>
            <table class="table table-bordered">
            <thead>
            <tr>
                <th>Sr no.</th>
                <th>Name</th>
                <th>Email Address</th>
                <th colspan="2">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="row" items="${rs.rows}">
            <tr>
                <td>${row.id}</td>
                <td>${row.name}</td>
                <td>${row.email}</td>
                <td><a href="edit?id=<c:out value='${row.id}' />">Edit
                </a></td>
                <td>
                   <a href="delete?id=<c:out value='${row.id}' />">Delete</a>
                </td>
            </tr>
            </c:forEach>
            </tbody>
            </table>
        </div>
    </div>

    <form action="logout" >
        <button type="submit">Logout</button>
    </form>
</body>
</html>