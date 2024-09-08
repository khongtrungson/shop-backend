package com.khongtrungson.shopapp.dtos.responses;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.khongtrungson.shopapp.entities.User;
import lombok.Data;
@Data
public class CommentResponse {
    private long id;
    private String content;
    private long rating;
    private User user;
    @Data
    private static class  User{
        private long id;
        @JsonProperty("full_name")
        private String fullName;
    }
    public static User entityToResponse(com.khongtrungson.shopapp.entities.User user){
        User user1 = new User();
        user1.setId(user.getId());
        user1.setFullName(user.getFullName());
        return user1;
    }
}
