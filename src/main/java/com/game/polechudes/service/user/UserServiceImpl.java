package com.game.polechudes.service.user;

import com.game.polechudes.exceptions.UserDuplicateException;
import com.game.polechudes.repo.UserRepository;
import com.game.polechudes.domain.entity.User;
import com.game.polechudes.domain.enums.Role;
import com.game.polechudes.domain.model.service.UserServiceModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void save(UserServiceModel userServiceModel) {
        User user = this.modelMapper.map(userServiceModel,User.class);
        if (this.userRepository.count() == 0) {
            user.setRoles(new HashSet<>());
            user.getRoles().add(Role.ADMIN);
        } else {
            user.setRoles(new HashSet<>());
            user.getRoles().add(Role.USER);
        }
        user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
        this.userRepository.save(user);
    }

    @Override
    public void existsByUsername(String username) {
       if(this.userRepository.existsByName(username))
           throw  new UserDuplicateException( username + " already exists , try different username" );
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = this.userRepository.findByName(userName);
        if(user == null){
            throw new UsernameNotFoundException("user is not found");
        }
        return user;
    }
}
