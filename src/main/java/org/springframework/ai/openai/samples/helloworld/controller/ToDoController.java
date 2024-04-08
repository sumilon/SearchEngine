package org.springframework.ai.openai.samples.helloworld.controller;

import org.springframework.ai.openai.samples.helloworld.model.ToDo;
import org.springframework.ai.openai.samples.helloworld.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ToDoController {

    @Autowired
    private ToDoService toDoService;

    private String username;

    @GetMapping("/todo/{name}")
    public String getAllToDos(@PathVariable String name, Model model) {
        model.addAttribute("todos", toDoService.getAllTodos(name));
        username = name;
        return "todos";
    }

    @PostMapping("/add")
    public String addToDo(@ModelAttribute ToDo todo) {
        todo.setName(username);
        toDoService.addOrUpdateToDo(todo);
        return "redirect:/todo/"+username;
    }

    @PostMapping("/edit/{id}")
    public String editToDo(@PathVariable String id, @ModelAttribute ToDo todo) {
        todo.setId(id); // Set the ID for updating existing ToDo
        todo.setName(username);
        toDoService.addOrUpdateToDo(todo);
        return "redirect:/todo/"+username;
    }

    @PostMapping("/delete/{id}")
    public String deleteToDo(@PathVariable String id) {
        toDoService.deleteToDoById(id);
        return "redirect:/todo/"+username;
    }
}

