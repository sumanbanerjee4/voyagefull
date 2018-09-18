package me.woemler.springblog.repositories;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import me.woemler.springblog.models.User;

import java.util.Arrays;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
  
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(User.ROLE_USER)));
        userRepository.save(user);
        System.out.println("saved user"+user.toString());
    }

    @Override
    public User findByUsername(String username) {
    	System.out.println("find by user name");
        return userRepository.findByUsername(username);
    }

	
}
