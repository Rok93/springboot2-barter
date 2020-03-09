package com.roki.springboot.domain.boards;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class BarterBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private String givingProductName;

    @Column(nullable = false)
    private String receivingProductName;

    @Column(nullable = false)
    private int amount;

    @Column(nullable = false)
    private String transactionPlace;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus transactionStatus = TransactionStatus.Y;

    @Column(length = 500, nullable = false)
    private String content;

    @Builder
    public BarterBoard(String givingProductName, String receivingProductName,
                       int amount, String transactionPlace, String content, Board board) {
        this.givingProductName = givingProductName;
        this.receivingProductName = receivingProductName;
        this.amount = amount;
        this.transactionPlace = transactionPlace;
        this.content = content;
        this.board = board;
    }

}
