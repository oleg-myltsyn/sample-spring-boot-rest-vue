package com.example.vue.rest.unit.converter;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.example.vue.rest.converter.UserConverter;
import com.example.vue.rest.dto.UserDTO;
import com.example.vue.rest.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author o.myltsyn * @since 12 февр. 2020 г.
 */
@Disabled
public class UserConverterTests {

    @Test
    public void testToDto() {
        User user = new User(1, "username", "password", "email");

        UserDTO userDTO = UserConverter.toDto(user);

        assertEquals(userDTO.getId(), user.getId());
        assertEquals(userDTO.getUsername(), user.getUsername());
        assertEquals(userDTO.getPassword(), user.getPassword());
        assertEquals(userDTO.getEmail(), user.getEmail());
    }

    @Test
    public void testToEntity() {
        UserDTO userDTO = new UserDTO(1, "username", "password", "email");

        User user = UserConverter.toEntity(userDTO);

        assertEquals(user.getId(), userDTO.getId());
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getPassword(), userDTO.getPassword());
        assertEquals(user.getEmail(), userDTO.getEmail());
    }

}
