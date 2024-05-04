package com.matthew.hodolog.request.post;

import com.matthew.hodolog.exception.InvalidRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class PostCreate {

    @NotBlank(message = "Please insert title.")
    private String title;

    @NotBlank(message = "Please insert content.")
    private String content;

    @Builder
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void validate() {
        if (title.contains("stupid")) {
            throw new InvalidRequest("title", "Title does not contain stupid.");
        }
    }
}
