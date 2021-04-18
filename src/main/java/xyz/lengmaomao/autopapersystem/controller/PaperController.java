package xyz.lengmaomao.autopapersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lengmaomao.autopapersystem.VO.PaperCreateRule;
import xyz.lengmaomao.autopapersystem.VO.PaperVO;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.beans.Subject;
import xyz.lengmaomao.autopapersystem.service.PaperService;
import xyz.lengmaomao.autopapersystem.service.SubjectService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/paper")
public class PaperController {
    @Resource
    PaperService paperService;
    @Resource
    SubjectService subjectService;
    /*
    查询试卷请求
     */
    @RequestMapping("/{paperId}")
    @ResponseBody
    public Paper getPaper(@PathVariable("paperId") int paperId){
        return paperService.findPaper(paperId);
    }

    /*
    查询试卷中不包含在试题集合的其余实体
     */
    @RequestMapping("/spareSubject/{paperId}")
    @ResponseBody
    public List<Subject> getPaperSpareSubject(@PathVariable("paperId") int paperId){
        return paperService.getPaperSpareSubject(paperId);
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
    public int addPaper(@RequestBody PaperCreateRule paperVO, HttpServletRequest httpServletRequest){
        System.out.println("paperVO.subjects:"+paperVO.toString());
        Paper paper;
        paperVO.setAuthor(Integer.valueOf(httpServletRequest.getUserPrincipal().getName())); //获取作者
        paper = paperVO.transfer();
        return paperService.addPaper(paper);
    }

    /*
    将自动生成试卷添加到试卷库中
     */
    @RequestMapping("/add_auto_paper")
    public String addAutoPaper(HttpSession session){
        PaperVO paperVO = (PaperVO) session.getAttribute("auto-paper");
        Paper paper = paperVO.getPaper();
        if (paper != null){
            paperService.addPaper(paper);
        }
        return "index";
    }
    /*
    自动生成试卷
     */
    @RequestMapping("/auto_paper")
    @ResponseBody
    public PaperVO createAutoPaper(@RequestBody PaperCreateRule rule, HttpServletRequest httpServletRequest){
        //返回的PaperVo
        PaperVO paperVO = new PaperVO();
        rule.setAuthor(Integer.valueOf(httpServletRequest.getUserPrincipal().getName()));
        String message = "";
        int code = 200;
        //检测试题数量是否足够
        if (rule.getSubjects().getSingle_select()!=0&&rule.getSubjects().getSingle_select()> subjectService.getSubjectsNum(Subject.SUBJECT_TYPE_SELECT_SINGLE,rule.getAuthor())){
            message += "单选题不足,无法组卷 ";
            code = 400;
        }
        if (rule.getSubjects().getMultiple_select()!=0&&rule.getSubjects().getMultiple_select()> subjectService.getSubjectsNum(Subject.SUBJECT_TYPE_SELECT_MULTIPLE,rule.getAuthor())){
            message += "多题不足,无法组卷 ";
            code = 400;
        }
        if (rule.getSubjects().getTrue_false_subject()!=0&&rule.getSubjects().getTrue_false_subject()> subjectService.getSubjectsNum(Subject.SUBJECT_TYPE_TRUE_OR_FALSE,rule.getAuthor())){
            message += "判断题不足,无法组卷 ";
            code = 400;
        }
        if (rule.getSubjects().getSingle_select()!=0&&rule.getSubjects().getSingle_select()> subjectService.getSubjectsNum(Subject.SUBJECT_TYPE_SELECT_SINGLE,rule.getAuthor())){
            message += "单选题不足,无法组卷 ";
            code = 400;
        }
        if (rule.getSubjects().getCompletion_subject()!=0&&rule.getSubjects().getCompletion_subject()> subjectService.getSubjectsNum(Subject.SUBJECT_TYPE_COMPLETION,rule.getAuthor())){
            message += "填空题不足,无法组卷 ";
            code = 400;
        }
        if (rule.getSubjects().getComprehensive_subject()!=0&&rule.getSubjects().getComprehensive_subject()> subjectService.getSubjectsNum(Subject.SUBJECT_TYPE_COMPREHENSIVE,rule.getAuthor())){
            message += "综合题不足,无法组卷 ";
            System.out.println(message);
            code = 400;
        }
        //设置返回码与返回字符串
        paperVO.setCode(code);
        paperVO.setMessage(message);
        if (code == 200){
            //自动组卷核心方法
            Paper paper = paperService.autoPaper(rule);
            paper.setPaperAuthor(Integer.valueOf(httpServletRequest.getUserPrincipal().getName()));
            paper.setPaperName(rule.getPaperName());
            paper.setPaperCreateTime(new Date());
            //将临时paper存入session中
            paperVO.setMessage("成功组卷");
            paperVO.setPaper(paper);
        }
//        将组卷规则和组成的试卷装入
        httpServletRequest.getSession().setAttribute("auto-paper",paperVO);
        httpServletRequest.getSession().setAttribute("auto-paper-rule",rule);
        return paperVO;
    }
    /*
    重新自动生成试卷
     */
    @RequestMapping("/reload")
    @ResponseBody
    public PaperVO reloadAutoPaper(HttpServletRequest httpServletRequest){
        PaperCreateRule rule = (PaperCreateRule) httpServletRequest.getSession().getAttribute("auto-paper-rule");
        PaperVO paperVO = createAutoPaper(rule,httpServletRequest);
        System.out.println(paperVO);
        return paperVO;
    }
    /*
    获取session中存储的paperVO
     */
    @RequestMapping("/getTemp")
    @ResponseBody
    public PaperVO getTempPaper(HttpServletRequest request, HttpSession session){
        PaperVO paperVO1 = (PaperVO) session.getAttribute("auto-paper");
        PaperVO paperVO2 = (PaperVO) request.getSession().getAttribute("auto-paper");
        System.out.println("paper1==paper2"+(paperVO1==paperVO2));
        return paperVO1;
    }
    /*
    删除试卷
     */
    @RequestMapping("/delete_paper/{id}")
    @ResponseBody
    public void deletePaper(@PathVariable("id")int id){
        paperService.deletePaper(id);
    }
    /*
    获取个人所有试卷
     */
    @RequestMapping("/user")
    @ResponseBody
    public List<Paper> findPapersByUser(HttpServletRequest httpServletRequest,int pageNumber,int nums){
        int userId = Integer.valueOf(httpServletRequest.getUserPrincipal().getName());
        List<Paper> papers = paperService.getUserPaper(userId,pageNumber,nums);
        System.out.println("papersSize:"+papers.size());
        return papers;
    }
    /*
    获取个人试卷数量
     */
    @RequestMapping("/user/nums")
    @ResponseBody
    public int findUserPaperNums(HttpServletRequest httpServletRequest){
        int userId = Integer.valueOf(httpServletRequest.getUserPrincipal().getName());
        return paperService.findUserPaperNums(userId);
    }
}
