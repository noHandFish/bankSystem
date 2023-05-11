package com.dvlms.common.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PassDto implements Serializable {

    @NotBlank(message = "账户名不能为空")
    private String username;

    @NotBlank(message = "新密码不能为空")
    private String password;

    @NotBlank(message = "旧密码不能为空")
    private String currentPass;

    @NotBlank(message = "新密码不能为空")
    private String passwordAgainInput;
}
