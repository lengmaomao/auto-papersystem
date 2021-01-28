package xyz.lengmaomao.autopapersystem;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.lengmaomao.autopapersystem.mapper.CourseMapper;
import xyz.lengmaomao.autopapersystem.mapper.SubjectMapper;
import xyz.lengmaomao.autopapersystem.service.SubjectService;

import javax.annotation.Resource;

@SpringBootTest
class AutoPapersystemApplicationTests {
    @Resource
    SubjectService subjectMapper;
    @Test
    void contextLoads() {
        System.out.println(subjectMapper.getAllSubject());

    }

}
