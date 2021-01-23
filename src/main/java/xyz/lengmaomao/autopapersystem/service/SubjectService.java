package xyz.lengmaomao.autopapersystem.service;


import xyz.lengmaomao.autopapersystem.beans.Subject;

import java.util.List;
import java.util.Set;

public interface SubjectService {
    //获得题目
    Subject getSubject(int subjectId);
    //删除题目
    void deleteSubject(int subjectId);
    //修改题目
    void updateSubject(Subject subject);
    //新增题目
    int addSubject(Subject subject);
    //获取试卷的题目列表
    Set<Subject> getSubjectByPaper(int paperId);
    //题目模糊查询
    List<Subject> getSubjectByTemplate(Subject subject);
    //题目全体查询
    List<Subject> getAllSubject();
}
