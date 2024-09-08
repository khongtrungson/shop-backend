package com.khongtrungson.shopapp.repositories.criteria;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Consumer;
@AllArgsConstructor
@Getter
public class CriteriaConsumer implements Consumer<Condition> {
    private List<Order> orders;
    private Predicate predicate;
    private CriteriaBuilder builder;
    private Root<?> root;
    @Override
    public void accept(Condition param) {
        if (param.getOperation().equalsIgnoreCase(">")) {
            predicate = builder.and(predicate, builder
                    .greaterThanOrEqualTo(root.get(param.getKey()), param.getValue().toString()));
        } else if (param.getOperation().equalsIgnoreCase("<")) {
            predicate = builder.and(predicate, builder.lessThanOrEqualTo(
                    root.get(param.getKey()), param.getValue().toString()));
        } else if (param.getOperation().equalsIgnoreCase(":")) {
            if(param.getValue().equalsIgnoreCase("desc")){
                orders.add(builder.desc(root.get(param.getKey())));
            } else if (param.getValue().equalsIgnoreCase("asc")) {
                orders.add(builder.asc(root.get(param.getKey())));
            }
            else if (root.get(param.getKey()).getJavaType() == String.class) {
                predicate = builder.and(predicate, builder.like(
                        root.get(param.getKey()), "%" + param.getValue() + "%"));
            }
        }
    }
}
