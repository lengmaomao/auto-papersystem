package xyz.lengmaomao.autopapersystem.beans.subjects;

import lombok.Data;
import xyz.lengmaomao.autopapersystem.beans.Subject;

import java.util.Arrays;
import java.util.List;

/*
    填空题
 */
@Data
public class CompletionSubject extends Subject {

    //题目答案
    private List<String> subjectAnswerCollection;

    public CompletionSubject() {
        this.subjectType = SUBJECT_TYPE_COMPLETION;
    }

    public List<String> getSubjectAnswerCollection() {
        if (this.subjectAnswer == "" || this.subjectAnswer == null)
            return null;
        if (this.subjectAnswerCollection==null)
            this.subjectAnswerCollection= Arrays.asList(subjectAnswer.split(" "));
        return subjectAnswerCollection;
    }

    @Override
    public String toString() {
        getSubjectAnswerCollection();
        return "CompletionSubject{" +
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
