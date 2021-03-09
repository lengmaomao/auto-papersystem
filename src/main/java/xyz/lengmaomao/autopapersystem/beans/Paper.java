package xyz.lengmaomao.autopapersystem.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import xyz.lengmaomao.autopapersystem.VO.PaperCreateVO;

import java.util.*;
import java.util.stream.Collectors;

@Data
public class Paper {
    /**
     * 知识点权重
     */
    public static final double KP_WEIGHT = 0.60;
    /**
     * 难度权重
     */
    public static final double DIFFCULTY_WEIGHt = 0.50;
    //试卷ID
    private int paperId;

    //试卷分数
    private int paperScore;

    //试卷名
    private String paperName;

    //试卷科目
    private Course paperCourse;

    //试卷知识点
    private Set<Knowledge> paperKnowledge;

    //试题总题目
    private List<Subject> totalSubjects;

    //试卷作者
    private int paperAuthor;

    //试卷创建时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date paperCreateTime;

    //试卷权限
    private boolean paperLimit;

    //试卷所属学校
    private String paperSchool;

    //适应度
    private double adaptationDegree = 0.00;

    //知识点覆盖率
    private double kPCoverage = 0.00;

    //试卷难度系数
    private double difficulty = 0.00;

    public Paper(){}
    //Paper构造器,添加一个size
    public Paper(int size) {
        totalSubjects = new ArrayList<>(size);
    }

    //获取难度系数 (计算试卷个体难度系数 计算公式： 每题难度*分数求和除总分)
    public double getDifficulty(){
        this.paperScore = getPaperScore();
        if (difficulty == 0){
            double singleDifficulty = 0;
            for (Subject subject: totalSubjects){
                //每一题的难度为:每一题难度*每一题分数
                singleDifficulty+=((double) subject.getSubjectDifficulty()/10)*subject.getSubjectScore();
            }
            difficulty = singleDifficulty /paperScore;
        }
        return difficulty;
    }

    //计算试卷知识点覆盖率 公式为：个体包含的知识点/期望包含的知识点
    public void setKpCoverage(PaperCreateVO rule){
        this.paperKnowledge = getPaperKnowledge();
        //符合知识点题目计数
        int count=0;
        //获取规则的知识点
        List<Integer> ruleKnowledge = rule.getKnowledge().stream().map(knowledgeVO -> knowledgeVO.getValue()).collect(Collectors.toList());
        //获取试卷的知识点
        List<Integer> paperPoints = totalSubjects.stream().map(subject -> subject.getSubjectKnowledge().getKnowledgeId()).collect(Collectors.toList());
        //循环查看试卷知识点是否包含在规则中
        for (int n : paperPoints){
            if (ruleKnowledge.contains(n))
                count++;
        }
        System.out.println("count:"+count+" paperPoints.size:"+paperPoints.size());
        kPCoverage = (double) count / (double) paperPoints.size();
    }

    /*
     * 计算个体适应度 公式为：f=1-(1-M/N)*f1-|EP-P|*f2
     * 其中M/N为知识点覆盖率，EP为期望难度系数，P为种群个体难度系数，f1为知识点分布的权重
     * ，f2为难度系数所占权重。当f1=0时退化为只限制试题难度系数，当f2=0时退化为只限制知识点分布
     *
     * @param rule 组卷规则
     * @param f1   知识点分布的权重
     * @param f2   难度系数的权重
     */
    public void setAdaptationDegree(PaperCreateVO rule,double f1,double f2){
            adaptationDegree =1 -  (1 - getKPCoverage())*f1 - Math.abs(rule.getDifficult()/10 - getDifficulty())*f2 ;
    }

    //是否包含题目, 题目查重
//    public boolean containsQuestion(Subject subject){
//        if (subject == null)
//            return true;
//        else
//            return totalSubjects.contains(subject);
//    }

    //保存题目
    public void saveQuestion(Subject subject){
        this.totalSubjects.add(subject);
        this.setPaperScore(0);
        this.setDifficulty(0);
        this.setAdaptationDegree(0);
        this.kPCoverage = 0;
    }

    //设置试卷知识点
    public Set<Knowledge> getPaperKnowledge(){
        Set<Knowledge> points = new HashSet<>();
        for (Subject subject:totalSubjects){
            Knowledge point;
            point = subject.getSubjectKnowledge();
            points.add(point);
        }
        paperKnowledge = points;
        return points;
    }

    //设置试卷总分
    public int getPaperScore(){
        int score = 0;
        for (Subject subject:totalSubjects){
            score += subject.getSubjectScore();
        }
        this.paperScore = score;
        return score;
    }


    //打印

    @Override
    public String toString() {
        return "Paper{" + '\n'+
                "paperId=" + paperId + '\n'+
                ", paperScore=" + paperScore + '\n'+
                ", paperName='" + paperName + '\'' + '\n'+
                ", paperCourse=" + paperCourse + '\n'+
                ", paperKnowledge=" + paperKnowledge + '\n'+
                ", totalSubjects=" + totalSubjects + '\n'+
                ", paperAuthor=" + paperAuthor + '\n'+
                ", paperCreateTime=" + paperCreateTime + '\n'+
                ", paperLimit=" + paperLimit + '\n'+
                ", paperSchool='" + paperSchool + '\'' + '\n'+
                ", adaptationDegree=" + adaptationDegree + '\n'+
                ", kPCoverage=" + kPCoverage + '\n'+
                ", difficulty=" + difficulty + '\n'+
                '}';
    }
}
