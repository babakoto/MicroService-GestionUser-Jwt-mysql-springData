package com.example.service;

import com.example.model.AppRole;
import com.example.model.AppUser;

public interface AccountService {
    AppUser saveUser(String username,String password,String confirmPassword);
    AppRole saveRole(AppRole appRole);
    AppUser loadUserByUsername(String username);
    void addRoleToUser(String username,String role);
}
