package xyz.lengmaomao.autopapersystem.service.Impl;

import org.springframework.stereotype.Service;
import xyz.lengmaomao.autopapersystem.beans.Course;
import xyz.lengmaomao.autopapersystem.mapper.CourseMapper;
import xyz.lengmaomao.autopapersystem.service.CourseService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Resource
    CourseMapper courseMapper;
    @Override
    public int addCourse(Course course) {
        return courseMapper.addCourse(course);
    }

    @Override
    public void deleteCourse(int courseId) {
        courseMapper.deleteCourse(courseId);
    }

    @Override
    public Course findCourse(int courseId) {
        return courseMapper.getCourse(courseId);
    }

    @Override
    public int alterCourse(Course course) {
        return courseMapper.alterCourse(course);
    }

    @Override
    public List<Course> findPaperByTemplate(Course course) {
        return courseMapper.getCourseByTemplate(course);
    }

    @Override
    public List<Course> findAllCourse() {
        return courseMapper.getAllCourse();
    }

    @Override
    public List<Course> getUserCourse(int userId) {
        return courseMapper.getUserCourse(userId);
    }
}
