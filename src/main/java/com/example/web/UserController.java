package com.example.web;

import com.example.model.AppUser;
import com.example.payload.Register;
import com.example.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public AppUser register(@RequestBody Register register){
        return accountService.saveUser(register.getUsername(),register.getPassword(),register.getConfirme());
    }
}
