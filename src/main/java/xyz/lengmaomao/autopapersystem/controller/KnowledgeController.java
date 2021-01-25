package xyz.lengmaomao.autopapersystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lengmaomao.autopapersystem.beans.Knowledge;
import xyz.lengmaomao.autopapersystem.service.KnowledgeService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/knowledge")
public class KnowledgeController {
    @Resource
    KnowledgeService knowledgeService;

    @ResponseBody
    @RequestMapping("/find_knowledge")
    public List<Knowledge> findKnowledge(@RequestBody Knowledge knowledge){
        List<Knowledge> list = knowledgeService.findKnowledgeByTemplate(knowledge);
        return list;
    }
}
