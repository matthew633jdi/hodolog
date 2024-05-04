package com.matthew.hodolog.repository.note;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class NoteRepositoryImpl implements NoteRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;


}
