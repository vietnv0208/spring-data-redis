package com.example.springdataredis.api;

import com.example.springdataredis.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SampleRedisJPAControllerImpl {

    @Autowired
    SampleRedisJpaService service;

    @GetMapping("/")
    public ResponseEntity getAll(){
            return ResponseEntity.ok(service.fillAll());
    }
    @PostMapping
    public ResponseEntity add(@RequestBody Student student){
        return ResponseEntity.ok(service.add(student));
    }
    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") String id){
        return ResponseEntity.ok(service.get(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Student student){
        return ResponseEntity.ok(service.update(id,student));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        return ResponseEntity.ok(service.delete(id));
    }

}
