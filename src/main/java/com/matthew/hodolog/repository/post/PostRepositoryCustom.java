package com.matthew.hodolog.repository.post;

import com.matthew.hodolog.domain.Post;
import com.matthew.hodolog.request.post.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);

}
