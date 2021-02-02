package xyz.lengmaomao.autopapersystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.lengmaomao.autopapersystem.beans.User;
import xyz.lengmaomao.autopapersystem.mapper.CourseMapper;
import xyz.lengmaomao.autopapersystem.mapper.SubjectMapper;
import xyz.lengmaomao.autopapersystem.service.SubjectService;
import xyz.lengmaomao.autopapersystem.service.UserService;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class AutoPapersystemApplicationTests {
    @Resource
    UserService userService;
    @Test
    void contextLoads() {
        User userTemplate = new User();
        userTemplate.setUserName("冷猫猫呀");
        List<User> users = userService.findUserByTemplate(userTemplate);
        System.out.println(users.size());
        User user = users.get(0);
        System.out.println(user);
    }

}
