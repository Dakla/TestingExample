package com.example.testingexample.controllers;

import com.example.testingexample.domain.CoolThing;
import com.example.testingexample.service.CoolThingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseCoolThingController {

    private final CoolThingService coolThingService;

    public BaseCoolThingController(CoolThingService coolThingService) {
        this.coolThingService = coolThingService;
    }

    @GetMapping("/")
    public ResponseEntity<String> baseController() {
        return ResponseEntity.ok("Hello, CoolThing");
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<CoolThing> create(String name, String desc) {
        return ResponseEntity.ok(coolThingService.create(name, desc));
    }
}
