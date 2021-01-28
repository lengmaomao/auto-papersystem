package xyz.lengmaomao.autopapersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GoPageController {
    //前往主页页面
    @RequestMapping("/index")
    public String goToIndex(){
        return "index";
    }
    //前往试卷总览页面
    @RequestMapping("/paper")
    public String goToPaper(){
        return "paper/paper";
    }
    //前往题库总览页面
    @RequestMapping("/subjects")
    public String goToSubject(){
        return "subject/subject";
    }
    @RequestMapping("/subjects/addSubjects/")
    public String goToAddSubject(){return "subject/addSubject";}
    @RequestMapping("/subjects/subjectList/")
    public String goSubjectList(){return "subject/subjectList";}
    @RequestMapping("/subjects/mySubject/")
    public String mySubject(){return "subject/mySubject";}
    //测试页面
    @RequestMapping("/test")
    public String test(){return "test";}
}
