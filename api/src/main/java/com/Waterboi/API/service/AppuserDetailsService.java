package com.waterboi.api.service;

import com.waterboi.api.model.Appuser;
import com.waterboi.api.repository.AppuserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppuserDetailsService implements UserDetailsService {
    @Autowired
    private AppuserRepository repository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Appuser appuser = repository.findByUsernameIgnoreCase(username);
        User.UserBuilder builder = null;
        if(appuser != null) {
            builder = User.withUsername(username);
            builder.password(appuser.getPassword());
            builder.roles("USER");
        } else {
            throw new UsernameNotFoundException("User not found/");
        }
        return builder.build();
    }

}
