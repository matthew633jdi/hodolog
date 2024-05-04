package com.matthew.hodolog.request.note;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter @ToString
public class NoteCreate {

    @NotBlank(message = "PBS하실 본문을 입력해주세요.")
    private String chapter;
    @NotBlank(message = "제목을 입력해주세요.")
    private String title;
    private String structure;

    @NotBlank(message = "PBS 내용을 입력해주세요.")
    private String content;
    @NotBlank(message = "핵심 키워드를 입력해주세요.")
    private String keyword;

    @Builder
    public NoteCreate(String chapter, String title, String structure, String content, String keyword) {
        this.chapter = chapter;
        this.title = title;
        this.structure = structure;
        this.content = content;
        this.keyword = keyword;
    }
}
