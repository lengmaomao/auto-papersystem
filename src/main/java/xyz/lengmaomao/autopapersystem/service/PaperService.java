package xyz.lengmaomao.autopapersystem.service;

import xyz.lengmaomao.autopapersystem.VO.PaperCreateRule;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.beans.Subject;

import java.util.List;

public interface PaperService {
    //增加试卷
    int addPaper(Paper paper);
    //删除试卷
    void deletePaper(int paperId);
    //修改试卷
    int updatePaper(Paper paper);
    //查询试卷(不包含实体)
    Paper getPaper(int paperId);
    //查询试卷(包含试题)
    Paper findPaper(int paperId);
    //查询试卷不包含在题目集合里的其余所属试卷
    List<Subject> getPaperSpareSubject(int paperId);
    //查询试卷(使用模式匹配查询)
    List<Paper> findPaperByTemplate(Paper paper);
    //查询试卷(用于数据展示,不包含试题)
    List<Paper> findAllPaper();
    //自动生成试卷
    Paper autoPaper(PaperCreateRule rule);
    //获取用户所有试卷
    List<Paper> getUserPaper(int userId,int pageNumber,int nums);
    //插入试卷试题中间表
    void insertStagPaper(int paperId, int subjectId, int subjectPaperId);
    //删除试卷试题中间表
    void deleteStagPaper(int paperId, int subjectId);
    //返回用户下所有试卷
    int findUserPaperNums(int userId);
}
