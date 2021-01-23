package xyz.lengmaomao.autopapersystem.beans.subjects;

import lombok.Data;
import xyz.lengmaomao.autopapersystem.beans.Subject;
/*
    判断题
 */
@Data
public class TrueOrFalseSubject extends Subject {
    //题目答案(1为正确,0为错误)
    private Integer subjectAnswerCollection;

    public TrueOrFalseSubject() {
        this.subjectType = SUBJECT_TYPE_TRUE_OR_FALSE;
    }

    public int getSubjectAnswerCollection() {
        if (this.subjectAnswerCollection == null){
            this.subjectAnswerCollection=Integer.valueOf(subjectAnswer);
        }
        return subjectAnswerCollection;
    }

    @Override
    public String toString() {
        getSubjectAnswerCollection();
        return "TrueOrFalseSubject{" +
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
