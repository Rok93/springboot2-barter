package com.roki.springboot.domain.barterboard;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionStatus {
    Y("거래가능", true),
    N("거래완료", false);

    private final String status;
    private final boolean value;
}
