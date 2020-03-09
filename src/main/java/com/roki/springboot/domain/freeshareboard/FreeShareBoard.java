package com.roki.springboot.domain.freeshareboard;

import com.roki.springboot.domain.boards.Board;
import com.roki.springboot.domain.grouppurchaseboard.ProductStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class FreeShareBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(length = 60, nullable = false)
    private String product_name;

    @Column(nullable = false)
    private int amount;

    @Column(length = 500, nullable = false)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SharingStatue sharingStatue = SharingStatue.Y;

    @Column(nullable = false)
    private String transactionPlace;

    @Builder
    public FreeShareBoard(String product_name, int amount, String content
            , String transactionPlace, Board board) {
        this.product_name = product_name;
        this.amount = amount;
        this.content = content;
        this.transactionPlace = transactionPlace;
        this.board = board;
    }
}
