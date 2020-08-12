package com.wjb.javaSpringBoot.modules.test.repository;

import com.wjb.javaSpringBoot.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ASUS on 2020/8/12 20:12
 */
@Repository
public interface StudentRepository  extends JpaRepository<Student, Integer> {
}
