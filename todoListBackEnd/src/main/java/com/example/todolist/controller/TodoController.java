package com.example.todolist.controller;

import com.example.todolist.model.Api;
import com.example.todolist.model.Todo;
import com.example.todolist.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/todo")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping
    public ResponseEntity getTodos(){
        return ResponseEntity.status(200).body(todoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity addTodos(@RequestBody Todo todo){
        todoRepository.save(todo);
        return ResponseEntity.status(201).body(new Api("New todo added :)",201));
    }

    @PutMapping("/{id}")
    public ResponseEntity updateTodos(@PathVariable Long id,
                                      @RequestBody Todo todo){
        Todo oldTodo = todoRepository.findById(id).get();
        oldTodo.setMessage(todo.getMessage());
        todoRepository.save(oldTodo);
        return ResponseEntity.status(200).body(new Api("Todo Updated :)",200));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletTodos(@PathVariable Long id){
        todoRepository.deleteById(id);
        return ResponseEntity.status(200).body(new Api("Todo deleted :)",200));
    }

}
