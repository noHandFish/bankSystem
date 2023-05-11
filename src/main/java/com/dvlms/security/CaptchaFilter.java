package com.dvlms.security;


import com.dvlms.common.exception.CaptchaException;
import com.dvlms.common.lang.Const;
import com.dvlms.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ͼƬ��֤��У����������ڵ�¼������ǰ
 */
@Slf4j
@Component
public class CaptchaFilter extends OncePerRequestFilter {
    private final String loginUrl = "/login";
    @Autowired
    RedisUtil redisUtil;

    @Autowired
    LoginFailureHandler loginFailureHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String url = request.getRequestURI();
        if (loginUrl.equals(url) && request.getMethod().equals("POST")) {
            log.info("����У����֤��" + url);
            try {
                validate(request);
            } catch (CaptchaException e) {
                // ��֤��ʧ�ܽ�����¼ʧ�ܴ���������
                log.info(e.getMessage());
                loginFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void validate(HttpServletRequest request) throws CaptchaException {
        String code = request.getParameter("code");
        String token = request.getParameter("token");
        if (code.isBlank() || token.isBlank()) {
            throw new CaptchaException("��֤�벻��Ϊ��");
        }
        if (!code.equals(redisUtil.hget(Const.CAPTCHA_KEY, token))) {
            throw new CaptchaException("��֤�벻��ȷ");
        }
        redisUtil.hdel(Const.CAPTCHA_KEY, token); // ��֤��ɾ������֤��
    }


}
