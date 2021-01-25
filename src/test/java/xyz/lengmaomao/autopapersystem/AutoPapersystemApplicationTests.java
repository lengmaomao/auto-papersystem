package xyz.lengmaomao.autopapersystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.lengmaomao.autopapersystem.mapper.CourseMapper;

import javax.annotation.Resource;

@SpringBootTest
class AutoPapersystemApplicationTests {
    @Resource
    CourseMapper courseMapper;
    @Test
    void contextLoads() {
        System.out.println(courseMapper.getCourse(1));

    }

}
