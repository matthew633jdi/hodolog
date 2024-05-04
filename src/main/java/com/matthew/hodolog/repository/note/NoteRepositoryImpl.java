package com.matthew.hodolog.repository.note;

import com.matthew.hodolog.domain.Note;
import com.matthew.hodolog.request.note.NoteSearch;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static com.matthew.hodolog.domain.QNote.note;

@RequiredArgsConstructor
public class NoteRepositoryImpl implements NoteRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Note> getNotes(NoteSearch noteSearch) {
        return jpaQueryFactory.selectFrom(note)
                .where(conditionalSearch(noteSearch))
                .fetch();
    }

    private BooleanBuilder conditionalSearch(NoteSearch noteSearch) {
        BooleanBuilder builder = new BooleanBuilder();
        if (StringUtils.hasText(noteSearch.getChapter())) {
            builder.and(chapterSearch(noteSearch.getChapter()));
        }

        if (StringUtils.hasText(noteSearch.getTitle())) {
            builder.and(titleSearch(noteSearch.getTitle()));
        }

        if (StringUtils.hasText(noteSearch.getKeyword())) {
            builder.and(keywordSearch(noteSearch.getKeyword()));
        }

        if (Objects.nonNull(noteSearch.getDate())) {
            builder.and(dateSearch(noteSearch.getDate()));
        }

        return builder;
    }

    private BooleanExpression chapterSearch(String chapter) {
        return note.chapter.likeIgnoreCase(chapter);
    }

    private BooleanExpression titleSearch(String title) {
        return note.title.likeIgnoreCase(title);
    }

    private BooleanExpression keywordSearch(String keyword) {
        return note.keyword.containsIgnoreCase(keyword);
    }

    private BooleanExpression dateSearch(LocalDate date) {
        StringTemplate template = Expressions.stringTemplate("TO_CHAR({0}, {1})", note.createdDate, "YYYY-MM-DD");
        return template.eq(date.toString());
    }

}
