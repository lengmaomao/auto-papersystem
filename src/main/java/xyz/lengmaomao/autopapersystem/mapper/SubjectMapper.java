package xyz.lengmaomao.autopapersystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xyz.lengmaomao.autopapersystem.beans.Subject;

import java.util.List;
import java.util.Set;


@Mapper
public interface SubjectMapper {
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

    //新增考点到题目多对多关系
    void addCourseSubject(@Param("courseId") int courseId,@Param("subjectId") int subjectId);

    //删除考点到题目多对多关系
    void deleteCourseSubject(@Param("courseId") int courseId,@Param("subjectId") int subjectId);

    //题目模糊查询
    List<Subject> getSubjectByTemplate(Subject subject);

    //题目全体查询
    List<Subject> getAllSubject(int offset, int nums);

    //通过登录的用户查询试题
    List<Subject> getSubjectsByUser(int userId,int page,int nums);

    /**
     * 通过试题类型查询实体列表
     * @param type
     * @param size
     * @param user
     * @return
     */
    List<Subject> getSubjectByType(@Param("type") String type,@Param("size") int size,@Param("user") int user);

    //按照实体类型单个查询
    Subject getSingleSubjectByType(@Param("type") String type,@Param("user") int user);

    //查询题目数量
    int getSubjectsNum(String type,int user);

    //查询所有公开题目数量
    int getPublicSubjectCount(int page,int nums);

    //查询用户下的题目数量
    int getMySubjectNum(int userId);
}
