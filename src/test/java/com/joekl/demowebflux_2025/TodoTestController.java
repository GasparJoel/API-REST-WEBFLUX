package com.joekl.demowebflux_2025;

import com.joekl.demowebflux_2025.model.Todo;
import com.joekl.demowebflux_2025.repository.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TodoTestController {

    @Autowired
    //Esto viene de reactor
    private WebTestClient webTestClient;

    //
    @Autowired
    private TodoRepository todoRepository;
//    pueba N°1
    @Test
    void getAll() {
        webTestClient.get()
                .uri("/api/v1/todos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Todo.class)
                .consumeWith(result -> {
                    var todos = result.getResponseBody();
                    Assertions.assertThat(todos).isNotEmpty();   // No está vacío
                    Assertions.assertThat(todos.get(0).getId()).isNotNull(); // El primer id no es nulo
                });
    }

    @Test
    void getNotFound() {

        webTestClient.get()
                .uri("api/v1/todos/{id}","id no existe")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty();
    }
    @Test
    void get(){
        Todo todo = createExampleTodo();
        webTestClient.get()
                .uri("api/v1/todos/{id}",todo.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo(todo.getName())
                .jsonPath("$.completed").isEqualTo(todo.getCompleted())
                .jsonPath("$.id").isEqualTo(todo.getId());

    }

    @Test
    void create(){

        Todo todo =new Todo();
        todo.setName("Prueba created");
        todo.setCompleted(true);

        webTestClient.post()
                .uri("api/v1/todos")
                .bodyValue(todo)
                .exchange()
                .expectStatus().isCreated()
                .expectBody()
                .jsonPath("$.id").isNotEmpty()
                .jsonPath("$.completed").isEqualTo(todo.getCompleted())
                .jsonPath("$.name").isEqualTo(todo.getName());

    }

    @Test
    void delete(){
        Todo todo = createExampleTodo();
        webTestClient.delete()
                .uri("/api/v1/todos/{id}",todo.getId())
                .exchange()
                .expectStatus().isNoContent()
                .expectBody().isEmpty(); //Decir que el cuerpo esta vacio
    }

    @Test
    void deleteNotFound(){

        webTestClient.delete()
                .uri("/api/v1/todos/{id}","id que no existe")
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty(); //Decir que el cuerpo esta vacio
    }

    private Todo createExampleTodo(){
        Todo todo = new Todo();
        todo.setName("Repasar la clase");
        todo.setCompleted(false);

      return   todoRepository.save(todo).block();

    }

}
