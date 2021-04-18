package xyz.lengmaomao.autopapersystem.service.Impl;

import org.springframework.stereotype.Service;
import xyz.lengmaomao.autopapersystem.VO.PaperCreateRule;
import xyz.lengmaomao.autopapersystem.beans.*;
import xyz.lengmaomao.autopapersystem.mapper.PaperMapper;
import xyz.lengmaomao.autopapersystem.service.PaperService;
import xyz.lengmaomao.autopapersystem.sort.PaperSort;

import javax.annotation.Resource;
import java.util.*;

@Service
public class PaperServiceImpl implements PaperService {
    @Resource
    PaperMapper paperMapper;
    @Override
    public int addPaper(Paper paper) {
        paperMapper.insertPaper(paper);
//        System.out.println("paperServiceImpl:"+paper.toString());
        if (paper.getTotalSubjects()!=null){
            for (Subject subject:paper.getTotalSubjects()){
                paperMapper.insertStagPaper(paper.getPaperId(),subject.getSubjectId(),subject.getSubjectPaperId());
            }
        }
        return paper.getPaperId();
    }

    @Override
    public void deletePaper(int paperId) {
        paperMapper.deletePaper(paperId);
        paperMapper.deleteStagPaper(paperId,0);
    }

    @Override
    public int updatePaper(Paper paper) {
        paperMapper.updatePaper(paper);
        if (paper.getTotalSubjects()!=null){
            Paper oldPaper = paperMapper.getPaper(paper.getPaperId());
            if (!oldPaper.getTotalSubjects().equals(paper.getTotalSubjects())){
                Set<Subject> set = new HashSet<>();
                //获取old与paper的差集,并进行删除
                set.addAll(oldPaper.getTotalSubjects());
                set.removeAll(paper.getTotalSubjects());
                for (Subject subject:set){
                    paperMapper.deleteStagPaper(paper.getPaperId(),subject.getSubjectId());
                }
                set.clear();
                //获取paper与old的差集,并进行添加
                set.addAll(paper.getTotalSubjects());
                set.removeAll(oldPaper.getTotalSubjects());
                for (Subject subject:set){
                    paperMapper.insertStagPaper(paper.getPaperId(),subject.getSubjectId(),subject.getSubjectPaperId());
                }
            }
        }
        return paper.getPaperId();
    }

    @Override
    public Paper getPaper(int paperId) {
        return paperMapper.getPaper(paperId);
    }

    @Override
    public Paper findPaper(int paperId) {
        Paper paper = paperMapper.getPaper(paperId);
        List<Subject> subjects = paper.getTotalSubjects();
        //题组排序
        Collections.sort(subjects,new PaperSort());
        //标注题号
        for (int n = 1;n <= subjects.size();n++){
            subjects.get(n-1).setSubjectPaperId(n);
        }
        return paper;

    }

    @Override
    public List<Subject> getPaperSpareSubject(int paperId) {
        return paperMapper.getPaperSpareSubject(paperId);
    }

    @Override
    public List<Paper> findPaperByTemplate(Paper paper) {
        return paperMapper.findPaperByTemplate(paper);
    }

    @Override
    public List<Paper> findAllPaper() {
        return paperMapper.findAllPaper();
    }

    @Override
    public Paper autoPaper(PaperCreateRule rule) {
        Paper resultPaper;
        // 迭代计数器
        int count = 0;
        int runCount = 300;
        // 适应度期望值
        double expand = 0.99;
        //初始化种群
        System.out.println("PaperService-rule:"+rule);
        Population population = new Population(20, true, rule);
        Paper paper = population.getFitness();
        while (count < runCount && population.getFitness().getAdaptationDegree() < expand) {
            count++;
            System.out.println("开始第"+count+"轮进化");
            population = GA.evolvePopulation(population, rule);

            System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree() +" 难度系数:"+paper.makeDifficulty() + " 知识点覆盖率:" + paper.getKPCoverage());
            System.out.println("第"+count+"次进化,最佳适应度个体:" + population.getFitness().toString());
        }
        resultPaper = population.getFitness();
        //题组排序
        Collections.sort(resultPaper.getTotalSubjects(),new PaperSort());
        //标注题号
        for (int n = 1;n <= resultPaper.getTotalSubjects().size();n++){
            resultPaper.getTotalSubjects().get(n-1).setSubjectPaperId(n);
        }
        Course course = new Course();
        course.setCourseId(rule.getCourseId());
        resultPaper.setPaperCourse(course);
        resultPaper.setPaperLimit(rule.isShare());
        return resultPaper;
    }

    @Override
    public List<Paper> getUserPaper(int userId,int pageNumber,int nums) {
        List<Paper> papers = paperMapper.getUserPaper(userId,(pageNumber-1)*nums,nums);
        System.out.println("PaperService:paperSize:"+papers.size());
        return papers;
    }

    @Override
    public void insertStagPaper(int paperId, int subjectId, int subjectPaperId) {
        paperMapper.insertStagPaper(paperId,subjectId,subjectPaperId);
    }

    @Override
    public void deleteStagPaper(int paperId, int subjectId) {
        paperMapper.deleteStagPaper(paperId,subjectId);
    }

    @Override
    public int findUserPaperNums(int userId) {
        return paperMapper.findUserPaperNums(userId);
    }
}
