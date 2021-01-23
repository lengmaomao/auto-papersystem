package xyz.lengmaomao.autopapersystem.mapper;

import org.apache.ibatis.annotations.Mapper;
import xyz.lengmaomao.autopapersystem.beans.Course;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.beans.Subject;
import xyz.lengmaomao.autopapersystem.beans.User;

import java.util.List;

@Mapper
public interface UserMapper {
//    增加用户
    public int addUser(User user);
//    删除用户
    public void deleteUser(int userId);
//    查询用户(基本信息)
    public User findUser(int userId);
//    修改用户
    public int updateUser(User user);
//    查询用户(担任课程)
    public List<Course> findUserCourse(int userId);
//    查询用户(所有的试卷)
    public List<Paper> findUserPaper(int userId);
//    查询用户(所有题目)
    public List<Subject> findUserSubject(int userId);
//    查询所有用户
    public List<User> findAllUser();
//    模糊查询用户
    public List<User> findUserByTemplate(User user);
//    删除用户-科目中间表
    public void deleteUserCourse(int userId, int courseId);
//    增加用户-科目中间表
    public int addUserCourse(int userId, int courseId);
}

