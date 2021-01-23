package xyz.lengmaomao.autopapersystem.service.Impl;

import org.springframework.stereotype.Service;
import xyz.lengmaomao.autopapersystem.beans.Paper;
import xyz.lengmaomao.autopapersystem.beans.Subject;
import xyz.lengmaomao.autopapersystem.mapper.PaperMapper;
import xyz.lengmaomao.autopapersystem.service.PaperService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PaperServiceImpl implements PaperService {
    @Resource
    PaperMapper paperMapper;
    @Override
    public int addPaper(Paper paper) {
        paperMapper.insertPaper(paper);
        System.out.println(paper.toString());
        if (paper.getTotalSubjects()!=null){
            for (Subject subject:paper.getTotalSubjects()){
                paperMapper.insertStagPaper(paper.getPaperId(),subject.getSubjectId());
            }
        }
        return paper.getPaperId();
    }

    @Override
    public void deletePaper(int paperId) {
        paperMapper.deletePaper(paperId);
        paperMapper.deleteStagPaper(paperId,0);
    }

    @Override
    public int updatePaper(Paper paper) {
        paperMapper.updatePaper(paper);
        if (paper.getTotalSubjects()!=null){
            Paper oldPaper = paperMapper.getPaper(paper.getPaperId());
            if (!oldPaper.getTotalSubjects().equals(paper.getTotalSubjects())){
                Set<Subject> set = new HashSet<>();
                //获取old与paper的差集,并进行删除
                set.addAll(oldPaper.getTotalSubjects());
                set.removeAll(paper.getTotalSubjects());
                for (Subject subject:set){
                    paperMapper.deleteStagPaper(paper.getPaperId(),subject.getSubjectId());
                }
                set.clear();
                //获取paper与old的差集,并进行添加
                set.addAll(paper.getTotalSubjects());
                set.removeAll(oldPaper.getTotalSubjects());
                for (Subject subject:set){
                    paperMapper.insertStagPaper(paper.getPaperId(),subject.getSubjectId());
                }
            }
        }
        return paper.getPaperId();
    }

    @Override
    public Paper getPaper(int paperId) {
        return paperMapper.getPaper(paperId);
    }

    @Override
    public Paper findPaper(int paperId) {
        return paperMapper.findPaper(paperId);
    }

    @Override
    public List<Paper> findPaperByTemplate(Paper paper) {
        return paperMapper.findPaperByTemplate(paper);
    }

    @Override
    public List<Paper> findAllPaper() {
        return paperMapper.findAllPaper();
    }
}
