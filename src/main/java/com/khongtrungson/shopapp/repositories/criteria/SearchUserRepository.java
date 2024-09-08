package com.khongtrungson.shopapp.repositories.criteria;

import com.khongtrungson.shopapp.dtos.responses.PageResponse;
import com.khongtrungson.shopapp.dtos.responses.UserResponse;
import com.khongtrungson.shopapp.entities.User;
import com.khongtrungson.shopapp.mappers.UserMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Repository
public class SearchUserRepository {

    @PersistenceContext
    private EntityManager entityManager ;

    public PageResponse<List<UserResponse>> searchByCriteria(int pageNo, int pageSize, String sortBy, String... search) {
    //b1: search=firstName:long,age<18,he<90...
        List<Condition> criteriaList = new ArrayList<>();

        if (search != null) {
            Pattern pattern = Pattern.compile("(\\w+?)(:|>|<)(.*)");
            for (String s : search) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    criteriaList.add(new Condition(matcher.group(1), matcher.group(2), matcher.group(3)));
                }
            }
        }

        if (StringUtils.hasLength(sortBy)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(asc|decs)");
            for (String s : search) {
                Matcher matcher = pattern.matcher(s);
                if (matcher.find()) {
                    criteriaList.add(new Condition(matcher.group(1), matcher.group(2), matcher.group(3)));
                }
            }
        }

        List<User> users = getUsers((pageNo-1)*pageSize, pageSize, criteriaList);
        var userResponses = users.stream().map((user)-> UserMapper.userToUserResponse(user)).
                collect(Collectors.toList());
        Long totalElements = getTotalElements(criteriaList);


        return PageResponse.<List<UserResponse>>builder()
                .page(pageNo)
                .size(pageSize)
                .total((long)totalElements/pageSize)
                .items(userResponses)
                .build();
    }

    private  List<User> getUsers(int offset, int pageSize, List<Condition> criteriaList) {
        // create an builder for build query
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // select and map data to user object
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        // define a root entity in this case <==> from User
        Root<User> root = criteriaQuery.from(User.class);


        // create multi clause conditional statement and
        Predicate predicate = criteriaBuilder.conjunction();
        List<Order> orders = new ArrayList<>();
        var queryConsumer = new CriteriaConsumer(orders,predicate,criteriaBuilder,root);
        criteriaList.forEach(queryConsumer);
        predicate = queryConsumer.getPredicate();
        criteriaQuery.select(root).where(predicate).orderBy(orders);
        TypedQuery<User> jpqlQuery = entityManager.createQuery(criteriaQuery);
        return jpqlQuery.setFirstResult(offset).setMaxResults(pageSize).getResultList();
    }
    private  Long getTotalElements(List<Condition> params) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);

        Root<User> root = query.from(User.class);

        Predicate predicate = criteriaBuilder.conjunction();

        var searchConsumer = new CriteriaConsumer(null,predicate, criteriaBuilder, root);

        params.forEach(searchConsumer);
        predicate = searchConsumer.getPredicate();
        query.select(criteriaBuilder.count(root)).where(predicate);

        return entityManager.createQuery(query).getSingleResult();
    }

}
