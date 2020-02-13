package com.example.vue.rest.unit.service;

import java.util.Arrays;
import java.util.List;

import javax.jws.soap.SOAPBinding;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.vue.rest.dto.user.UserDTO;
import com.example.vue.rest.entity.User;
import com.example.vue.rest.repository.UserRepository;
import com.example.vue.rest.security.UserDetailsServiceImpl;
import com.example.vue.rest.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * @author o.myltsyn * @since 13 февр. 2020 г.
 */
@Disabled
public class UserServiceTests {

    private UserRepository userRepository =  Mockito.mock(UserRepository.class);

    private PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();

    @Test
    public void testGetAll() {
        User user = new User(1, "username", "password", "email");

        given(userRepository.findAll()).willReturn(Arrays.asList(user));

        UserService userService = new UserService(userRepository, null);

        List<UserDTO> userDTOList = userService.getAll();

        assertEquals(userDTOList.get(0).getUsername(), user.getUsername());
        assertEquals(userDTOList.get(0).getPassword(), user.getPassword());
        assertEquals(userDTOList.get(0).getEmail(), user.getEmail());

    }

    @Test
    public void testGetUserById() {
        Integer id = 1;
        User user = new User(id, "username", "password", "email");

        given(userRepository.getOne(id)).willReturn(user);

        UserService userService = new UserService(userRepository, null);

        UserDTO userDTO = userService.getUserById(id);

        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.getEmail(), user.getEmail());
    }

    @Test
    public void testLoadByUsername() {
        User user = new User(1, "username", "password", "email");

        given(userRepository.loadByUsername(user.getUsername())).willReturn(user);

        UserService userService = new UserService(userRepository, null);

        User userFound = userService.loadByUsername(user.getUsername());

        assertEquals(userFound.getUsername(), user.getUsername());
        assertEquals(userFound.getPassword(), user.getPassword());
        assertEquals(userFound.getEmail(), user.getEmail());
    }

    @Test
    public void testAuthorizeSuccess() {
        String password = "password";
        User user = new User(1, "username", passwordEncoder.encode(password), "email");

        given(userRepository.loadByUsername(user.getUsername())).willReturn(user);

        UserService userService = new UserService(userRepository, passwordEncoder);

        Boolean isAuth = userService.authorizeUser(user.getUsername(), password);

        assertEquals(isAuth, true);
    }

    @Test
    public void testAuthorizeFail() {
        User user = new User(1, "username", passwordEncoder.encode("password"), "email");

        given(userRepository.loadByUsername(user.getUsername())).willReturn(user);

        UserService userService = new UserService(userRepository, passwordEncoder);

        Boolean isAuth = userService.authorizeUser(user.getUsername(), "test");

        assertEquals(isAuth, false);
    }

}
