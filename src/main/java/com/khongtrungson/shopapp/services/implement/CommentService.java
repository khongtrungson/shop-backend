package com.khongtrungson.shopapp.services.implement;

import com.khongtrungson.shopapp.dtos.requests.CommentDTO;
import com.khongtrungson.shopapp.dtos.responses.CommentResponse;
import com.khongtrungson.shopapp.entities.Comment;
import com.khongtrungson.shopapp.exceptions.DataNotFoundException;
import com.khongtrungson.shopapp.mappers.CommentMapper;
import com.khongtrungson.shopapp.repositories.CommentRepository;
import com.khongtrungson.shopapp.repositories.ProductRepository;
import com.khongtrungson.shopapp.repositories.UserRepository;
import com.khongtrungson.shopapp.services.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;

    @Override
    public void createComment(long userId, long productId, CommentDTO commentDTO) {
        var user = userRepository.findById(userId).orElseThrow(()->new DataNotFoundException("user not found"));
        var product = productRepository.findById(productId).orElseThrow(()-> new DataNotFoundException("product not exist"));
       commentRepository.save( CommentMapper.dtoToEntity(user,product,commentDTO));
    }

    @Override
    public void deleteComment(long userId, long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentResponse> getAll(long productId) {
        var product = productRepository.findById(productId).orElseThrow(()-> new DataNotFoundException("product not exist"));
        var comments = commentRepository.findCommentsByProduct(product);
        var commentResponses = comments.stream().
                map((comment)->CommentMapper.entityToResponse(comment)).collect(Collectors.toList());
        return commentResponses;

    }
}
