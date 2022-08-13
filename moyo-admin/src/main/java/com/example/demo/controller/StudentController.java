package com.example.demo.controller;
import com.example.demo.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

//类注解
@RestController
@RequestMapping("/student")
@Api(tags = "联系人接口tag123s")
public class StudentController {
    @GetMapping("/getStudent")
    @ApiOperation("根据ID查询用户")
    @ApiImplicitParam(name = "id",value = "用户ID",defaultValue = "9999",required = true)
    public Object getStudent(){
//        return user;

        Student user1 = new Student();
        user1.setId(1);
        user1.setName("name");
        user1.setPwd("666");
        return user1;
    }
    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    @ApiOperation("获取用户信息get")
    @ApiImplicitParam(name = "id",value = "车位表对应的id",dataType = "int ")
    public Student getUser()
    {
        Student user = new Student();
        user.setId(1);
        user.setName("name");
        user.setPwd("123");
        return user;
    }
    @PostMapping("/postStudent")
    @ApiOperation("获取post123用户信息")
    @ApiImplicitParam(name = "student",value = "学生信息对象",dataType = "Student")
    public Object postStudent(@Validated @RequestBody Student student) {
        return student;
    }
}

