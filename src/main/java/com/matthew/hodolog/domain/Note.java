package com.matthew.hodolog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Note extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String chapter;

    private String title;

    private String structure;
    @Column(nullable = false)
    @Lob
    private String content;
    @Column(nullable = false)
    private String keyword;

    @Builder
    public Note(String chapter, String title, String structure, String content, String keyword) {
        this.chapter = chapter;
        this.title = title;
        this.structure = structure;
        this.content = content;
        this.keyword = keyword;
    }
}
