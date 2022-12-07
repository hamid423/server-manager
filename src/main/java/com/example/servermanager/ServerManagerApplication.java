package com.example.servermanager;

import com.example.servermanager.models.Server;
import com.example.servermanager.repos.ServerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.example.servermanager.enums.Status.SERVER_DOWN;
import static com.example.servermanager.enums.Status.SERVER_UP;

@SpringBootApplication
public class ServerManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerManagerApplication.class, args);
    }


    @Bean
    CommandLineRunner run(ServerRepo serverRepo){
        return args -> {
            serverRepo.save(new Server(null, "192.168.1.160", "Server1", "16 GB", "Personal PC1",
                    "http://localhost:8080/server/image/server1.png", SERVER_UP));
            serverRepo.save(new Server(null, "193.168.1.160", "Server2", "32 GB", "Web Server",
                    "http://localhost:8080/server/image/server2.png", SERVER_DOWN));
            serverRepo.save(new Server(null, "194.168.1.160", "Server3", "64 GB", "Mail Server",
                    "http://localhost:8080/server/image/server1.png", SERVER_UP));
            serverRepo.save(new Server(null, "195.168.1.160", "Server4", "16 GB", "Dell Tower",
                    "http://localhost:8080/server/image/server1.png", SERVER_DOWN));
        };
    }

}
