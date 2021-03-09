package xyz.lengmaomao.autopapersystem.VO;

import lombok.Data;
import xyz.lengmaomao.autopapersystem.beans.Course;
import xyz.lengmaomao.autopapersystem.beans.Paper;

import java.util.Date;
import java.util.List;

@Data
public class PaperCreateVO {
    //试卷名
    String paperName;
    //权限设置
    boolean share;
    //科目ID
    int courseId;
    //创建方式
    String builder;
    //作者
    int author;
    //题目类型与数量
    SubjectTypeVO subjects;
    //试卷分数
    int score;
    //知识点
    List<KnowledgeVO> knowledge;
    //试卷难度
    double difficult;

    public Paper transfer(){
        Paper paper = new Paper();
        Course course = new Course();
        course.setCourseId(courseId);
        paper.setPaperName(paperName);
        paper.setPaperAuthor(author);
        paper.setPaperLimit(share);
        paper.setPaperCourse(course);
        paper.setPaperScore(score);
        paper.setDifficulty(difficult);
        paper.setPaperCreateTime(new Date());
        return paper;
    }

}
