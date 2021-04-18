package xyz.lengmaomao.autopapersystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.lengmaomao.autopapersystem.VO.PaperStatsVO;
import xyz.lengmaomao.autopapersystem.service.AdminService;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired(required = false)
    AdminService adminService;

    @RequestMapping("/stats/paper")
    public PaperStatsVO returnPaperStats(){
        PaperStatsVO paperStatsVO = new PaperStatsVO();
        return paperStatsVO;
    }

}
