package com.game.polechudes.domain.model.service;

import com.game.polechudes.domain.enums.Role;
import lombok.Data;

import java.util.Set;

@Data
public class UserServiceModel {
    private String name;
    private String email;
    private String password;
    private Set<Role> authorities;
}
