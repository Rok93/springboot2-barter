package com.roki.springboot.domain.boards;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ProductStatus {

    VERYHIGHT("최상", 5),
    HIGHT("상", 4),
    MIDDLE("중", 3),
    LOW("하", 2),
    VERYLOW("최하", 1);

    private final String status;
    private final int value;
}
