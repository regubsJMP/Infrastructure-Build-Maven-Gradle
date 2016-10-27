<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>person system</title>
    </head>
    <body>
    	<h1>Persons List</h1>
	    <table border="2" width="70%" cellpadding="2">
	        <tr>
	            <th>Id</th>
	            <th>First Name</th>
	            <th>Last Name</th>
	            <th>Age</th>
	            <th>Address</th>
	            <th>Salary</th>
	            <th>Edit</th>
                <th>Delete</th>
	        </tr>
            <c:forEach var="person" items="${list}">
                <tr>
                    <td>${person.id}</td>
                    <td>${person.firstName}</td>
                    <td>${person.lastName}</td>
                    <td>${person.age}</td>
                    <td>${person.address}</td>
                    <td>${person.salary}</td>
                    <td><a href="editPerson/${person.id}">Edit</a></td>
                    <td><a href="deletePerson/${person.id}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <br/>
        <a href="addPersonForm">Add New Person</a>
    </body>
</html>