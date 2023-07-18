package de.pogozhev.keygeneratorservice.controllers;

import de.pogozhev.keygeneratorservice.services.KeyGeneratorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/code-generator")
public class SimpleController {
    private final KeyGeneratorService generatorService;

    public SimpleController(KeyGeneratorService generatorService) {
        this.generatorService = generatorService;
    }

    @PostMapping
    public ResponseEntity<String> generate() {
        return ResponseEntity.ok(generatorService.generate());
    }
}
