package xyz.lengmaomao.autopapersystem.beans;

import lombok.Data;

@Data
public class Knowledge {
    private int knowledgeId;
    private String knowledgeContext;
    private String knowledgeChapter;
    private Course knowledgeCourse;
}
