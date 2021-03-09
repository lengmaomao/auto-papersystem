package xyz.lengmaomao.autopapersystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.lengmaomao.autopapersystem.VO.KnowledgeVO;
import xyz.lengmaomao.autopapersystem.VO.PaperCreateVO;
import xyz.lengmaomao.autopapersystem.VO.SubjectTypeVO;
import xyz.lengmaomao.autopapersystem.beans.*;
import xyz.lengmaomao.autopapersystem.mapper.CourseMapper;
import xyz.lengmaomao.autopapersystem.mapper.SubjectMapper;
import xyz.lengmaomao.autopapersystem.service.SubjectService;
import xyz.lengmaomao.autopapersystem.service.UserService;
import xyz.lengmaomao.autopapersystem.utils.DB;

import javax.annotation.Resource;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class AutoPapersystemApplicationTests {
    @Resource
    UserService userService;
    @Resource
    SubjectService subjectService;
    @Test
    void contextLoads() {
        List<Paper> papers;
        List<KnowledgeVO> points = new ArrayList<>();
        KnowledgeVO knowledge1 = new KnowledgeVO();
        KnowledgeVO knowledge2 = new KnowledgeVO();
        KnowledgeVO knowledge3 = new KnowledgeVO();
        knowledge1.setValue(1);
        knowledge2.setValue(2);
        knowledge3.setValue(3);
        points.add(knowledge1);
        points.add(knowledge2);
        points.add(knowledge3);

        PaperCreateVO paperCreateVO = new PaperCreateVO();
        paperCreateVO.setDifficult(2);
        paperCreateVO.setKnowledge(points);
        DB db = new DB();
        papers = db.getPaperDB();
        for (Paper paper : papers){
            paper.setKpCoverage(paperCreateVO);
            paper.setAdaptationDegree(paperCreateVO,Paper.KP_WEIGHT,Paper.DIFFCULTY_WEIGHt);
//            System.out.println("试卷"+paper.getPaperId()+"知识点覆盖率:"+paper.getKPCoverage()+" 难度系数:"+paper.getDifficulty()+" 适应度:"+paper.getAdaptationDegree());
            System.out.println(paper.getAdaptationDegree());
        }
        Population population = new Population();
        population.setPapers(papers);

        Paper p = GA.select(population);
        System.out.println(p.getAdaptationDegree());
    }

    @Test
    void addSubject(){
        Knowledge knowledge = new Knowledge();
        Subject subject = new Subject();
        Course course = new Course();
        course.setCourseId(1);
        subject.setSubjectScore(5);
        subject.setSubjectType(Subject.SUBJECT_TYPE_WRITTEN);
        subject.setSubjectDescribe("测试题");
        subject.setSubjectAnswer("测试答案");

        for (int i = 1; i < 30; i++){
            subject.setSubjectName("测试"+i);
            knowledge.setKnowledgeId(randomKnowledge());
            subject.setSubjectDifficulty(randomDifferent());
            subject.setSubjectCourse(course);
            subject.setSubjectKnowledge(knowledge);
            subjectService.addSubject(subject);
        }
    }

    @Test
    void getPopulation(){
        List<KnowledgeVO> points = new ArrayList<>();
        KnowledgeVO knowledge1 = new KnowledgeVO();
        KnowledgeVO knowledge2 = new KnowledgeVO();
        KnowledgeVO knowledge3 = new KnowledgeVO();
        knowledge1.setValue(1);
        knowledge2.setValue(2);
        knowledge3.setValue(3);
        points.add(knowledge1);
        points.add(knowledge2);
        points.add(knowledge3);
        PaperCreateVO rule = new PaperCreateVO();
        SubjectTypeVO subjectTypeVO = new SubjectTypeVO();
        subjectTypeVO.setSingle_select(5);
        rule.setSubjects(subjectTypeVO);
        rule.setKnowledge(points);
        //设置规则难度
        rule.setDifficult(2);
        Population population = new Population(30,true,rule);
    }

    @Test
    public void qwq(){
        System.out.println(randomKnowledge());
        System.out.println(randomDifferent());
    }
    public int randomKnowledge(){
        return (int) (Math.random()*(6-1)+1);
    }

    public int randomDifferent(){
        return (int) (Math.random()*(3-1)+1);
    }

    @Test
    public void envoTest(){
        List<KnowledgeVO> points = new ArrayList<>();
        KnowledgeVO knowledge1 = new KnowledgeVO();
        SubjectTypeVO subjectTypeVO = new SubjectTypeVO();
        //单选题:20 多选题:10 判断题:10 填空题:2
        subjectTypeVO.setSingle_select(20);
        subjectTypeVO.setCompletion_subject(10);
        subjectTypeVO.setTrue_false_subject(10);
        subjectTypeVO.setComprehensive_subject(2);
        knowledge1.setValue(1);
        points.add(knowledge1);

        PaperCreateVO rule = new PaperCreateVO();
        rule.setDifficult(2);
        rule.setKnowledge(points);
        rule.setSubjects(subjectTypeVO);

        Paper resultPaper = null;
        // 迭代计数器
        int count = 0;
        int runCount = 300;
        // 适应度期望值
        double expand = 0.99;
        // 可自己初始化组卷规则rule
        if (rule != null) {
            // 初始化种群
            Population population = new Population(20, true, rule);
            Paper paper = population.getFitness();
            System.out.println("初次适应度  " + paper.getAdaptationDegree() +" 难度系数:"+paper.getDifficulty() + " 知识点覆盖率:" + paper.getKPCoverage());
            while (count < runCount && population.getFitness().getAdaptationDegree() < expand) {
                count++;
                System.out.println("开始第"+count+"轮进化");
                population = GA.evolvePopulation(population, rule);

                System.out.println("第 " + count + " 次进化，适应度为： " + population.getFitness().getAdaptationDegree() +" 难度系数:"+paper.getDifficulty() + " 知识点覆盖率:" + paper.getKPCoverage());
                System.out.println("第"+count+"次进化,最佳适应度个体:" + population.getFitness().toString());
            }
            System.out.println("进化次数： " + count);
            System.out.println(population.getFitness().getAdaptationDegree());
            resultPaper = population.getFitness();
        }
        System.out.println(resultPaper);
    }

}


