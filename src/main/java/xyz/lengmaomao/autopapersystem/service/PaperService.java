package xyz.lengmaomao.autopapersystem.service;

import xyz.lengmaomao.autopapersystem.beans.Paper;

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
    //查询试卷(使用模式匹配查询)
    List<Paper> findPaperByTemplate(Paper paper);
    //查询试卷(用于数据展示,不包含试题)
    List<Paper> findAllPaper();
}
