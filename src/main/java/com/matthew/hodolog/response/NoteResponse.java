package com.matthew.hodolog.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter @ToString
@RequiredArgsConstructor
public class NoteResponse {

    private final String title;
    private final String structure;
    private final String keyword;

}
