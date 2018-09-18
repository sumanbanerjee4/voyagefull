package me.woemler.springblog.repositories;

import me.woemler.springblog.models.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
