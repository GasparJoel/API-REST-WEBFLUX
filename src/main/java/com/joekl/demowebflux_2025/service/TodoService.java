package com.joekl.demowebflux_2025.service;

import com.joekl.demowebflux_2025.model.Todo;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TodoService {
    Flux<Todo> findAll();
    Mono<Todo> findById(String id);
    Mono<Void> delete(Todo todo);
    Mono<Todo> create(Todo todo);
}
