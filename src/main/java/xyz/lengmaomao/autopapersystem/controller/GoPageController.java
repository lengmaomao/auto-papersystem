package xyz.lengmaomao.autopapersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GoPageController {
    //前往主页页面
    @RequestMapping("/index")
    public String goToIndex(){
        return "index";
    }
    //前往管理员界面
    @RequestMapping("/admin")
    public String goAdminPage(){return "/admin/adminIndex";}
    //登录页面
    @RequestMapping("/login")
    public String goLogin(){return "login";}
    //前往题库总览页面
//    @RequestMapping("/subjects")
//    public String goToSubject(HttpServletRequest httpServletRequest){
//        System.out.println("WELCOME! "+httpServletRequest.getUserPrincipal().getName());
//        return "subject/subject";
//    }
    @RequestMapping("/subjects")
    public String goToSubject(HttpServletRequest httpServletRequest){
        System.out.println("WELCOME! "+httpServletRequest.getUserPrincipal().getName());
        return "subject/subject";
    }
    //添加试题页面
    @RequestMapping("/subjects/addSubjects/")
    public String goToAddSubject(){return "subject/addSubject";}
    //总览试卷页面
    @RequestMapping("/subjects/subjectList/")
    public String goSubjectList(){return "subject/subjectList";}
    //我的试题页面
    @RequestMapping("/subjects/mySubject/")
    public String mySubject(){return "subject/mySubject";}

    //管理面板
    @RequestMapping("/admin/stats")
    public String systemStats(){return "admin/systemStats";}

    //添加试卷页面
    @RequestMapping("/papers/addPaper/")
    public String addPaper(){return "paper/addPaper";}
    //自动试卷生成预览页面
    @RequestMapping("/papers/showPaper/{paperId}")
    public String autoPaper(@PathVariable int paperId){return "paper/showPaper";}
    //前往试卷总览页面
    @RequestMapping("/create/paper")
    public String goToPaper(){
        return "/paper/createpaper";
    }
    //前往我的试卷页面
    @RequestMapping("/papers/myPaper/")
    public String myPaper(){return "/paper/myPaper";}
    //测试页面
    @RequestMapping("/test")
    public String test(){return "test";}
}
