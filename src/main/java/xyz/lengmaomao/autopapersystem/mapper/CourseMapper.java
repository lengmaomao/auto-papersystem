package xyz.lengmaomao.autopapersystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import xyz.lengmaomao.autopapersystem.beans.Course;

import java.util.List;


@Mapper
public interface CourseMapper {
    //获得课程(ID)
    Course getCourse(int courseId);
    //修改课程
    int alterCourse(Course course);
    //删除课程
    void deleteCourse(int courseId);
    //添加课程
    int addCourse(Course course);
    //模糊查询课程类别
    List<Course> getCourseByTemplate(Course course);
    //查询所有课程类别
    List<Course> getAllCourse();
}
