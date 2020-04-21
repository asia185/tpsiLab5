<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="com.example.kramar.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" session="true" %>

<%
  List<Student> students = Collections.emptyList();

  if (session.getAttribute("students") != null) {
    students = (List<Student>) session.getAttribute("students");
  }

%>

<html>
<head>
  <title>Studenty</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>Imię</th>
    <th>Nazwisko</th>
    <th>Email</th>
  </tr>
  </thead>
  <tbody>

  <% for (Student student : students) { %>

  <tr>
    <td><%=student.getFirstName()%></td>
    <td><%=student.getLastName()%></td>
    <td><%=student.getEmail()%></td>
  </tr>

  <% } %>
  </tbody>
</table>
<form action="/students" method="post">
  <label for="firstName">Imię</label>
  <input type="text" name="firstName" id="firstName" value="${sessionScope["firstName"]}">

  <label for="lastName">Nazwisko</label>
  <input type="text" name="lastName" id="lastName" value="${sessionScope["lastName"]}">

  <label for="email">Email</label>
  <input type="text" name="email" id="email" value="${sessionScope["email"]}">

  <input type="submit" value="Dajesz!">
</form>
<div>Liczba wejść: ${sessionScope["visitors"]}</div>
<div><span style="color: red;">${sessionScope["error"]}</span></div>
<%
  session.removeAttribute("firstName");
  session.removeAttribute("lastName");
  session.removeAttribute("email");
  session.removeAttribute("error");
%>
</body>

</html>