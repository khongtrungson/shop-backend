package com.khongtrungson.shopapp.services;

import com.khongtrungson.shopapp.dtos.requests.CommentDTO;
import com.khongtrungson.shopapp.dtos.responses.CommentResponse;
import com.khongtrungson.shopapp.entities.Comment;

import java.util.List;

public interface ICommentService {
    void createComment(long userId, long productId, CommentDTO commentDTO);

    void deleteComment(long userId, long commentId);

    List<CommentResponse> getAll(long productId);
}
