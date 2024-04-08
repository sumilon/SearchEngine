<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To Do List</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Custom CSS for alignment */
        .todo-item {
            display: flex;
            justify-content: space-between;
            margin-bottom: 10px;
        }
        .todo-buttons {
            display: flex;
            gap: 5px;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <!-- Button trigger modal for adding ToDo -->
        <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addTodoModal">
            Add ToDo
        </button>

        <!-- Modal for adding ToDo -->
        <div class="modal fade" id="addTodoModal" tabindex="-1" aria-labelledby="addTodoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addTodoModalLabel">Add ToDo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="/add" method="post">
                            <div class="mb-3">
                                <label for="title" class="form-label">Title:</label>
                                <input type="text" class="form-control" id="title" name="title" required>
                            </div>
                            <div class="mb-3">
                                <label for="description" class="form-label">Description:</label>
                                <input type="text" class="form-control" id="description" name="description" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Add ToDo</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal for editing ToDo -->
        <div class="modal fade" id="editTodoModal" tabindex="-1" aria-labelledby="editTodoModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTodoModalLabel">Edit ToDo</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form id="editTodoForm" action="" method="post">
                            <div class="mb-3">
                                <label for="editTitle" class="form-label">Title:</label>
                                <input type="text" class="form-control" id="editTitle" name="title">
                            </div>
                            <div class="mb-3">
                                <label for="editDescription" class="form-label">Description:</label>
                                <input type="text" class="form-control" id="editDescription" name="description">
                            </div>
                            <button type="submit" class="btn btn-primary">Save</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Display ToDo items -->
        <div class="mt-3">
            <h2>To Do List</h2>
            <div class="todo-list">
                <c:forEach items="${todos}" var="todo">
                    <div class="todo-item">
                        <div>${todo.title} - ${todo.description}</div>
                        <div class="todo-buttons">
                            <!-- Button for editing ToDo -->
                            <button type="button" class="btn btn-primary edit-todo-btn" data-bs-toggle="modal" data-bs-target="#editTodoModal" data-id="${todo.id}" data-title="${todo.title}" data-description="${todo.description}">
                                Edit
                            </button>
                            <!-- Button for deleting ToDo -->
                            <form action="/delete/${todo.id}" method="post">
                                <button type="submit" class="btn btn-danger">Delete</button>
                            </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <!-- Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        document.addEventListener('DOMContentLoaded', function () {
            var editButtons = document.querySelectorAll('.edit-todo-btn');

            editButtons.forEach(function (button) {
                button.addEventListener('click', function () {
                    var id = button.getAttribute('data-id');
                    var title = button.getAttribute('data-title');
                    var description = button.getAttribute('data-description');

                    var editTitleInput = document.getElementById('editTitle');
                    var editDescriptionInput = document.getElementById('editDescription');
                    var editTodoForm = document.getElementById('editTodoForm');

                    editTitleInput.value = title;
                    editDescriptionInput.value = description;
                    editTodoForm.action = '/edit/' + id;
                });
            });
        });
    </script>
</body>
</html>
