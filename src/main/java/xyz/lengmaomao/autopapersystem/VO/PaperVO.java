package xyz.lengmaomao.autopapersystem.VO;

import lombok.Data;
import xyz.lengmaomao.autopapersystem.beans.Paper;

@Data
public class PaperVO {
    int code;
    Paper paper;
    String message;
}
