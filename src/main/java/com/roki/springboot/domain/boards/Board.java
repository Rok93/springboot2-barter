package com.roki.springboot.domain.boards;

import com.roki.springboot.domain.BaseTimeEntity;
import com.roki.springboot.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USER_ID")
    private Long userId;

    @Column(nullable = false)
    private BoardType boardType;

    @Builder
    public Board(Long userId, BoardType boardType) {
        this.userId = userId;
        this.boardType = boardType;
    }
}
