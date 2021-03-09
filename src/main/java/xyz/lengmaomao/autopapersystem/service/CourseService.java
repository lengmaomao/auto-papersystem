package xyz.lengmaomao.autopapersystem.service;

import xyz.lengmaomao.autopapersystem.beans.Course;

import java.util.List;

public interface CourseService {
    //添加课程类别
    int addCourse(Course course);
    //删除课程类别
    void deleteCourse(int courseId);
    //查询课程类别
    Course findCourse(int courseId);
    //修改课程类别
    int alterCourse(Course course);
    //模糊查询课程类别
    List<Course> findPaperByTemplate(Course course);
    //查询所有课程类别
    List<Course> findAllCourse();
    //查询某个用户所有的课程
    List<Course> getUserCourse(int userId);
}
