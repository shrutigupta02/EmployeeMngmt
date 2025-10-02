<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Task</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <h1>Edit Task</h1>
        <hr>
        <form action="update" method="post">
            <input type="hidden" name="id" value="<c:out value='${task.id}' />" />

            <label>Title:</label>
            <input type="text" name="title" value="<c:out value='${task.title}' />" required>
            
            <label>Status:</label>
            <select name="status">
                <option value="To Do" ${task.status == 'To Do' ? 'selected' : ''}>To Do</option>
                <option value="In Progress" ${task.status == 'In Progress' ? 'selected' : ''}>In Progress</option>
                <option value="Done" ${task.status == 'Done' ? 'selected' : ''}>Done</option>
            </select>
            
            <label>Description:</label>
            <textarea name="description"><c:out value='${task.description}' /></textarea>
            
            <button type="submit">Update Task</button>
        </form>
        <br>
        <a href="list">Back to Task List</a>
    </div>
</body>
</html>