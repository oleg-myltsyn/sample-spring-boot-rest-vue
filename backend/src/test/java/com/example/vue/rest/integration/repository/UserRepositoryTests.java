package com.example.vue.rest.integration.repository;

import javax.jws.soap.SOAPBinding;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.vue.rest.entity.User;
import com.example.vue.rest.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author o.myltsyn * @since 13 февр. 2020 г.
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testLoadByUsername() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        user.setEmail("email");
        entityManager.persist(user);
        entityManager.flush();

        User found = userRepository.loadByUsername(user.getUsername());

        assertEquals(found.getUsername(),user.getUsername());
        assertEquals(found.getPassword(),user.getPassword());
        assertEquals(found.getEmail(),user.getEmail());
    }
}
