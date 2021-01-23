package xyz.lengmaomao.autopapersystem.beans.subjects;

import lombok.Data;
import xyz.lengmaomao.autopapersystem.beans.Subject;

import java.util.Map;

/*
    综合题
 */
@Data
public class ComprehensiveSubject extends Subject {
    //题目答案
    private Map<Integer,String> subjectAnswerCollection;

    public ComprehensiveSubject() {
        this.subjectType = SUBJECT_TYPE_COMPREHENSIVE;
    }

    public Map<Integer, String> getSubjectAnswerCollection() {
        if (this.subjectAnswerCollection==null){
            String[] ans= subjectAnswer.split(" ");
            for (String s: ans){
                subjectAnswerCollection.put(Integer.valueOf(s.split(",")[0]),s.split(",")[1]);
            }
        }
        return subjectAnswerCollection;
    }

    @Override
    public String toString() {
        getSubjectAnswerCollection();
        return "ComprehensiveSubject{" +
                "subjectAnswerCollection='" + subjectAnswerCollection + '\'' +
                ", subjectId=" + subjectId +
                ", subjectCourse=" + subjectCourse +
                ", subjectScore=" + subjectScore +
                ", subjectDifficulty=" + subjectDifficulty +
                ", subjectType='" + subjectType + '\'' +
                ", subjectDescribe='" + subjectDescribe + '\'' +
                ", subjectAnswer='" + subjectAnswer + '\'' +
                '}';
    }
}
