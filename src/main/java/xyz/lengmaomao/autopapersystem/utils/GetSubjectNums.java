package xyz.lengmaomao.autopapersystem.utils;

import xyz.lengmaomao.autopapersystem.beans.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class GetSubjectNums {
    public static List<Integer> getSubjectNums(Set<Subject> subjectSet){
        List<Integer> list=new ArrayList<>();
        for (Subject subject:subjectSet){
            list.add(subject.getSubjectId());
        }
        return list;
    }
}
