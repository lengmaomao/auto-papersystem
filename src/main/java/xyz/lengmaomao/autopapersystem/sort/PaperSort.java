package xyz.lengmaomao.autopapersystem.sort;

import xyz.lengmaomao.autopapersystem.beans.Subject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PaperSort implements Comparator<Subject> {
    @Override
    public int compare(Subject o1, Subject o2) {
        String[] strings = new String[]{Subject.SUBJECT_TYPE_SELECT_SINGLE,Subject.SUBJECT_TYPE_SELECT_MULTIPLE,Subject.SUBJECT_TYPE_TRUE_OR_FALSE,Subject.SUBJECT_TYPE_COMPLETION,Subject.SUBJECT_TYPE_WRITTEN,Subject.SUBJECT_TYPE_COMPREHENSIVE};
        List<String> list = new ArrayList<String>(Arrays.asList(strings));
        int num1 = o1.getSubjectPaperId();
        int num2 = o2.getSubjectPaperId();
        int type1 = list.indexOf(o1.getSubjectType());
        int type2 = list.indexOf(o2.getSubjectType());
        int diff1 = o1.getSubjectDifficulty();
        int diff2 = o2.getSubjectDifficulty();
        if (type1!=type2)
            return type1-type2;
        else if (num1!=num2)
            return num1-num2;
        else
            return diff1 - diff2;
    }
}
