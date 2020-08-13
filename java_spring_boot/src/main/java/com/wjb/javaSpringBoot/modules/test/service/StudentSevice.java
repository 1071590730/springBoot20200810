package com.wjb.javaSpringBoot.modules.test.service;

import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;
import com.wjb.javaSpringBoot.modules.test.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Created by ASUS on 2020/8/12 20:32
 */
public interface StudentSevice {

    //增加
    Result<Student> insertStudent(Student student);

    //查询
    Student getStudentByStudentId(int studebtId);

    //条件分页排序
    Page<Student> getStudentsBySearchVo(SearchVo searchVo);

//    //list所有
//    List<Student> getStudents();

    //属性查询
    List<Student> getStudentsByStudentName(String studentName, int cardId);
}
