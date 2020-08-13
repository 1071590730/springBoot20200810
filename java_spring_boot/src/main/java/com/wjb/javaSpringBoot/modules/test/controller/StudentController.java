package com.wjb.javaSpringBoot.modules.test.controller;

import com.wjb.javaSpringBoot.modules.common.vo.Result;
import com.wjb.javaSpringBoot.modules.common.vo.SearchVo;
import com.wjb.javaSpringBoot.modules.test.entity.Student;
import com.wjb.javaSpringBoot.modules.test.service.StudentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ASUS on 2020/8/12 20:35
 */

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentSevice studentSevice;

    /**
     * 127.0.0.1/api/student ---- post
     * {"studentName":"hujiang1","studentCard":{"cardId":"1"}}
     */
    @PostMapping(value = "student", consumes = "application/json")
    public Result<Student> insertStudent(@RequestBody Student student) {
        return studentSevice.insertStudent(student);
    }

    /**
     * 127.0.0.1/api/student/1 ---get
     */
    @GetMapping("/student/{studentId}")
    public Student getStudentByStudentId(@PathVariable int studentId){
        return studentSevice.getStudentByStudentId(studentId);
    }

    /**
     * 127.0.0.1/api/students ---- post
     * {"currentPage":"1","pageSize":"5","keyWord":"w","orderBy":"studentName","sort":"asc"}
     */
    @PostMapping(value = "/students", consumes = "application/json")
    public Page<Student> getStudentsBySearchVo(@RequestBody SearchVo searchVo) {
        return studentSevice.getStudentsBySearchVo(searchVo);
    }

    /**
     *127.0.0.1/api/students?studentName=wangjianbiao1 ---- get
     */
//    @GetMapping("/students")
//    public List<Student> getStudentsByStudentName(@RequestParam String studentName,){
//        return studentSevice.getStudentsByStudentName( studentName);
//    }

    /**
     *127.0.0.1/api/students?studentName=wangjianbiao3&cardId=3 ---- get
     */
    @GetMapping("/students")
    public List<Student> getStudentsByParams(
            @RequestParam String studentName,
            //@RequestParam(required = false(false可有可无), defaultValue = "0"（默认值）)
            @RequestParam(required = false, defaultValue = "0") Integer cardId) {
        return studentSevice.getStudentsByStudentName(studentName, cardId);
    }


}