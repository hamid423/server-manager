package com.example.servermanager.models;

import com.example.servermanager.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Server {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true)
    @NotEmpty(message = "Ip adress cannot be empty or null")
    private String ipAddress;
    private String memory;
    private String imageUrl;
    private String type;
    private Status status;
}
