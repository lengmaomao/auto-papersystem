package xyz.lengmaomao.autopapersystem.service.Impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import xyz.lengmaomao.autopapersystem.mapper.UserMapper;

import javax.annotation.Resource;
import javax.management.Query;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        xyz.lengmaomao.autopapersystem.beans.User userTemplate = new xyz.lengmaomao.autopapersystem.beans.User();
        userTemplate.setUserName(username);
        List<xyz.lengmaomao.autopapersystem.beans.User> users = userMapper.findUserByTemplate(userTemplate);
        if (users.size()==0)
            throw new UsernameNotFoundException("用户不存在");
        xyz.lengmaomao.autopapersystem.beans.User user = users.get(0);
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getUserRole());
        return new User(String.valueOf(user.getUserId()),new BCryptPasswordEncoder().encode(user.getUserPassword()),auths);
    }
}
