package xyz.lengmaomao.autopapersystem.beans.subjects;

import lombok.Data;
import xyz.lengmaomao.autopapersystem.beans.Subject;

/*
    简答题
 */
@Data
public class WritenSubject extends Subject {
    //题目答案
    private String subjectAnswerCollection;

    public WritenSubject() {
        this.subjectType = SUBJECT_TYPE_WRITTEN;
    }

    public String getSubjectAnswerCollection() {
        if (this.subjectAnswerCollection==null){
            this.subjectAnswerCollection=subjectAnswer;
        }
        return subjectAnswerCollection;
    }

    @Override
    public String toString() {
        getSubjectAnswerCollection();
        return "WritenSubject{" +
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
