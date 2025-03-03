package com.hyper.autoshop.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users", schema = "hyper") // Specify schema explicitly
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment ID
    private Long id;

    private String fname;
    private String lname;
    private String email;
    private String userId;
    private String method;
}
