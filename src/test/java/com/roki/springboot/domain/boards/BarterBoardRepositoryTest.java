package com.roki.springboot.domain.boards;

import com.roki.springboot.domain.barterboard.BarterBoard;
import com.roki.springboot.domain.barterboard.BarterBoardRepository;
import com.roki.springboot.domain.barterboard.TransactionStatus;
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
public class BarterBoardRepositoryTest {

    @Autowired
    private BarterBoardRepository barterBoardRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
        boardRepository.deleteAll();
        barterBoardRepository.deleteAll();
    }

    @Test
    public void barterBoard를_조회한다() {

        String username = "rok";
        String email = "goodboy302@naver.com";
        Role role = Role.USER;

        User user = User.builder()
                .name(username)
                .email(email)
                .role(role)
                .build();
        userRepository.save(user);

        Board board = Board.builder()
                .user(user)
                .boardType(BoardType.BARTER)
                .build();
        boardRepository.save(board);

        String givingProductName = "감자";
        String receivingProductName = "대파";
        int amount = 10;
        String transactionPlace = "봉천역";
        String content = "쿨거래";

        barterBoardRepository.save(BarterBoard.builder()
                .board(board)
                .givingProductName(givingProductName)
                .receivingProductName(receivingProductName)
                .amount(amount)
                .transactionPlace(transactionPlace)
                .content(content)
                .build());

        BarterBoard barterBoard = barterBoardRepository.findAll().get(0);
        assertThat(givingProductName).isEqualTo(barterBoard.getGivingProductName());
        assertThat(receivingProductName).isEqualTo(barterBoard.getReceivingProductName());
        assertThat(amount).isEqualTo(barterBoard.getAmount());
        assertThat(transactionPlace).isEqualTo(barterBoard.getTransactionPlace());
        assertThat(TransactionStatus.Y).isEqualTo(barterBoard.getTransactionStatus());
        assertThat(content).isEqualTo(barterBoard.getContent());

        Board findBoard = boardRepository.findAll().get(0);
        assertThat(board).isEqualTo(findBoard);

        User findUser = userRepository.findAll().get(0);
        assertThat(user).isEqualTo(findUser);
    }

}