package org.springframework.ai.openai.samples.helloworld.repository;//package com.example.simpleapp.repository;

import org.springframework.ai.openai.samples.helloworld.model.ToDo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ToDoRepository extends MongoRepository<ToDo, String> {
}
