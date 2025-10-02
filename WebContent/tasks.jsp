<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>Task Manager</title>
    <link rel="stylesheet" type="text/css" href="css/style.css" />
  </head>
  <body>
    <div class="container">
      <h1>Task Management</h1>
      <hr />

      <h2>Add New Task</h2>
      <form action="insert" method="post">
        <input type="text" name="title" placeholder="Task Title" required />
        <select name="status">
          <option value="To Do">To Do</option>
          <option value="In Progress">In Progress</option>
          <option value="Done">Done</option>
        </select>
        <textarea name="description" placeholder="Description"></textarea>
        <button type="submit">Add Task</button>
      </form>

      <hr />

      <h2>Task List (Basic Reporting)</h2>
      <table>
        <tr>
          <th>Title</th>
          <th>Status</th>
          <th>Actions</th>
        </tr>
        <c:forEach var="task" items="${listTask}">
          <tr>
            <td><c:out value="${task.title}" /></td>
            <td><c:out value="${task.status}" /></td>
            <td>
              <a href="edit?id=<c:out value='${task.id}' />">Edit</a>
              &nbsp;&nbsp;&nbsp;
              <a
                href="delete?id=<c:out value='${task.id}' />"
                onclick="return confirm('Are you sure?')"
                >Delete</a
              >
            </td>
          </tr>
        </c:forEach>
      </table>
    </div>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  </body>
</html>
