package xyz.lengmaomao.autopapersystem.service.Impl;

import org.springframework.stereotype.Service;
import xyz.lengmaomao.autopapersystem.beans.Course;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.beans.Subject;
import xyz.lengmaomao.autopapersystem.beans.User;
import xyz.lengmaomao.autopapersystem.service.UserService;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Override
    public int addUser(User user) {
        return 0;
    }

    @Override
    public void deleteUser(int userId) {

    }

    @Override
    public User findUser(int userId) {
        return null;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public List<Course> findUserCourse(int userId) {
        return null;
    }

    @Override
    public List<Paper> findUserPaper(int userId) {
        return null;
    }

    @Override
    public List<Subject> findUserSubject(int userId) {
        return null;
    }

    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public List<User> findUserByTemplate(User user) {
        return null;
    }
}
