package xyz.lengmaomao.autopapersystem.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xyz.lengmaomao.autopapersystem.beans.Paper;

import java.util.List;


@Mapper
public interface PaperMapper {
    //添加试卷
    int insertPaper(Paper paper);
    //删除试卷
    void deletePaper(int paperId);
    //修改试卷
    void updatePaper(Paper paper);
    //查询试卷(不包含题目)
    Paper getPaper(int paperId);
    //查询试卷(包含题目)
    Paper findPaper(int paperId);
    //插入paper-subject总中间表
    void insertStagPaper(@Param("paperId") int paperId,@Param("subjectId") int subjectId);
    //删除paper-subject总中间表
    void deleteStagPaper(@Param("paperId") int paperId,@Param("subjectId") int subjectId);

    //查询试卷(使用模式串匹配查询)
    List<Paper> findPaperByTemplate(Paper paper);
    //查询试卷(用于数据展示,不包含试题)
    List<Paper> findAllPaper();
}