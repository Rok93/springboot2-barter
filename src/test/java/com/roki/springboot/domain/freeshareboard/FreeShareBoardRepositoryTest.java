package com.roki.springboot.domain.freeshareboard;

import com.roki.springboot.domain.boards.Board;
import com.roki.springboot.domain.boards.BoardRepository;
import com.roki.springboot.domain.boards.BoardType;
import com.roki.springboot.domain.grouppurchaseboard.ProductStatus;
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
public class FreeShareBoardRepositoryTest {

    @Autowired
    FreeShareBoardRepository freeShareBoardRepository;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @After
    public void tearDown() throws Exception {
        userRepository.deleteAll();
        boardRepository.deleteAll();
        freeShareBoardRepository.deleteAll();
    }

    @Test
    public void FreeShareBoard를_조회한다() {
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

        String productName = "27인치 UHD 벤큐모니터";
        int amount = 1;
        String content = "이사가는데 버리긴 아까워서 드립니다.";
        ProductStatus productStatus = ProductStatus.VERYHIGH;
        String transactionPlace = "봉천역";
        freeShareBoardRepository.save(FreeShareBoard.builder()
                .product_name(productName)
                .amount(amount)
                .content(content)
                .transactionPlace(transactionPlace)
                .board(board)
                .build());

        FreeShareBoard findFreeBoard = freeShareBoardRepository.findAll().get(0);
        assertThat(productName).isEqualTo(findFreeBoard.getProduct_name());
        assertThat(amount).isEqualTo(findFreeBoard.getAmount());
        assertThat(content).isEqualTo(findFreeBoard.getContent());
        assertThat(transactionPlace).isEqualTo(findFreeBoard.getTransactionPlace());

        assertThat(board).isEqualTo(findFreeBoard.getBoard());

        assertThat(user).isEqualTo(findFreeBoard.getBoard().getUser());


    }
}