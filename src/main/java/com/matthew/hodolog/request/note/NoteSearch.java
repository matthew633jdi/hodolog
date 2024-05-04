package com.matthew.hodolog.request.note;

import jakarta.validation.constraints.PastOrPresent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter @Setter @ToString
public class NoteSearch {

    private String chapter;

    private String title;
    private String keyword;

    @PastOrPresent
    private LocalDate date;

    @Builder
    public NoteSearch(String chapter, String title, String keyword, LocalDate date) {
        this.chapter = chapter;
        this.title = title;
        this.keyword = keyword;
        this.date = date;
    }
}
