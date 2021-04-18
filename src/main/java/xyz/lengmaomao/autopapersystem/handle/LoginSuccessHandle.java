package xyz.lengmaomao.autopapersystem.handle;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @program: auto-papersystem
 * @description
 * @author: song zi nan
 * @create: 2021-03-24 08:34
 **/
public class LoginSuccessHandle implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //获取权限
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        String path = httpServletRequest.getContextPath() ;
        String basePath = httpServletRequest.getScheme()+"://"+httpServletRequest.getServerName()+":"+httpServletRequest.getServerPort()+path+"/";
        if (roles.contains("ROLE_ADMIN")){
            httpServletResponse.sendRedirect(basePath+"admin");
            return;
        }
        httpServletResponse.sendRedirect(basePath+"index");
    }
}
