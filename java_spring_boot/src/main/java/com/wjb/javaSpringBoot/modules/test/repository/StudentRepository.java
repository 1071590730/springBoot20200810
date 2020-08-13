package com.wjb.javaSpringBoot.modules.test.repository;

import com.wjb.javaSpringBoot.modules.test.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ASUS on 2020/8/12 20:12
 */
@Repository
public interface StudentRepository  extends JpaRepository<Student, Integer> {
    //名字查询
    List<Student> findByStudentName(String studentName);
    //名字模糊查询
    List<Student> findByStudentNameLike(String studentName);
    //名字模糊查询2条
    List<Student> findTop2ByStudentNameLike(String studentName);

//    @Query(value = "select s from Student s where s.studentName = ?1 and s.studentCard.cardId = ?2")
//    @Query(value = "select s from Student s where s.studentName = :studentName " +
//            "and s.studentCard.cardId = :cardId")
    @Query(nativeQuery = true,
            value = "select * from h_student where student_name = :studentName " +
                    "and card_id = :cardId")
    List<Student> getStudentsByParams(@Param("studentName") String studentName,
                                      @Param("cardId") int cardId);
}
