package xyz.lengmaomao.autopapersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.beans.Subject;
import xyz.lengmaomao.autopapersystem.service.SubjectService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    @Resource
    SubjectService subjectService;

    /*
        根据id获取固定的题目
     */
    @RequestMapping("/{subjectId}")
    @ResponseBody
    public Subject getSubject(@PathVariable("subjectId")int subjectId){
        return subjectService.getSubject(subjectId);
    }
    /*
        模糊查询
     */
    @RequestMapping("/find_subject")
    @ResponseBody
    public List<Subject> findSubject(Subject subject){
        return subjectService.getSubjectByTemplate(subject);
    }

    /*
        获取所有subject
     */
    @RequestMapping("/find_all_subject")
    @ResponseBody
    public List<Subject> findAllSubject(){
        return subjectService.getAllSubject();
    }
    /*
        删除subject
     */

    @RequestMapping("/delete_subject/{subjectId}")
    @ResponseBody
    public boolean deleteSubject(@PathVariable("subjectId") int subjectId){
        subjectService.deleteSubject(subjectId);
        return true;
    }

    /*
        {
    "subjectCourse":{
        "courseId":1
    },
    "subjectKnowledge":{
        "knowledgeId":1
    },
    "subjectScore":150,
    "subjectType":"多项选择题",
    "subjectDescribe":"这是一道前端传回json后台的选择题2,请选择()\n A.A B.B C.C D.D",
    "subjectAnswer":"A"
}
     */
    @RequestMapping("/add_subject")
    @ResponseBody
    public int addSubject(@RequestBody Subject subject){
        return subjectService.addSubject(subject);
    }
}
