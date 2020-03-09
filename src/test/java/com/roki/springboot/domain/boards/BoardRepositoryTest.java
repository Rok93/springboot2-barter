package com.roki.springboot.domain.boards;

import com.roki.springboot.domain.user.Role;
import com.roki.springboot.domain.user.User;
import com.roki.springboot.domain.user.UserRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
        boardRepository.deleteAll();
    }

    @Test
    public void board를_조회한다() {
        String name = "rokA";
        String email = "aaa@naver.com";
        Role role = Role.USER;

        User user = User.builder()
                .name(name)
                .email(email)
                .role(role)
                .build();
        userRepository.save(user);

        boardRepository.save(Board.builder()
                .user(user)
                .boardType(BoardType.BARTER)
                .build());

        Board board = boardRepository.findAll().get(0);

        assertThat(user.getId()).isEqualTo(board.getUser().getId());
        assertThat(user.getEmail()).isEqualTo(board.getUser().getEmail());
        assertThat(user.getName()).isEqualTo(board.getUser().getName());
        assertThat(user.getRole()).isEqualTo(board.getUser().getRole());
        assertThat(BoardType.BARTER).isEqualTo(board.getBoardType());

    }

}