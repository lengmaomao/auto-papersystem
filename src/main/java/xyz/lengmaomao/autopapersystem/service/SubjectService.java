package xyz.lengmaomao.autopapersystem.service;


import org.apache.ibatis.annotations.Param;
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
    List<Subject> getAllSubject(int paperNumber,int nums);
    //获取登录用户下所有题目
    List<Subject> getSubjectsByUser(int userId,int page,int nums);
    //按照类型获取试题列表
    List<Subject> getSubjectByType(String type,int size,int user);
    //按照实体类型单个查询
    Subject getSingleSubjectByType(String type,int user);
    //查询题目数量
    int getSubjectsNum(String type,int user);
    //查询所有试题数量
    int getPublicSubjectCount(int page, int nums);
    //获取用户下所有题目数量
    int getMySubjectNum(int userId);
    //hello
    void say();
}
