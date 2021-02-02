package xyz.lengmaomao.autopapersystem.beans;

import lombok.Data;

import java.util.List;

/*
    用户(教师)类
    userId:用户ID
    userName:用户姓名
    userCourse:用户所任职的课程
 */
@Data
public class User {
    private int userId;
    private String userName;
    private String userPassword;
    private String userRole;
    private List<Course> userCourse;
    private List<Paper> userPaper;
    private List<Subject> userSubject;
}
