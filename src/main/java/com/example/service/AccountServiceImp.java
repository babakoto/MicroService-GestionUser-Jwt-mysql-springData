package com.example.service;

import com.example.dao.AppRoleRepository;
import com.example.dao.AppUserRepository;
import com.example.model.AppRole;
import com.example.model.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AccountServiceImp implements AccountService {

    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AppRoleRepository appRoleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public AppUser saveUser(String username, String password, String confirmPassword) {
      AppUser user = appUserRepository.findByUsername(username);
      if (user!=null)throw new RuntimeException("User already exists");
      if (!password.equals(confirmPassword)) throw new RuntimeException("password not confirmed");

      AppUser appUser = new AppUser();
      appUser.setUsername(username);
      appUser.setActived(true);
      appUser.setPassword(bCryptPasswordEncoder.encode(password));
      appUserRepository.save(appUser);
      addRoleToUser(username,"USER");
        return appUser;
    }

    @Override
    public AppRole saveRole(AppRole appRole) {
        return appRoleRepository.save(appRole);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String username, String role) {
        AppUser user = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(role);
        user.getAppRoles().add(appRole);
    }
}
