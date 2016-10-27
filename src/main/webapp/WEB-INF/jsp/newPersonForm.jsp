<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>person system</title>
    </head>
    <body>
		<h1>Add New Employee</h1>
        <form:form method="post" action="save">
      		<table>
        		<tr>
          			<td>First Name: </td>
          			<td><form:input path="firstName"/></td>
         		</tr>
         		<tr>
         			<td>Last Name: </td>
         			<td><form:input path="lastName"/></td>
         		</tr>
         		<tr>
         			<td>Age: </td>
         			<td><form:input path="age"/></td>
         		</tr>
         		<tr>
         			<td>Address: </td>
         			<td><form:input path="address"/></td>
         		</tr>
         		<tr>
          			<td>Salary :</td>
          			<td><form:input path="salary"/></td>
         		</tr>
         		<tr>
          			<td></td>
          			<td><input type="submit" value="Save"/></td>
         		</tr>
        	</table>
        </form:form>
    </body>
</html>