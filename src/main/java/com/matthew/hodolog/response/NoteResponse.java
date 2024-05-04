package com.matthew.hodolog.response;

import com.matthew.hodolog.domain.Note;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter @ToString
public class NoteResponse {

    private final Long id;
    private final String chapter;
    private final String title;
    private final String structure;
    private final String content;
    private final String keyword;
    private final LocalDateTime createdDate;

    public NoteResponse(Note note) {
        this.id = note.getId();
        this.chapter = note.getChapter();
        this.title = note.getTitle();
        this.structure = note.getStructure();
        this.content = note.getContent();
        this.keyword = note.getKeyword();
        this.createdDate = note.getCreatedDate();
    }

    @Builder
    public NoteResponse(Long id, String chapter, String title, String structure, String content, String keyword, LocalDateTime createdDate) {
        this.id = id;
        this.chapter = chapter;
        this.title = title;
        this.structure = structure;
        this.content = content;
        this.keyword = keyword;
        this.createdDate = createdDate;
    }
}
