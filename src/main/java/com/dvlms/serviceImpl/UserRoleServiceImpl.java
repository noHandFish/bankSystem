package com.dvlms.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dvlms.entity.UserRole;
import com.dvlms.mapper.UserRoleMapper;
import com.dvlms.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dvlms.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhb
 * @since 2022-03-07
 */
@Slf4j
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public UserRole getByUserName(String username) {
        return getOne(new QueryWrapper<UserRole>().eq("username", username));
    }

    @Override
    public String getUserAuthorityInfo(String username) {
        UserRole userRole = userRoleMapper.selectById(username);
        String authority = null;
        // 这里添加缓存是为了点击多个方法时减少对数据库的请求
        if (redisUtil.hasKey("GrantedAuthority:" + userRole.getUserName())) {
            // 优先从缓存获取
            authority = (String) redisUtil.get("GrantedAuthority:" + userRole.getUserName());
        }else{
            authority = userRole.getPerms();
            log.info("用户 - {} ---权限身份为：{}", username, authority);
            redisUtil.set("GrantedAuthority:" + userRole.getUserName(), authority, 60*60);
        }

        return authority;
    }

    @Override
    public void clearUserAuthorityInfoInRedis(String username) {
        redisUtil.del("GrantedAuthority:" + username);
    }
}
