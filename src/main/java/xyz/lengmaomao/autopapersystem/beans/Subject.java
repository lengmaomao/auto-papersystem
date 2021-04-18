package xyz.lengmaomao.autopapersystem.beans;

import lombok.Data;
import lombok.Getter;

import java.util.Objects;
import java.util.Set;

@Data
public class Subject {
    //题目难易度
    static final public int SUBJECT_DIFFICULTY_EASY = 0;
    static final public int SUBJECT_DIFFICULTY_MEDIUM = 1;
    static final public int SUBJECT_DIFFICULTY_HARD = 2;
    //题目类别
    //选择题(单选)
    static final public String SUBJECT_TYPE_SELECT_SINGLE = "单项选择题";
    //选择题(多选)
    static final public String SUBJECT_TYPE_SELECT_MULTIPLE = "多项选择题";
    //填空题
    static final public String SUBJECT_TYPE_COMPLETION = "填空题";
    //判断题
    static final public String SUBJECT_TYPE_TRUE_OR_FALSE = "判断题";
    //简答题
    static final public String SUBJECT_TYPE_WRITTEN = "简答题";
    //综合题
    static final public String SUBJECT_TYPE_COMPREHENSIVE = "综合题";


    //题目ID
    private int subjectId;

    //题目在试卷中的ID
    private int subjectPaperId;

    //题名
    private String subjectName;

    //题目学科
    private Course subjectCourse;

    //题目知识点
    private Knowledge subjectKnowledge;

    //题目分数
    private int subjectScore;

    //题目难易度
    private int subjectDifficulty;

    //题目种类
    private String subjectType;

    //题目描述
    private String subjectDescribe;

    //答案字符串
    private String subjectAnswer;

    //题目作者
    private int subjectAuthor;

    //分享
    private boolean subjectShare;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return subjectId == subject.subjectId &&
                subjectScore == subject.subjectScore &&
                subjectDifficulty == subject.subjectDifficulty &&
                subjectShare == subject.subjectShare &&
                Objects.equals(subjectName, subject.subjectName) &&
                Objects.equals(subjectCourse, subject.subjectCourse) &&
                Objects.equals(subjectKnowledge, subject.subjectKnowledge) &&
                Objects.equals(subjectType, subject.subjectType) &&
                Objects.equals(subjectDescribe, subject.subjectDescribe) &&
                Objects.equals(subjectAnswer, subject.subjectAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subjectId, subjectName, subjectCourse, subjectKnowledge, subjectScore, subjectDifficulty, subjectType, subjectDescribe, subjectAnswer, subjectShare);
    }

    @Override
    public String toString() {
        return
//                "Subject{" +
                "subjectId=" + subjectId +
//                ", subjectName='" + subjectName + '\'' +
//                ", subjectCourse=" + subjectCourse +
                ", subjectKnowledge=" + subjectKnowledge +
//                ", subjectScore=" + subjectScore +
                ", subjectDifficulty=" + subjectDifficulty +
                ", subjectType='" + subjectType + '\'' ;
//                ", subjectDescribe='" + subjectDescribe + '\'' +
//                ", subjectAnswer='" + subjectAnswer + '\'' +
//                ", subjectShare=" + subjectShare +
//                '}';
    }
}
