package xyz.lengmaomao.autopapersystem.service.Impl;

import org.springframework.stereotype.Service;
import xyz.lengmaomao.autopapersystem.beans.Knowledge;
import xyz.lengmaomao.autopapersystem.mapper.KnowledgeMapper;
import xyz.lengmaomao.autopapersystem.service.KnowledgeService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {
    @Resource
    KnowledgeMapper knowledgeMapper;
    @Override
    public Knowledge getKnowledge(int knowledgeId) {
        return knowledgeMapper.getKnowledge(knowledgeId);
    }

    @Override
    public int addKnowledge(Knowledge knowledge) {
         knowledgeMapper.addKnowledge(knowledge);
         return knowledge.getKnowledgeId();
    }

    @Override
    public void updateKnowledge(Knowledge knowledge) {
        knowledgeMapper.updateKnowledge(knowledge);
    }

    @Override
    public void deleteKnowledge(int knowledgeId) {
        knowledgeMapper.deleteKnowledge(knowledgeId);

    }

    @Override
    public List<Knowledge> findKnowledgeByTemplate(Knowledge knowledge) {
        return knowledgeMapper.findKnowledgeByTemplate(knowledge);
    }

    @Override
    public List<Knowledge> findAllKnowledge() {
        return knowledgeMapper.findAllKnowledge();
    }
}
