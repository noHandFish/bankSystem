package com.dvlms.service;

import com.dvlms.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhb
 * @since 2022-03-07
 */
public interface UserRoleService extends IService<UserRole> {

    UserRole getByUserName(String username);

    String getUserAuthorityInfo(String username);

    void clearUserAuthorityInfoInRedis(String username);
}

