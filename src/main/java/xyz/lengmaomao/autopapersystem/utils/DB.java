package xyz.lengmaomao.autopapersystem.utils;

import xyz.lengmaomao.autopapersystem.beans.Knowledge;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.beans.Subject;

import java.util.*;

public class DB {
    //模拟试卷数据库大小
    private static final int PAPER_DB_SIZE = 30;

    //模拟试题库大小
    private static final int SUBJECT_DB_SIZE = 500;

    //试卷列表
    List<Paper> papers = new ArrayList<>();

    //试题列表
    List<Subject> subjects = new ArrayList<>();

    //生成试题
    public List<Subject> getSubjectDB(){
        for (int i = 0;i < SUBJECT_DB_SIZE; i++){
            //创建subject
            Subject subject = new Subject();
            Knowledge knowledge = new Knowledge();
            knowledge.setKnowledgeId(randomPoints());
            //设置subject编号ID
            subject.setSubjectId(i);
            subject.setSubjectDifficulty(randomDifficult());
            subject.setSubjectKnowledge(knowledge);
            subject.setSubjectScore(randomScore());
            subjects.add(subject);
        }
        return subjects;
    }

    //生成试卷
    public List<Paper> getPaperDB(){
        for(int i = 1;i <= PAPER_DB_SIZE;i++){
            //创建paper
            Paper paper = new Paper();
            //创建试卷set
            Set<Subject> subjects = new HashSet<>();
            //创建试卷知识点
            Set<Knowledge> knowledges = new HashSet<>();
            //设置paper编号ID
            paper.setPaperId(i);
            //每一份试卷30题
            for (int n=1;n<=30;n++){
                Subject subject = new Subject();
                Knowledge knowledge = new Knowledge();
                if (n>0&&n<=10){
                    subject.setSubjectType(Subject.SUBJECT_TYPE_SELECT_SINGLE);
                }else if(n>10&&n<=20){
                    subject.setSubjectType(Subject.SUBJECT_TYPE_COMPLETION);
                }else if (n>20&&n<=30){
                    subject.setSubjectType(Subject.SUBJECT_TYPE_WRITTEN);
                }
                //设置试题考点
                knowledge.setKnowledgeId(randomPoints());
                subject.setSubjectId(n);
                subject.setSubjectDifficulty(randomDifficult());
                subject.setSubjectKnowledge(knowledge);
                subject.setSubjectScore(randomScore());
                //试题加入试卷
                subjects.add(subject);
                //知识点加入知识点列表
                knowledges.add(knowledge);
            }
            //设置试卷题库
            paper.setTotalSubjects(new ArrayList<>(subjects));
            //设置试卷知识点
            paper.setPaperKnowledge(knowledges);
            //试卷加入试卷列表
            papers.add(paper);
            //清空知识点
            knowledges.clear();
        }
        return papers;
    }

    //生成随机难度系数
    public int randomDifficult(){
        //0-0.3难度系数
        int difficult = (int)(Math.random()*(3-1)+1);
        return difficult;
    }

    //随机知识点
    public int randomPoints(){
        //1-50知识点
        int point = (int)(Math.random()*(50-1)+1);
        return point;
    }

    //随机分数
    public int randomScore(){
        //1-3分分数
        int score = (int)(Math.random()*(3-1)+1);
        return score;
    }

}
