package xyz.lengmaomao.autopapersystem.beans;

import lombok.Data;

@Data
public class Knowledge {
    private int knowledgeId;
    private String knowledgeContext;
    private String knowledgeChapter;
    private Course knowledgeCourse;

    @Override
    public String toString() {
        return
//                "Knowledge{" + '\n'+
                "knowledgeId=" + knowledgeId + '\n';
//                ", knowledgeContext='" + knowledgeContext + '\'' + '\n'+
//                ", knowledgeChapter='" + knowledgeChapter + '\'' + '\n'+
//                ", knowledgeCourse=" + knowledgeCourse + '\n'+
//                '}' + '\n';
    }
}
