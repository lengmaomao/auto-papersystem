package xyz.lengmaomao.autopapersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.service.PaperService;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/paper")
public class PaperController {
    @Resource
    PaperService paperService;
    /*
    查询试卷请求
     */
    @RequestMapping("/{paperId}")
    @ResponseBody
    public Paper getPaper(@PathVariable("paperId") int paperId){
        return paperService.findPaper(paperId);
    }

    /*
    模糊查询试卷
     */
    @RequestMapping("/find_paper")
    @ResponseBody
    public List<Paper> findPaper(@RequestBody Paper paper){
        return paperService.findPaperByTemplate(paper);
    }

    /*
    查询所有试卷
     */
    @RequestMapping("/find_all_paper")
    @ResponseBody
    public List<Paper> findAllPaper(){
        return paperService.findAllPaper();
    }
    /*
    添加试卷请求格式:
     {
    "paperName":"广东药科大学附属第一中学2020年期末试卷",
    "paperScore":150,
    "paperCourse":"数据库教程",
    "paperSchool":"广东药科大学",
    "paperAuthor":"宋梓楠",
    "paperCreateTime":"1999-10-9",
    "totalSubjects":[
        {
            "subjectId":1
        },
        {
            "subjectId":2
        },
        {
            "subjectId":3
        }
    ]
}
    * */
    @RequestMapping("/add_paper")
    @ResponseBody
    public int addPaper(@RequestBody Paper paper){
        return paperService.addPaper(paper);
    }
    /*
    删除试卷
     */
    @RequestMapping("/delete_paper/{id}")
    @ResponseBody
    public void deletePaper(@PathVariable("id")int id){
        paperService.deletePaper(id);
    }
}
