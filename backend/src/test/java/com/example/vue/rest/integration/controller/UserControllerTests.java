package com.example.vue.rest.integration.controller;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.example.vue.rest.controller.UserController;
import com.example.vue.rest.dto.user.UserAuthRequest;
import com.example.vue.rest.dto.user.UserDTO;
import com.example.vue.rest.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author o.myltsyn * @since 31 янв. 2020 г.
 */
@Disabled
@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void testAuthUserSuccess() throws Exception {
        UserAuthRequest userAuthRequest = new UserAuthRequest();
        userAuthRequest.setUsername("username");
        userAuthRequest.setPassword("password");

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(userAuthRequest);

        given(userService.authorizeUser(userAuthRequest.getUsername(), userAuthRequest.getPassword())).willReturn(true);

        mvc.perform(post("/api/authorize", userAuthRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", is(true)));
    }

    @Test
    public void testAuthUserFail() throws Exception {
        UserAuthRequest userAuthRequest = new UserAuthRequest();
        userAuthRequest.setUsername("username");
        userAuthRequest.setPassword("password");

        ObjectMapper objectMapper = new ObjectMapper();
        String body = objectMapper.writeValueAsString(userAuthRequest);

        given(userService.authorizeUser(userAuthRequest.getUsername(), userAuthRequest.getPassword())).willReturn(false);

        mvc.perform(post("/api/authorize", userAuthRequest)
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.response", is(false)));
    }

    @Test
    public void testGetAllUser() throws Exception {
        UserDTO userDTO = new UserDTO(1, "username", "password", "email");

        List<UserDTO> allEmployees = Arrays.asList(userDTO);

        given(userService.getAll()).willReturn(allEmployees);

        mvc.perform(get("/api/user/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response", hasSize(1)))
                .andExpect(jsonPath("$.response[0].id", is(userDTO.getId())))
                .andExpect(jsonPath("$.response[0].username", is(userDTO.getUsername())))
                .andExpect(jsonPath("$.response[0].password", is(userDTO.getPassword())))
                .andExpect(jsonPath("$.response[0].email", is(userDTO.getEmail())));
    }

    @Test
    public void testUserById() throws Exception {
        Integer userId = 1;
        UserDTO userDTO = new UserDTO(userId, "username", "password", "email");


        given(userService.getUserById(userId)).willReturn(userDTO);

        mvc.perform(get("/api/user/"+ userId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.response.id", is(userDTO.getId())))
                .andExpect(jsonPath("$.response.username", is(userDTO.getUsername())))
                .andExpect(jsonPath("$.response.password", is(userDTO.getPassword())))
                .andExpect(jsonPath("$.response.email", is(userDTO.getEmail())));
    }

}
