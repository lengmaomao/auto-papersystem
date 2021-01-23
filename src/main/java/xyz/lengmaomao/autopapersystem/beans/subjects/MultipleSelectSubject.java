package xyz.lengmaomao.autopapersystem.beans.subjects;

import lombok.Data;
import xyz.lengmaomao.autopapersystem.beans.Subject;

import java.util.Arrays;
import java.util.List;

/*
    多选题
 */
@Data
public class MultipleSelectSubject extends Subject {

    //题目答案
    private List<String> subjectAnswerCollection;

    public MultipleSelectSubject() {
        this.subjectType = SUBJECT_TYPE_SELECT_MULTIPLE;
    }

    public List<String> getSubjectAnswerCollection() {
        if (this.subjectAnswerCollection==null)
            this.subjectAnswerCollection= Arrays.asList(subjectAnswer.split(" "));
        return this.subjectAnswerCollection;
    }

    @Override
    public String toString() {
        getSubjectAnswerCollection();
        return "MultipleSelectSubject{" +
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
