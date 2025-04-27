package com.joekl.demowebflux_2025.service;

import com.joekl.demowebflux_2025.model.Todo;
import com.joekl.demowebflux_2025.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//Crea el contructor de la inyeccion
@RequiredArgsConstructor
@Service
public class TodoServiceImpl implements TodoService{

    //Inyeccion de dependencia por constructor

    private final TodoRepository todoRepository;


    @Override
    public Flux<Todo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Mono<Todo> findById(String id) {
        return todoRepository.findById(id);

    }

    @Override
    public Mono<Void> delete(Todo todo) {
        return todoRepository.delete(todo);
    }

    @Override
    public Mono<Todo> create(Todo todo) {
        return todoRepository.save(todo);

    }
}
