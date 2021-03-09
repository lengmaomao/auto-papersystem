package xyz.lengmaomao.autopapersystem.beans;

import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import xyz.lengmaomao.autopapersystem.VO.PaperCreateVO;
import xyz.lengmaomao.autopapersystem.service.SubjectService;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.*;

@Data
@Component
public class Population {

    @Resource
    protected SubjectService subjectService;
    private static Population population;

    @PostConstruct
    public void init(){
        population = this;
        population.subjectService = this.subjectService;
    }
    /**
    * 试卷集合
    */
    public List<Paper> papers = new ArrayList<>();

    public Population(){};

    /**
     * 初始化种群
     *
     * @param populationSize 种群规模
     * @param initFlag       是否为初始化种群
     * @param rule           组卷规则
     */
    public Population(int populationSize, boolean initFlag, PaperCreateVO rule){
        if (initFlag){
            for (int n=0;n<populationSize;n++){
                Paper paper = new Paper();
                //试卷题目
                Set<Subject> set = new HashSet<>();
                //设置试卷临时ID
                paper.setPaperId(n+1);
                    //随机添加单选题
                    if (rule.getSubjects().getSingle_select()!=0){
                        set.addAll(population.subjectService.getSubjectByType(Subject.SUBJECT_TYPE_SELECT_SINGLE,rule.getSubjects().getSingle_select(),rule.getAuthor()));
                    }
                    //随机添加填空题
                    if (rule.getSubjects().getCompletion_subject()!=0){
                        set.addAll(population.subjectService.getSubjectByType(Subject.SUBJECT_TYPE_COMPLETION,rule.getSubjects().getCompletion_subject(),rule.getAuthor()));
                    }
                    //随机添加综合题
                    if (rule.getSubjects().getComprehensive_subject()!=0){
                        set.addAll(population.subjectService.getSubjectByType(Subject.SUBJECT_TYPE_COMPREHENSIVE,rule.getSubjects().getComprehensive_subject(),rule.getAuthor()));
                    }
                    //随机添加多选题
                    if (rule.getSubjects().getMultiple_select()!=0){
                        set.addAll(population.subjectService.getSubjectByType(Subject.SUBJECT_TYPE_SELECT_MULTIPLE,rule.getSubjects().getMultiple_select(),rule.getAuthor()));
                    }
                    //随机添加判断题
                    if (rule.getSubjects().getTrue_false_subject()!=0){
                        set.addAll(population.subjectService.getSubjectByType(Subject.SUBJECT_TYPE_TRUE_OR_FALSE,rule.getSubjects().getTrue_false_subject(),rule.getAuthor()));
                    }
                    //随机添加简答题
                    if (rule.getSubjects().getWritten_subject()!=0){
                        set.addAll(population.subjectService.getSubjectByType(Subject.SUBJECT_TYPE_WRITTEN,rule.getSubjects().getWritten_subject(),rule.getAuthor()));
                    }
                    //设置试卷
                    paper.setTotalSubjects(new ArrayList<>(set));
                    paper.setPaperAuthor(rule.getAuthor());
                    paper.getPaperKnowledge();
                    paper.getPaperScore();
                    //计算试卷知识点覆盖率
                    paper.setKpCoverage(rule);
                    //计算试卷适应度
                    paper.setAdaptationDegree(rule,Paper.KP_WEIGHT,Paper.DIFFCULTY_WEIGHt);
                    papers.add(paper);
            }
        }
    }

    /**
     * 获取种群最优秀个体
     *
     */
    public Paper getFitness(){
        if (papers!=null && papers.size()!=0){
            Paper temp = papers.get(0);
            for (Paper paper: papers){
                if (paper.getAdaptationDegree()>temp.getAdaptationDegree()){
                    temp = paper;
                }
            }
            return  temp;
        }
        return null;
    }
}
