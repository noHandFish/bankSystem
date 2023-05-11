package com.dvlms.serviceImpl;

import com.dvlms.entity.ClientInfo;
import com.dvlms.mapper.ClientInfoMapper;
import com.dvlms.service.ClientInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhb
 * @since 2022-03-05
 */
@Service
public class ClientInfoServiceImpl extends ServiceImpl<ClientInfoMapper, ClientInfo> implements ClientInfoService {

}
