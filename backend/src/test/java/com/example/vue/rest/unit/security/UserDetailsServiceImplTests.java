package com.example.vue.rest.unit.security;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.vue.rest.entity.User;
import com.example.vue.rest.security.UserDetailsServiceImpl;
import com.example.vue.rest.service.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

/**
 * @author o.myltsyn * @since 13 февр. 2020 г.
 */
@Disabled
public class UserDetailsServiceImplTests {

    private UserService userService =  Mockito.mock(UserService.class);

    @Test
    public void testLoadUserByUsername() {
        User user = new User(1, "username", "password", "email");

        given(userService.loadByUsername(user.getUsername())).willReturn(user);

        UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl(userService);

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());

        assertEquals(userDetails.getUsername(), user.getUsername());
        assertEquals(userDetails.getPassword(), user.getPassword());

    }

}
