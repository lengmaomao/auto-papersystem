package xyz.lengmaomao.autopapersystem.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@NoArgsConstructor
public class Course {
    private int courseId;
    private String courseName;
    private String courseBook;
    private String courseChapter;

    public Course(String courseName, String courseBook, String courseChapter) {
        this.courseName = courseName;
        this.courseBook = courseBook;
        this.courseChapter = courseChapter;
    }
}
