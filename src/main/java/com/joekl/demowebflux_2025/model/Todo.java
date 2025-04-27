package com.joekl.demowebflux_2025.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// para los get and set
@Data
//document por que se va a usar MONGODB
@Document
public class Todo {
    @Id
    private String id;
    private String name;
    private Boolean completed;
}

