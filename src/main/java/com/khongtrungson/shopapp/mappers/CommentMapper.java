package com.khongtrungson.shopapp.mappers;

import com.khongtrungson.shopapp.dtos.requests.CommentDTO;
import com.khongtrungson.shopapp.dtos.responses.CommentResponse;
import com.khongtrungson.shopapp.entities.Comment;
import com.khongtrungson.shopapp.entities.Product;
import com.khongtrungson.shopapp.entities.User;

public class CommentMapper {
    public static Comment dtoToEntity(User user, Product product, CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setCommentContent(commentDTO.getContent());
        comment.setProduct(product);
        comment.setUser(user);
        comment.setRating(comment.getRating());
        comment.setActive(true);
        return comment;
    }
    public static CommentResponse entityToResponse(Comment comment){
        CommentResponse commentResponse = new CommentResponse();
        commentResponse.setContent(comment.getCommentContent());
        commentResponse.setId(comment.getId());
        commentResponse.setRating(comment.getRating());
        commentResponse.setUser(CommentResponse.entityToResponse(comment.getUser()));
        return commentResponse;
    }


}
