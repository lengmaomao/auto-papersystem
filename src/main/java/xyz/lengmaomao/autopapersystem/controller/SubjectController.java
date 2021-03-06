package xyz.lengmaomao.autopapersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.beans.Subject;
import xyz.lengmaomao.autopapersystem.service.PaperService;
import xyz.lengmaomao.autopapersystem.service.SubjectService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/subject")
public class SubjectController {
    @Resource
    SubjectService subjectService;
    @Resource
    PaperService paperService;

    /*
        根据id获取固定的题目
     */
    @RequestMapping("/{subjectId}")
    @ResponseBody
    public Subject getSubject(@PathVariable("subjectId")int subjectId){
         Subject subject = subjectService.getSubject(subjectId);
         return subject;
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
    public List<Subject> findAllSubject(int pageNumber,int nums){
        System.out.println("调用接口");
        return subjectService.getAllSubject(pageNumber,nums);
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
    "subjectAnswer":"A",
    "subjectShare": true
}
     */
//    添加试题
    @RequestMapping("/add_subject")
    @ResponseBody
    public int addSubject(@RequestBody Subject subject){
        System.out.println(subject);
        return subjectService.addSubject(subject);
    }

    //更新试题
    @RequestMapping("/update_subject")
    @ResponseBody
    public void updateSubject(@RequestBody Subject subject){
        System.out.println("获取到的subject:" + subject);
        subjectService.updateSubject(subject);
    }

    //将题目添加到试卷
    @RequestMapping("add_subject_to_paper")
    @ResponseBody
    public void addSubjectToPaper(int subjectId, int paperId ){
        System.out.println("subjectId:"+subjectId+" paperId:"+paperId);
        paperService.insertStagPaper(paperId,subjectId,0);
    }

    //将题目从试卷删除
    @RequestMapping("/delete_subject_from_paper")
    @ResponseBody
    public void deleteSubjectFromPaper(int subjectId, int paperId){
        paperService.deleteStagPaper(paperId,subjectId);
    }

    //查询某个用户所有的试题
    @RequestMapping("/user")
    @ResponseBody
    public List<Subject> findSubjectsByUser(HttpServletRequest httpServletRequest,int page,int nums){
        int userId = Integer.valueOf(httpServletRequest.getUserPrincipal().getName());
        System.out.println("userId:"+userId+" page:"+page+" nums:"+nums);
        return subjectService.getSubjectsByUser(userId,page,nums);
    }
    //获取全部公开试题的数量
    @RequestMapping("/getPublicSubjectCount")
    @ResponseBody
    public int getPublicSubjectCount(int page,int nums){
        return subjectService.getPublicSubjectCount(page,nums);
    }
    /*
    返回用户下所有题目数量
     */
    @RequestMapping("/my/num")
    @ResponseBody
    public int getMySubjectNum(HttpServletRequest httpServletRequest){
        int userId = Integer.valueOf(httpServletRequest.getUserPrincipal().getName());
        int num = subjectService.getMySubjectNum(userId);
        System.out.println("mysubjectnums:"+num);
        return num;
    }
}
