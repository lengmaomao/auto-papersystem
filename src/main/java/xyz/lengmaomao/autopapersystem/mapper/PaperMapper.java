package xyz.lengmaomao.autopapersystem.mapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.beans.Subject;

import java.util.List;


@Mapper
public interface PaperMapper {
    //添加试卷
    int insertPaper(Paper paper);
    //删除试卷
    void deletePaper(int paperId);
    //修改试卷
    void updatePaper(Paper paper);
    //查询试卷
    Paper getPaper(int paperId);
    //查询试卷未添加到试题集合中的其余stimulate
    List<Subject> getPaperSpareSubject(int paperId);
    //插入paper-subject总中间表
    void insertStagPaper(@Param("paperId") int paperId,@Param("subjectId") int subjectId,@Param("subjectPaperId") int subjectPaperId);
    //删除paper-subject总中间表
    void deleteStagPaper(@Param("paperId") int paperId,@Param("subjectId") int subjectId);

    //查询试卷(使用模式串匹配查询)
    List<Paper> findPaperByTemplate(Paper paper);
    //查询试卷(用于数据展示,不包含试题)
    List<Paper> findAllPaper();
    //获取用户所有的ID
    List<Paper> getUserPaper(int userId,int pageNumber,int nums);
    //获取用户下所有试卷
    int findUserPaperNums(int userId);
}