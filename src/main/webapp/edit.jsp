<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
<title>Student Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
    <header>
    		<nav class="navbar navbar-expand-md navbar-dark"
    			style="background-color: tomato">
    			<h1> Student Management App </h1>

    			<ul class="navbar-nav">
    				<li><a href="welcome.jsp"
    					class="nav-link">Users</a></li>
    			</ul>
    		</nav>
    </header>
    </br>
    <div class="container col-md-5">
        <div class="card">
            <div class="card-body">
                <form action="update" method="post">
                    <input type="hidden" name="id" value="${user.id}" />
                    <label>Change Username:</label>
                    <input type="text" name="name" value="<c:out value='${user.name}' />" class="form-control"/> </br>
                    <label>Change Email Address:</label>
                    <input type="email" name="email" value="<c:out value='${user.email}' />"  class="form-control"/></br>
                    <button type="submit" class="btn btn-success">Save</button>

                </form>
            </div>
        </div>
    </div>
</body>
</html>