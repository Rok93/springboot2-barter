package com.roki.springboot.domain.freeshareboard;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SharingStatue {
    Y("나눔가능", true),
    N("나눔완료", false);

    private final String status;
    private final boolean value;
}
