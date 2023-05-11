package com.dvlms.security;

import cn.hutool.json.JSONUtil;
import com.dvlms.common.lang.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ��¼ʧ�ܴ�����
 * ��ȡ�쳣����Ϣ��Ȼ���װ��Result�����ת��json���ظ�ǰ��
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        Result result = new Result<>(
                "null",
                400,
                "Bad credentials".equals(exception.getMessage()) ? "�û��������벻��ȷ---" : exception.getMessage()
        );
        outputStream.write(JSONUtil.toJsonStr(result).getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }


}
