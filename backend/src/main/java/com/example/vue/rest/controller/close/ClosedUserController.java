package com.example.vue.rest.controller.close;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.vue.rest.dto.Response;
import com.example.vue.rest.service.UserService;

/**
 * @author o.myltsyn * @since 18 дек. 2019 г.
 */

@RestController
@RequestMapping("api/auth")
public class ClosedUserController {

    @Autowired
    private UserService userService;

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
