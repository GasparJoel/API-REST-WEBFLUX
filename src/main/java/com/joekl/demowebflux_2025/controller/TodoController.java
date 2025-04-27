package com.joekl.demowebflux_2025.controller;

import com.joekl.demowebflux_2025.model.Todo;
import com.joekl.demowebflux_2025.repository.TodoRepository;
import com.joekl.demowebflux_2025.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/todos")
public class TodoController     {

    private final TodoService todoService;


    @GetMapping
    public Flux<Todo> getAll(){

        return todoService.findAll()
                .delayElements(Duration.ofSeconds(2));
    }

    @GetMapping("/{id}")
    public Mono<Todo> get(@PathVariable String id){
        return todoService.findById(id);
    }

    @PostMapping
    public Mono<Todo> create(@RequestBody Todo todo){
        return todoService.create(todo);
    }
    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return    todoService.findById(id)
      //             .flatMap(todo->todoService.delete(todo));
                   .flatMap(todoService::delete);

    }

}
