package com.joekl.demowebflux_2025.repository;

import com.joekl.demowebflux_2025.model.Todo;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TodoRepository extends ReactiveMongoRepository<Todo, String> {
}
