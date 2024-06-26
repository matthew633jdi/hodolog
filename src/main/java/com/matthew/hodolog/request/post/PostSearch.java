package com.matthew.hodolog.request.post;

import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.Setter;

import static java.lang.Math.max;
import static java.lang.Math.min;

@Getter @Setter
@Builder
public class PostSearch {

    private static final int MAX_SIZE = 2000;

    @Default
    private Integer page = 1;

    @Default
    private Integer size = 10;

    public PostSearch(Integer page, Integer size) {
        this.page = page == null ? 1 : page;
        this.size = size == null ? 10 : size;
    }

    public long getOffset() {
        return (long) (max(1, page) - 1) * min(size, MAX_SIZE);
    }
}
