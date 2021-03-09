package xyz.lengmaomao.autopapersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lengmaomao.autopapersystem.beans.Course;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.mapper.CourseMapper;
import xyz.lengmaomao.autopapersystem.service.CourseService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Resource
    CourseService courseService;

    /*
        根据ID获取科目
     */
    @RequestMapping("/{courseId}")
    @ResponseBody
    public Course getCourse(@PathVariable("courseId") int courseId){
        return courseService.findCourse(courseId);
    }

    /*
        模糊查询
     */
    @RequestMapping("/find_course")
    @ResponseBody
    public List<Course> findCourse(Course course){
        return courseService.findPaperByTemplate(course);
    }
    /*
        全部查询
     */
    @RequestMapping("/all_course")
    @ResponseBody
    public List<Course> findAllCourse(){
        return courseService.findAllCourse();
    }
    /*
        添加科目
        {
            "courseName":"科目名字",
            "courseBook":"科目所属教材",
            "courseChapter":"科目所属章节"
        }
     */
    @RequestMapping("/add_course")
    @ResponseBody
    public int addPaper(@RequestBody Course course){
        return courseService.addCourse(course);
    }
    /*
        删除科目
     */
    @RequestMapping("/delete_course/{id}")
    @ResponseBody
    public void deletePaper(@PathVariable("id")int id){
        courseService.deleteCourse(id);
    }

    //查询一个用户的所有科目
    @RequestMapping("/user_courses")
    @ResponseBody
    public List<Course> userCourses(HttpServletRequest httpServletRequest){
        int userId = Integer.valueOf(httpServletRequest.getUserPrincipal().getName());
        return courseService.getUserCourse(userId);
    }
}
