package xyz.lengmaomao.autopapersystem.service;

import xyz.lengmaomao.autopapersystem.beans.Knowledge;

import java.util.List;

public interface KnowledgeService {
    //按ID查询知识点
    public Knowledge getKnowledge(int knowledgeId);
    //增加知识点
    public int addKnowledge(Knowledge knowledge);
    //修改知识点
    public void updateKnowledge(Knowledge knowledge);
    //删除知识点
    public void deleteKnowledge(int knowledgeId);
    //模糊查询知识点
    public List<Knowledge> findKnowledgeByTemplate(Knowledge knowledge);
    //查询所有知识点
    public List<Knowledge> findAllKnowledge();
}
