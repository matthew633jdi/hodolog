package com.matthew.hodolog.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class NoteCreate {

    @NotBlank
    private String title;
    private String structure;
    @NotBlank
    private String keyword;

}
