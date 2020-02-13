package com.example.vue.rest.dto.user;

import lombok.Data;

/**
 * @author o.myltsyn * @since 06 февр. 2020 г.
 */
@Data
public class UserAuthRequest {
    private String username;
    private String password;
}
