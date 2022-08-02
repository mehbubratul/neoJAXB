package com.mehbub.neoJAXB.controller;

import com.mehbub.neoJAXB.dto.User;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    UserController userController = new UserController();
    @Autowired
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @SneakyThrows
    @Test
    void testPath(){
        Path path = Path.of("src", "main", "resources");
        String filesFolder = path.toString();
        Path dir = Path.of(filesFolder);
        Path pathToFile = dir.resolve("u1".concat(".xml"));

        if (Files.notExists(pathToFile)) {
            Files.createDirectories(dir);
            Files.createFile(pathToFile);
        }
    }
}