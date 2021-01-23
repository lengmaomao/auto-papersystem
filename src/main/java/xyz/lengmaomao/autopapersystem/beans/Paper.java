package xyz.lengmaomao.autopapersystem.beans;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
public class Paper {
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
    private Set<Subject> totalSubjects;
    //试卷作者
    private int paperAuthor;
    //试卷创建时间
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date paperCreateTime;
    //试卷权限
    private boolean paperLimit;
//    //选择题(单选)
//    private Set<Subject> singleSelectSubjects;
//    //选择题(多选)
//    private Set<Subject> multipleSelectSubjects;
//    //填空题
//    private Set<Subject> completionSubjects;
//    //判断题
//    private Set<Subject> truefalseSubjects;
//    //简答题
//    private Set<Subject> writenSubjects;
//    //综合题
//    private Set<Subject> comprehensiveSubjects;
    //试卷所属学校
    private String paperSchool;
}
