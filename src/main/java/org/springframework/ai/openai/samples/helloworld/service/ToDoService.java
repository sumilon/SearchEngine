package org.springframework.ai.openai.samples.helloworld.service;


import org.springframework.ai.openai.samples.helloworld.model.ToDo;

import java.util.List;

public interface ToDoService {
    List<ToDo> getAllTodos(String name);
    ToDo getToDoById(String id);
    ToDo addOrUpdateToDo(ToDo todo);
    void deleteToDoById(String id);
}
