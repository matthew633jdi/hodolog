package com.matthew.hodolog.repository;

import com.matthew.hodolog.domain.Post;
import com.matthew.hodolog.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);

}
