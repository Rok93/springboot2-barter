package com.roki.springboot.domain.user;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
    }

    @Test
    public void user를_조회한다() {
        String username = "rok";
        String email = "goodboy302@naver.com";
        Role role = Role.USER;

        userRepository.save(User.builder()
                .name(username)
                .email(email)
                .role(role)
                .build());

        User findUser = userRepository.findAll().get(0);
        assertThat(username).isEqualTo(findUser.getName());
        assertThat(email).isEqualTo(findUser.getEmail());
        assertThat(role).isEqualTo(findUser.getRole());
    }
}