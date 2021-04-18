package xyz.lengmaomao.autopapersystem.service.Impl;

import org.springframework.stereotype.Service;
import xyz.lengmaomao.autopapersystem.beans.Subject;
import xyz.lengmaomao.autopapersystem.mapper.SubjectMapper;
import xyz.lengmaomao.autopapersystem.service.SubjectService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Resource
    SubjectMapper subjectMapper;
    @Override
    public Subject getSubject(int subjectId) {
        return subjectMapper.getSubject(subjectId);
    }

    @Override
    public void deleteSubject(int subjectId) {
        subjectMapper.deleteSubject(subjectId);
    }

    @Override
    public void updateSubject(Subject subject) {
        subjectMapper.updateSubject(subject);
    }

    @Override
    public int addSubject(Subject subject) {
        subjectMapper.addSubject(subject);
        return subject.getSubjectId();
    }

    @Override
    public Set<Subject> getSubjectByPaper(int paperId) {
        return subjectMapper.getSubjectByPaper(paperId);
    }


    @Override
    public List<Subject> getSubjectByTemplate(Subject subject) {
        return subjectMapper.getSubjectByTemplate(subject);
    }

    @Override
    public List<Subject> getAllSubject(int paperNumber,int nums) {
        return subjectMapper.getAllSubject((paperNumber-1)*nums,nums);
    }

    @Override
    public List<Subject> getSubjectsByUser(int userId,int page,int nums) {
        return subjectMapper.getSubjectsByUser(userId,page*(page-1),nums);
    }

    @Override
    public List<Subject> getSubjectByType(String type, int size,int user) {
        return subjectMapper.getSubjectByType(type,size,user);
    }

    @Override
    public Subject getSingleSubjectByType(String type,int user) {
        return subjectMapper.getSingleSubjectByType(type,user);
    }

    @Override
    public int getSubjectsNum(String type, int user) {
        return subjectMapper.getSubjectsNum(type,user);
    }

    @Override
    public int getPublicSubjectCount(int page,int nums) {
        return subjectMapper.getPublicSubjectCount(page*(page-1),nums);
    }

    @Override
    public int getMySubjectNum(int userId) {
        return subjectMapper.getMySubjectNum(userId);
    }


    @Override
    public void say() {
        System.out.println("hello");
    }

}
