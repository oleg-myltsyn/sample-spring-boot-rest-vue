package com.example.vue.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.vue.rest.dto.Response;
import com.example.vue.rest.dto.user.UserAuthRequest;
import com.example.vue.rest.service.UserService;

/**
 * @author o.myltsyn * @since 18 дек. 2019 г.
 */

@RestController
@RequestMapping("api")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "authorize"
    )
    public ResponseEntity<Response> authUser(@RequestBody UserAuthRequest userAuthRequest) {

        Boolean isAuth = userService.authorizeUser(userAuthRequest.getUsername(), userAuthRequest.getPassword());

        return new ResponseEntity<>(
                Response.builder()
                        .response(isAuth)
                        .build(),
                isAuth ? HttpStatus.OK:HttpStatus.UNAUTHORIZED);
    }


    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "user/all"
    )
    public ResponseEntity<Response> getAllUsers() {

        return new ResponseEntity<>(
                Response.builder()
                        .response(userService.getAll())
                        .build(),
                HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE,
            value = "user/{id}"
    )
    public ResponseEntity<Response> getUserById(@PathVariable("id") Integer id) {

        return new ResponseEntity<>(
                Response.builder()
                        .response(userService.getUserById(id))
                        .build(),
                HttpStatus.OK);
    }

}
