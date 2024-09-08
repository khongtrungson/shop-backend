package com.khongtrungson.shopapp.controllers;

import com.khongtrungson.shopapp.dtos.requests.CommentDTO;
import com.khongtrungson.shopapp.dtos.responses.CommentResponse;
import com.khongtrungson.shopapp.dtos.responses.ResponseData;
import com.khongtrungson.shopapp.entities.Comment;
import com.khongtrungson.shopapp.services.ICommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Tag(
        name = "CRUD APIs for Comment"
)
@RestController
@RequestMapping(value = "/comment",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CommentController {
    private final ICommentService commentService;
    @Operation(summary = "create comment api")
    @PostMapping("/user/{userId}/product/{productId}")
    public ResponseData<String> createComment(
            @PathVariable @Positive  long userId,
            @PathVariable @Positive long productId,
            @RequestBody @Valid CommentDTO commentDTO){
        commentService.createComment(userId,productId,commentDTO);
        return ResponseData.<String>builder()
                .status(HttpStatus.CREATED.value())
                .message("create comment action")
                .data("create comment successfully")
                .build();
    }
    @Operation(summary = "delete comment api")
    @DeleteMapping("/{commentId}/user/{userId}")
    public ResponseData<String> deleteComment(
            @PathVariable @Positive  long userId,
            @PathVariable @Positive long commentId){
        commentService.deleteComment(userId,commentId);
        return ResponseData.<String>builder()
                .status(HttpStatus.OK.value())
                .message("delete comment action")
                .data("delete comment successfully")
                .build();
    }

    @Operation(summary = "get all comment of a product api ")
    @GetMapping("/all/product/{productId}")
    public ResponseData<List<CommentResponse>> getComments(@PathVariable @Positive long productId){
        List<CommentResponse> commentList = commentService.getAll(productId);
        return ResponseData.<List<CommentResponse>>builder()
                .status(HttpStatus.OK.value())
                .message("list comments here")
                .data(commentList)
                .build();
    }


}
