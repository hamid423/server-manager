package com.example.servermanager.controllers;


import com.example.servermanager.enums.Status;
import com.example.servermanager.models.Response;
import com.example.servermanager.models.Server;
import com.example.servermanager.services.implementations.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import static com.example.servermanager.enums.Status.SERVER_UP;
import static java.time.LocalDateTime.*;
import static java.util.Map.of;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerServiceImpl serverService;

    @GetMapping("/list")
    public ResponseEntity<Response> getServers(){
        return ResponseEntity.ok(
            Response.builder()
                    .timestamp(now())
                    .data(of("servers", serverService.list(30)))
                    .message("servers retrieved")
                    .status(OK)
                    .statusCode(OK.value())
                    .build()
        );
    }

    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("server", server))
                        .message(server.getStatus() == SERVER_UP ? "Ping succeed" : "Ping failed" )
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping ("/save")
    public ResponseEntity<Response> saveServer(@RequestBody() Server server)  {

        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("server", serverService.create(server)))
                        .message("Server created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("server", serverService.get(id)))
                        .message("server retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .data(of("deleted", serverService.delete(id)))
                        .message("server deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping(path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("src/main/resources/static/servers icons/" + fileName));
    }
}
