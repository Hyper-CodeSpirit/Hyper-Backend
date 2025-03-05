package com.hyper.autoshop.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Builder
@Table(name = "users", schema = "hyper")
@NoArgsConstructor
@AllArgsConstructor // Specify schema explicitly
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // or another strategy
    private Long id; // No auto-increment as per SQL table

    @Column(name = "user_name", nullable = false, length = 200)
    private String userName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String avatar;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Date createdDate;

    @Column(name = "authentication_method", nullable = false, length = 50)
    private String authenticationMethod;
}
