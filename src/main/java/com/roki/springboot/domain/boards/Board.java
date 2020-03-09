package com.roki.springboot.domain.boards;

import com.roki.springboot.domain.BaseTimeEntity;
import com.roki.springboot.domain.user.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Board extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BoardType boardType;

    @Builder
    public Board(User user, BoardType boardType) {
        this.user = user;
        this.boardType = boardType;
    }
}
