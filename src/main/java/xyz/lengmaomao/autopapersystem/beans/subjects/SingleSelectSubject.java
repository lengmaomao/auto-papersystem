package xyz.lengmaomao.autopapersystem.beans.subjects;

import lombok.Data;
import xyz.lengmaomao.autopapersystem.beans.Subject;
/*
    单选题
 */
@Data
public class SingleSelectSubject extends Subject {

    //题目答案
    private Character subjectAnswerCollection;

    public SingleSelectSubject() {
        this.subjectType = SUBJECT_TYPE_SELECT_SINGLE;
    }

    public Character getSubjectAnswerCollection() {
        if (this.subjectAnswerCollection == null){
            this.subjectAnswerCollection = subjectAnswer.charAt(0);
        }
        return subjectAnswerCollection;
    }

    @Override
    public String toString() {
        getSubjectAnswerCollection();
        return "SingleSelectSubject{" +
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
