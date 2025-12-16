package com.example.resourceserver;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceServerController {
    @GetMapping("/public")
    public ResponseEntity<String> endPointPublic() {
        return ResponseEntity.ok("Recurso público acessado com sucesso!");
    }

    @GetMapping("/private")
    public ResponseEntity<String> endPointPrivate() {
        return ResponseEntity.ok("Recurso privado acessado com sucesso!");
    }
}
