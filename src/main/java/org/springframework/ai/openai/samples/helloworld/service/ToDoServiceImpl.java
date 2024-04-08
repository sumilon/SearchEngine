package org.springframework.ai.openai.samples.helloworld.service;

import org.springframework.ai.openai.samples.helloworld.model.ToDo;
import org.springframework.ai.openai.samples.helloworld.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ToDoServiceImpl implements ToDoService {

    @Autowired
    private ToDoRepository toDoRepository;

    private final MongoTemplate mongoTemplate;

    @Autowired
    public ToDoServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<ToDo> getAllTodos(String name) {

        Query query = new Query(Criteria.where("name").is(name));
        return mongoTemplate.find(query, ToDo.class);
//        return toDoRepository.findAll();
    }

    @Override
    public ToDo getToDoById(String id) {
        Optional<ToDo> optionalToDo = toDoRepository.findById(id);
        return optionalToDo.orElse(null);
    }

    @Override
    public ToDo addOrUpdateToDo(ToDo todo) {

        return toDoRepository.save(todo);
    }

    @Override
    public void deleteToDoById(String id) {

        toDoRepository.deleteById(id);
    }
}
