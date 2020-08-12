package com.wjb.javaSpringBoot.modules.test.repository;

import com.wjb.javaSpringBoot.modules.test.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ASUS on 2020/8/12 20:10
 */
//持久层注解
@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
}
