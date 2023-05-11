package com.dvlms.controller;

import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.map.MapUtil;
import com.dvlms.common.lang.Const;
import com.dvlms.common.lang.Result;
import com.google.code.kaptcha.Producer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.UUID;

@Slf4j
@RestController
@Api(tags = {"验证码数据请求接口"})
public class AuthController<T> extends BaseContoller {

    @Autowired
    Producer producer;

    @GetMapping("/captcha")
    @ApiOperation(value = "获取验证码图片", notes = "返回格式为{success: ,code: ,message: ,data: {captchaImg} }")
    public Object captcha() throws IOException {
        String key = UUID.randomUUID().toString(); //32位随机数
        String code = producer.createText(); //生成五位数验证码

        BufferedImage image = producer.createImage(code);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", outputStream);

        // 将图片输出流转成base64格式
        Base64Encoder encoder = new Base64Encoder();
        String str = "data:image/jpeg;base64,"; //字符前缀
        String base64Img = str + encoder.encode(outputStream.toByteArray()); //图片base64格式

        redisUtil.hset(Const.CAPTCHA_KEY, key, code, 120); //存放在redis的有效时间
        log.info("验证码 -- {} - {}", key, code);;
        return new Result<>(
                MapUtil.builder()
                        .put("token",key)
                        .put("captchaImg",base64Img)
                        .build()
        );


    }
}
