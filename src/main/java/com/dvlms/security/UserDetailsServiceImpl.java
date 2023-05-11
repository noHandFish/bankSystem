package com.dvlms.security;

import com.dvlms.common.lang.Result;
import com.dvlms.controller.UserRoleController;
import com.dvlms.entity.UserRole;
import com.dvlms.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRoleService userRoleService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("正在获取UserRole");
        UserRole userRole = userRoleService.getByUserName(username);
        if (userRole == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        return new AccountUser(userRole.getUserName(), userRole.getPassword(), getUserAuthority(userRole.getUserName()));
    }

    public List<GrantedAuthority> getUserAuthority(String userName) {
        // ROLE_SeniorManager,ROLE_MiddleManager,ROLE_Marketer,ROLE_ExtensionWorker
        String authority = userRoleService.getUserAuthorityInfo(userName);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authority);
    }
}
