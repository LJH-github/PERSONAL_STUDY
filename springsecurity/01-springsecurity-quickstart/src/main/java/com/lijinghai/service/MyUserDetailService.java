package com.lijinghai.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lijinghai.mapper.UsersMapper;
import com.lijinghai.pojo.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userDetailsService")
@Slf4j
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private UsersMapper usersMapper;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("当前输入的username:[{}]", username);

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        Users users = usersMapper.selectOne(queryWrapper);

        if (users == null) {
            throw new UsernameNotFoundException("用户不存在！");
        }
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_sale,manager");
        return new User(users.getUsername(), new BCryptPasswordEncoder().encode(users.getPassword()), auths);

    }
}
