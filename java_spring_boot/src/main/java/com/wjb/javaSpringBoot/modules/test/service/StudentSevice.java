package com.wjb.javaSpringBoot.modules.test.service;

import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.test.entity.Student;

/**
 * Created by ASUS on 2020/8/12 20:32
 */
public interface StudentSevice {
    Result<Student> insertStudent(Student student);
}
