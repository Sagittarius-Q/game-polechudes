package com.game.polechudes.service.user;

import com.game.polechudes.domain.model.service.UserServiceModel;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void save(UserServiceModel userServiceModel);
    void existsByUsername(String username);
}
