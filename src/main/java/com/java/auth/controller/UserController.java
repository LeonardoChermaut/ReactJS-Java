package com.java.auth.controller;

import com.java.auth.dto.CreateUserDto;
import com.java.auth.dto.UpdateUserDto;
import com.java.auth.dto.UserDto;
import com.java.auth.exception.UserException;
import com.java.auth.exception.UserNotFoundException;
import com.java.auth.model.UserModel;
import com.java.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @ResponseBody
    public ResponseEntity<HttpStatus> save(@Valid @RequestBody CreateUserDto dto) throws UserException {
        return ResponseEntity.ok(service.save(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable long id) throws UserNotFoundException {
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@Valid @PathVariable long id, @RequestBody UpdateUserDto dto) throws UserException {
        service.update(id, dto);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> remove(@Valid @PathVariable long id) throws UserNotFoundException {
        service.remove(id);
        return ResponseEntity.accepted().body(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> all() {
        return ResponseEntity.ok(service.allObjects());
    }

    @GetMapping("/context")
    public ResponseEntity<Optional<UserModel>> context() {
        ResponseEntity.accepted().body(HttpStatus.CONTINUE);
        return ResponseEntity.ok(service.context());
    }

}